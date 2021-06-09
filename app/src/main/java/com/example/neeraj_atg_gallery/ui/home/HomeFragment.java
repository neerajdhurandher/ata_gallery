package com.example.neeraj_atg_gallery.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neeraj_atg_gallery.AdapterPhotos;
import com.example.neeraj_atg_gallery.MainActivity;
import com.example.neeraj_atg_gallery.R;
import com.example.neeraj_atg_gallery.models.Photo;
import com.example.neeraj_atg_gallery.models.Photos;
import com.example.neeraj_atg_gallery.models.Photos__1;
import com.example.neeraj_atg_gallery.viewmodel.mainActivity_vm;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;

    AdapterPhotos adapterPhotos;

    RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        mainActivity_vm  mainActivityVm = new ViewModelProvider(this).get(mainActivity_vm.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        recyclerview = root.findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerview.setLayoutManager(new LinearLayoutManager( getActivity() ) ) ;

        homeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<Photo>>() {
            @Override
            public void onChanged(@Nullable List<Photo> list) {

                Log.d("dolly",list.get(0).getTitle());
                adapterPhotos =  new AdapterPhotos(getActivity(),list);
                recyclerView.setAdapter(adapterPhotos);
            }
        });

        homeViewModel.getOb().observe(getViewLifecycleOwner(), new Observer<Observer<Photos>>() {
            @Override
            public void onChanged(Observer<Photos> photosObserver) {

                mainActivityVm.forMutableLiveData.observe(getViewLifecycleOwner(),photosObserver);
            }
        });


        return root;
    }
}