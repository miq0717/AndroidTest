package miq0717.videoplayer.viewModel;


import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;

import miq0717.videoplayer.repositories.VideoRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<File>> videoFiles;
    private MutableLiveData<ArrayList<Bitmap>> videoThumbnails;
    private VideoRepository videoRepository;

    //initialize repository, videoFiles, videoThumbNails
    public void init() {
//        Log.e("MainActivityViewModel", "INIT Called");
        if (videoFiles != null)
            return;
        videoRepository = VideoRepository.getInstance();
        videoFiles = videoRepository.getVideoFiles();
//        Log.e("MainActivityViewModel", "Video Files Size" + videoFiles.getValue().size() + "");
        videoThumbnails = videoRepository.getVideoThumbNails();
    }

    //gets Video Files
    public LiveData<ArrayList<File>> getVideoFiles() {
        return videoFiles;
    }

    //gets Video Thumbnails
    public LiveData<ArrayList<Bitmap>> getVideoThumbnails() {
        return videoThumbnails;
    }
}
