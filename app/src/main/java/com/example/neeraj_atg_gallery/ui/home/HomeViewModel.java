package com.example.neeraj_atg_gallery.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.neeraj_atg_gallery.models.Photo;
import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.viewmodel.mainActivity_vm;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Photo> > list;

    private MutableLiveData< Observer<Photos> > ob;

    public  Observer<Photos> observer;

    public HomeViewModel() {
        list = new MutableLiveData<>();
        Log.d("service_check","working");

          observer = new Observer<Photos>() {
            @Override
            public void onChanged(Photos Photos) {

                list.setValue(Photos.getPhotos().getPhoto());

            }

        };


        ob.setValue(observer);

    }

    public LiveData<List<Photo> > getList() {
        ob.setValue(observer);
        return list;
    }

    public  LiveData< Observer<Photos>> getOb(){
        return ob;
    }
}