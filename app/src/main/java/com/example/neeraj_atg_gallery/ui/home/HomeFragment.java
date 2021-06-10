package com.example.neeraj_atg_gallery.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neeraj_atg_gallery.AdapterPhotos;
import com.example.neeraj_atg_gallery.MainActivity;
import com.example.neeraj_atg_gallery.R;
import com.example.neeraj_atg_gallery.models.Photo;
import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.models.Photos__1;
import com.example.neeraj_atg_gallery.restApiHelper.RestApiHelp;
import com.example.neeraj_atg_gallery.viewmodel.mainActivity_vm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    AdapterPhotos adapterPhotos;

    RecyclerView recyclerview;
    public Button btn_prev, btn_next;
    TextView pg_txt;
    Photos__1 photo__1 ;
    int total_pages = 1;
    int current_page = 1;

    private RestApiHelp restApiHelp;

    List<Photo> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        restApiHelp = RestApiHelp.retrofit.create(RestApiHelp.class);

        mainActivity_vm  mainActivityVm = new ViewModelProvider(HomeFragment.this).get(mainActivity_vm.class);



        recyclerview = root.findViewById(R.id.recyclerview);
        btn_next = root.findViewById(R.id.btn_next);
        btn_prev = root.findViewById(R.id.btn_prev);
        pg_txt = root.findViewById(R.id.pg_no_text);


        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        Observer<Photos> observer = new Observer<Photos>() {
            @Override
            public void onChanged(Photos Photos) {

                if(Photos.getPhotos().getPhoto() != null) {

                   Photos.getPhotos().setPage(1);
                     photo__1 = Photos.getPhotos();



                      total_pages =  Photos.getPhotos().getPages();

                       photo__1.setPage(current_page);


                    list = Photos.getPhotos().getPhoto();

                    Log.d("page_num" , " is total in home frag "+ Photos.getPhotos().getTotal() );
                    Log.d("page_num" , " is pages in home frag "+ Photos.getPhotos().getPages() );

                    adapterPhotos = new AdapterPhotos(getActivity(), list);
                    recyclerview.setAdapter(adapterPhotos);

                }
            }

        };


        pg_txt.setText(String.valueOf(current_page) );


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_page > 0 && current_page < 50 && photo__1 != null) {
                    current_page++;

                    Log.d("page_num" , current_page + " next clicked");

                    Call<Photos> changeNext = restApiHelp.changePage(current_page);

                    changeNext.enqueue(new Callback<Photos>() {
                        @Override
                        public void onResponse(Call<Photos> call, Response<Photos> response) {
                            if(response.isSuccessful()){

                                Log.d("page_num", "onResponse: " + response.body());

                                Photos photos = response.body();

                                List<Photo> photoList = photos.getPhotos().getPhoto();

                                adapterPhotos = new AdapterPhotos(getActivity(), photoList);
                                recyclerview.setAdapter(adapterPhotos);
                                pg_txt.setText(String.valueOf(current_page) );

                            }
                        }

                        @Override
                        public void onFailure(Call<Photos> call, Throwable t) {
                            Log.d("page_num", "error: "+t);
                        }
                    });

                }

            }


        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_page > 1 && current_page < 51 && photo__1 != null) {
                    current_page--;

                    Log.d("page_num" , current_page+" prev clicked");

                    Call<Photos> changeNext = restApiHelp.changePage(current_page);

                    changeNext.enqueue(new Callback<Photos>() {
                        @Override
                        public void onResponse(Call<Photos> call, Response<Photos> response) {
                            if(response.isSuccessful()){

                                Log.d("page_num", "onResponse: new data");

                                Photos photos = response.body();

                                List<Photo> photoList = photos.getPhotos().getPhoto();

                                adapterPhotos = new AdapterPhotos(getActivity(), photoList);
                                recyclerview.setAdapter(adapterPhotos);

                                pg_txt.setText(String.valueOf(current_page) );

                            }
                        }

                        @Override
                        public void onFailure(Call<Photos> call, Throwable t) {
                            Log.d("page_num", "error: "+t);
                        }
                    });

                }
            }
        });


        mainActivityVm.forMutableLiveData.observe(getViewLifecycleOwner(),observer);


        return root;
    }


}