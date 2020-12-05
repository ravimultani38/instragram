package com.example.instragramclone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.instragramclone.LoginActivity;
import com.example.instragramclone.Post;
import com.example.instragramclone.PostAdapter;
import com.example.instragramclone.ProfilePostAdapter;
import com.example.instragramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends PostsFragment {

    public static final String TAG = "ProfileFragment";
    private static final int NUM_GRID_COL = 3;

    private Button btnEditProfile;
    private RecyclerView rvPost;
    protected List<Post> allPosts;
    private ProfilePostAdapter adapter;
    //private Toolbar toolbar;
    //private EndlessScrollListener scrollListener;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEditActivity();
                // Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
            }
        });

        rvPost = view.findViewById(R.id.rvPost);
        allPosts = new ArrayList<>();
        adapter = new ProfilePostAdapter(getContext(), allPosts);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),3);

        rvPost.setLayoutManager(linearLayoutManager);
        rvPost.setAdapter(adapter);
        queryPosts();
    }

    @Override
    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e !=null ) {
                    Log.e(TAG, "issue with getting post", e);
                    return;
                }

                for( Post post : posts ){
                    //Log.i(TAG, "Post:" + post.getDescription() + ", username:" + post.getUser().getUsername());
                }

                allPosts.addAll(posts);
                //adapter.notifyItemInserted(posts.size()-1);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void goEditActivity() {
        Intent i = new Intent(getContext(), EditActivity.class);
        startActivity(i);
    }
}