package miq0717.videoplayer.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import miq0717.videoplayer.R;


public class VideoFileAdapter extends RecyclerView.Adapter<VideoFileAdapter.VideoFileViewHolder> {

    private Context context;
    private LiveData<ArrayList<File>> videoArrayList;
    private LiveData<ArrayList<Bitmap>> videoThumbNails;

    public VideoFileAdapter(Context context, LiveData<ArrayList<File>> videoArrayList, LiveData<ArrayList<Bitmap>> videoThumbNails) {
        this.context = context;
        this.videoArrayList = videoArrayList;
        this.videoThumbNails = videoThumbNails;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public VideoFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_row, parent, false);
        return new VideoFileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoFileViewHolder videoFileHolder, int position) {
        videoFileHolder.txvVideoFileName.setText(videoArrayList.getValue().get(position).getName());
        videoFileHolder.imvVideoThumbnail.setImageBitmap(videoThumbNails.getValue().get(position));
        videoFileHolder.crvVideoHolder.setOnClickListener(v -> {
            Intent videoIntent = new Intent(context, VideoPlayerActivity.class);
            videoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            videoIntent.putExtra("position", videoFileHolder.getAdapterPosition());
            context.startActivity(videoIntent);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (videoArrayList.getValue() != null && videoArrayList.getValue().size() > 0)
            return videoArrayList.getValue().size();
        return 1;
    }

    static class VideoFileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txvVideoFileName)
        TextView txvVideoFileName;
        @BindView(R.id.imvVideoThumbnail)
        ImageView imvVideoThumbnail;
        @BindView(R.id.crvVideoHolder)
        CardView crvVideoHolder;

        VideoFileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}


