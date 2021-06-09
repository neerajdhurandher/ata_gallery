package com.example.neeraj_atg_gallery.restApiHelper;

import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.models.Photos__1;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
public interface RestApiHelp {

    String BASE_URL = "https://api.flickr.com/services/rest/";
    String methord = "?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";




    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(methord)
    Call<Photos> data_from_api();
}
