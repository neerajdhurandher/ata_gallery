package com.example.neeraj_atg_gallery;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.neeraj_atg_gallery.models.Photo;

import java.util.List;

import retrofit2.http.Url;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.photoViewHolder> {


    Context context;
    List<Photo> photoList;

    public AdapterPhotos(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public photoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.photo_blueprint, viewGroup,false);
        return new photoViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull photoViewHolder holder, int i) {

        if(i == photoList.size() -2 ){


        }


        String photoUrl = photoList.get(i).getUrlS();
        String title = photoList.get(i).getTitle();

        try {
            Glide.with(context).load(photoUrl).circleCrop().into(holder.showphoto);
        }
        catch (Exception e){
            Glide.with(context).load(R.drawable.ic_photo_asset_foreground).circleCrop().into(holder.showphoto);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clicked_photo = new Intent( context,Photo_Details_Activity.class);

                context.startActivity(clicked_photo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class photoViewHolder extends RecyclerView.ViewHolder{

        ImageView showphoto;

        public photoViewHolder(@NonNull View itemView) {
            super(itemView);

            showphoto = itemView.findViewById(R.id.imageView);
        }
    }


}
