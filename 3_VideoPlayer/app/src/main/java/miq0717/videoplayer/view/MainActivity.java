package miq0717.videoplayer.view;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import miq0717.videoplayer.R;
import miq0717.videoplayer.viewModel.MainActivityViewModel;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcvVideoList)
    RecyclerView rcvVideoList;
    @BindView(R.id.pbrLoader)
    ProgressBar pbrLoader;

    private Context context;
    private MainActivityViewModel mainActivityViewModel;
    private VideoFileAdapter videoFileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ButterKnife.bind(this);
        rcvVideoList.setVisibility(GONE);
        pbrLoader.setVisibility(VISIBLE);
        //Initialize viewModel to get Videos through viewModel from Repository
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        setRecyclerViewControls();
        takePermissionForVideo();
    }

    //sets controls for recycler view
    private void setRecyclerViewControls() {
        rcvVideoList.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        rcvVideoList.setLayoutManager(gridLayoutManager);
    }

    //Checks for Storage permission of user
    private void takePermissionForVideo() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        getVideoFileAnSetAdapter();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(context, context.getString(R.string.allow_permission), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    //Sets permission to true, gets Video Files from Storage and Sets Adapter to RecyclerView
    private void getVideoFileAnSetAdapter() {
        mainActivityViewModel.init();
        videoFileAdapter = new VideoFileAdapter(getApplicationContext(), mainActivityViewModel.getVideoFiles(), mainActivityViewModel.getVideoThumbnails());
        rcvVideoList.setVisibility(VISIBLE);
        pbrLoader.setVisibility(GONE);
        rcvVideoList.setAdapter(videoFileAdapter);
    }
}
