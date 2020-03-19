package miq0717.videoplayer.repositories;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;

/*Created by MiQ0717 on 19-Mar-2020.*/
public class VideoRepository {

    private static VideoRepository mInstance;
    private ArrayList<File> videoFiles = new ArrayList<>();
    private ArrayList<Bitmap> videoThumbNails = new ArrayList<>();

    //Create video repository instance
    public static VideoRepository getInstance() {
        if (mInstance == null) {
            mInstance = new VideoRepository();
        }
        return mInstance;
    }

    //Get video Files into Mutable Live Data
    public MutableLiveData<ArrayList<File>> getVideoFiles() {
        File directory = new File("/mnt/");
        videoFiles = getVideoFiles(directory);

        MutableLiveData<ArrayList<File>> data = new MutableLiveData<>();
        data.setValue(videoFiles);
        return data;
    }

    //Read video files from internal storage and SD card
    public ArrayList<File> getVideoFiles(File directory) {
        File[] listFile = directory.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getVideoFiles(listFile[i]);
                } else {
                    boolean permission = false;
                    if (listFile[i].getName().endsWith(".mp4")) {
                        for (int j = 0; j < videoFiles.size(); j++) {
                            if (videoFiles.get(j).getName().equals(listFile[i].getName())) {
                                permission = true;
                            }
                        }
                        if (permission)
                            permission = false;
                        else
                            videoFiles.add(listFile[i]);
                    }
                }
            }
        }
        return videoFiles;
    }

    //Gets video thumbnails separately to reduce lagging in adapter's recyclerview
    public MutableLiveData<ArrayList<Bitmap>> getVideoThumbNails() {
        for (File aVideoFile :
                videoFiles) {
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(aVideoFile.getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
            videoThumbNails.add(bitmap);
        }
        MutableLiveData<ArrayList<Bitmap>> thumbnails = new MutableLiveData<>();
        thumbnails.setValue(videoThumbNails);
        return thumbnails;
    }
}
