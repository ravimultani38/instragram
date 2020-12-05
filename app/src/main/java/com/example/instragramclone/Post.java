package com.example.instragramclone;

import android.text.format.DateUtils;
import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

@ParseClassName("Post")
public class Post extends ParseObject implements Serializable {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE ="image";
    public static final String KEY_USER="user";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_LIKED_BY = "likedBy";

    public String getDescription(){
        return getString(KEY_DESCRIPTION);

    }

    public void setDescription(String description){

        put(KEY_DESCRIPTION, description);
    }
    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);

    }
    public void setImage(ParseFile parseFile){

        put(KEY_IMAGE, parseFile);
    }

    public JSONArray getLikes() {
        return getJSONArray(KEY_LIKED_BY);
    }

    public int getNumLikes() {
        return getLikes().length();
    }

    public void likePost(ParseUser user) {
        add(KEY_LIKED_BY, user);

    }
    public void unlikePost(ParseUser user) {
        ArrayList<ParseUser> a = new ArrayList<>();
        a.add(user);
        removeAll(KEY_LIKED_BY, a);
    }
    public boolean isLiked() {
        JSONArray a = getLikes();
        if(a != null) {
            for (int i = 0; i < a.length(); i++) {
                try {
                    if (a.getJSONObject(i).getString("objectId").equals(ParseUser.getCurrentUser().getObjectId())) {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public ParseUser getUser(){

        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }
    public String getRelativeTimeAgo(String parseDate) {
        String dateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(parseDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}



