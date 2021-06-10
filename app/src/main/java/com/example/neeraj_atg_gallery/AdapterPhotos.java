package com.example.neeraj_atg_gallery;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.neeraj_atg_gallery.models.Photo;
import com.example.neeraj_atg_gallery.models.Photos;

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


//        if(photoList.get(i).getIsfamily() == 1) {
            String photoUrl1 = photoList.get(i).getUrlS();

            String title = photoList.get(i).getTitle();

            Log.d("photo_num", String.valueOf(i));


            try {
                Glide.with(context).load(photoUrl1).into(holder.showphoto1);
            } catch (Exception e) {
                Glide.with(context).load(R.drawable.ic_photo_asset_foreground).into(holder.showphoto1);
            }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clicked_photo = new Intent( context,Photo_Details_Activity.class);

                clicked_photo.putExtra("photo",photoUrl1);
                clicked_photo.putExtra("title",photoList.get(i).getTitle());
                clicked_photo.putExtra("owner",photoList.get(i).getOwner());
                clicked_photo.putExtra("id",photoList.get(i).getId());

                context.startActivity(clicked_photo);
            }
        });

//        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    class photoViewHolder extends RecyclerView.ViewHolder{

        ImageView showphoto1;

        public photoViewHolder(@NonNull View itemView) {
            super(itemView);

            showphoto1 = itemView.findViewById(R.id.imageView1);

        }
    }


}
