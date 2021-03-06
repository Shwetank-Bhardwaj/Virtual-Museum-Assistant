package com.shwetank.libraryassistant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shwetank.libraryassistant.glide.GlideApp;
import com.shwetank.libraryassistant.model.Art;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ArtViewHolder> {

    private final Context mContext;
    private ArrayList<Art> mArtList;

    public MainAdapter(Context context, ArrayList<Art> artList) {
        this.mContext = context;
        this.mArtList = artList;
    }

    @NonNull
    @Override
    public ArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.art_layout, parent, false);
        return new ArtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtViewHolder holder, int position) {
        Art art = mArtList.get(position);
        holder.setDetails(mContext, art);
    }

    @Override
    public int getItemCount() {
        return mArtList.size();
    }

    class ArtViewHolder extends RecyclerView.ViewHolder {

        private TextView artName;
        private TextView artistName;
        private ImageView artImageView;

        public ArtViewHolder(@NonNull View itemView) {
            super(itemView);
            artName = itemView.findViewById(R.id.art_name);
            artistName = itemView.findViewById(R.id.artist_name);
            artImageView = itemView.findViewById(R.id.art_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openArtActivity(mArtList.get(getAdapterPosition()));
                }
            });
        }

        public void setDetails(final Context context, Art art) {
            artName.setText(art.getArtworkName());
            artistName.setText(art.getArtistName());
            GlideApp.with(context)
                    .load(art.getArtworkImageUrl())
                    .centerCrop()
                    .into(artImageView);
        }

        private void openArtActivity(Art art) {
            Intent i = new Intent(mContext.getApplicationContext(), ArtActivity.class);
            i.putExtra("data", art);
            mContext.startActivity(i);
        }

    }
}

