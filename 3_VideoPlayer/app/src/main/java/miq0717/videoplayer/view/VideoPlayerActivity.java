package miq0717.videoplayer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import miq0717.videoplayer.R;
import miq0717.videoplayer.viewModel.MainActivityViewModel;

import static android.view.View.GONE;

public class VideoPlayerActivity extends AppCompatActivity {

    @BindView(R.id.vdvVideoPlayer)
    VideoView vdvVideoPlayer;
    @BindView(R.id.imbRepeat)
    ImageButton imbRepeat;

    private Context context;
    private MainActivityViewModel mainActivityViewModel;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        context = this;
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        position = getIntent().getIntExtra("position", -1);

        //initialize viewModel to for playing particular video
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        //hide actionbar to play video in fullscreen mode
        getSupportActionBar().hide();

        playVideo();
    }

    //functionality to play video
    private void playVideo() {
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(vdvVideoPlayer);
        vdvVideoPlayer.setMediaController(mediaController);
        vdvVideoPlayer.setVideoPath(String.valueOf(mainActivityViewModel.getVideoFiles().getValue().get(position)));
        vdvVideoPlayer.requestFocus();
        setListeners(mediaController);
    }

    //listeners for video play, rewind, forward functionalities
    private void setListeners(MediaController mediaController) {
        vdvVideoPlayer.setOnPreparedListener(mediaPlayer -> {
            vdvVideoPlayer.start();
        });

        vdvVideoPlayer.setOnCompletionListener(mediaPlayer -> {
            imbRepeat.setVisibility(View.VISIBLE);
            imbRepeat.setOnClickListener(v -> {
                imbRepeat.setVisibility(GONE);
                vdvVideoPlayer.start();
            });
        });

//to play next or previous video
        mediaController.setPrevNextListeners(v -> {
            if (vdvVideoPlayer.canSeekForward()) {
                checkReplayButtonVisibility();
                vdvVideoPlayer.setVideoPath(String.valueOf(mainActivityViewModel.getVideoFiles().getValue().get(position = position + 1)));
                vdvVideoPlayer.start();
            }
        }, v -> {
            if (vdvVideoPlayer.canSeekBackward()) {
                checkReplayButtonVisibility();
                vdvVideoPlayer.setVideoPath(String.valueOf(mainActivityViewModel.getVideoFiles().getValue().get(position = position - 1)));
                vdvVideoPlayer.start();
            }
        });


        imbRepeat.setOnClickListener(v -> {
            imbRepeat.setVisibility(GONE);
            vdvVideoPlayer.start();
        });
    }

    //checks if replay button is visible
    private void checkReplayButtonVisibility() {
        if (imbRepeat.isShown())
            imbRepeat.setVisibility(GONE);
    }

    //stop video player on pressing back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vdvVideoPlayer.stopPlayback();
    }
}
