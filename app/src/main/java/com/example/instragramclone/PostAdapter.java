package com.example.instragramclone;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
        //holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private TextView tvDescription;
        private ImageView ivImg;
        private TextView tvTime;
        private ImageView ivHeart;
        private ImageView ivComment;
        private ImageView ivSave;
        private TextView tvTotalLikes;
        private TextView tvTotalComments;
        private ImageView ivProfyl;
        private TextView lvViewComments;
        // private ListView lvViewComments;
        // private TextView etShowComments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImg = itemView.findViewById(R.id.ivImg);
            ivHeart = itemView.findViewById(R.id.ivHeart);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTotalLikes = itemView.findViewById(R.id.tvTotalLikes);
            ivProfyl = itemView.findViewById(R.id.ivProfyl);
            lvViewComments = itemView.findViewById(R.id.lvViewComments);
            //  etShowComments = itemView.findViewById(R.id.etShowComments);
            // tvTotalComments = itemView.findViewById(R.id.etTotalComments);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvTime.setText(post.getRelativeTimeAgo(post.getCreatedAt().toString()));
//          tvTotalLikes.setText(Integer.toString(post.getNumLikes()));
            ivComment = itemView.findViewById(R.id.ivComment);

            //post.setNumComments("hellllll");

            lvViewComments.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }});

            //tvTotalComments.setText(Integer.toString(post.getNumComments()));

            //  Glide.with(context).load(post.getProImage().getUrl()).into(ivProfyl);


            /*ivComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext(), CommentActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });*/

            ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailActivity(post);
                }
            });

            if (post.isLiked()){
                ivHeart.setImageResource(R.drawable.ufi_heart_active);
                tvTotalLikes.setText(Integer.toString(post.getNumLikes()));
            }

            ivHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!post.isLiked()){
                        post.likePost(ParseUser.getCurrentUser());
                        //ivHeart.setImageResource(R.drawable.ufi_red_heart_filled);
                        ivHeart.setImageResource(R.drawable.ufi_heart_active);
                        tvTotalLikes.setText(Integer.toString(post.getNumLikes()));
                        post.saveInBackground();
                    }
                    else {
                        post.unlikePost(ParseUser.getCurrentUser());
                        ivHeart.setImageResource(R.drawable.ufi_heart);
                        // ivHeart.setColorFilter(R.color.black);
                        tvTotalLikes.setText(Integer.toString(post.getNumLikes()));
                        post.saveInBackground();
                    }
                }
            });

            ParseFile image = post.getImage();
            if (image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImg);
            }
        }
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    private void goToDetailActivity(Post post) {
        //Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("user",post.getUser().getUsername());
        intent.putExtra("image",post.getImage().getUrl());
        intent.putExtra("heart",post.isLiked());
        intent.putExtra("like",Integer.toString(post.getNumLikes()));
        intent.putExtra("description",post.getDescription());
        intent.putExtra("time",post.getRelativeTimeAgo(post.getCreatedAt().toString()));
        context.startActivity(intent);
    }
}