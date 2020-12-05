package com.example.instragramclone;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfilePostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile, parent, false);
        // View view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // public ImageView rvPost;
        private ImageView ivPposts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPposts = itemView.findViewById(R.id.ivPposts);
            ivPposts.setImageResource(R.drawable.arrow);
        }

        public void bind(Post post) {
            ivPposts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goUserDetailActivity(post);
                }
            });
        }
    }

    private void goUserDetailActivity(Post post) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("image", post.getImage().getUrl());
        context.startActivity(intent);
    }
}

