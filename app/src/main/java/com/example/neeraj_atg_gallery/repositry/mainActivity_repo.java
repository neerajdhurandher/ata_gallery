package com.example.neeraj_atg_gallery.repositry;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.models.Photos__1;
import com.example.neeraj_atg_gallery.restApiHelper.RestApiHelp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainActivity_repo {

    public mainActivity_repo() {

        getdatafromApi();

    }

    private void getdatafromApi() {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                restApiHelp = RestApiHelp.retrofit.create(RestApiHelp.class);

                Call<Photos> data_call = restApiHelp.data_from_api();

                data_call.enqueue(new Callback<Photos>() {
                    @Override
                    public void onResponse(Call<Photos> call, Response<Photos> response) {

                        Photos Photos = response.body();

                        forMutableLiveData.setValue(Photos);

                        Log.d("get_data" , "Successful" );

                    }

                    @Override
                    public void onFailure(Call<Photos> call, Throwable t) {

                        Log.d("service_check","on Failure "+t);
                    }
                });




            }
        });
    }

    private final Executor executor = Executors.newSingleThreadExecutor();
    private RestApiHelp restApiHelp;
    public MutableLiveData<Photos> forMutableLiveData = new MutableLiveData<>();
}
