package com.example.neeraj_atg_gallery.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.models.Photos__1;
import com.example.neeraj_atg_gallery.repositry.mainActivity_repo;

import java.util.List;

public class mainActivity_vm extends AndroidViewModel {

    public MutableLiveData<Photos> forMutableLiveData;
    public MutableLiveData<List<Photos>> changePhotos;

    public mainActivity_vm(@NonNull Application application) {

        super(application);
        mainActivity_repo mainActivityRepo = new mainActivity_repo();
        forMutableLiveData = mainActivityRepo.forMutableLiveData;
        changePhotos = mainActivityRepo.changePageMutable;
    }
}
