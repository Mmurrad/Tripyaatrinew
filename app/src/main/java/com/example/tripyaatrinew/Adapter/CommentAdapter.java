package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.Comment;
import com.example.tripyaatrinew.model.PlaceDetails;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private Activity contex;
    private List<Comment> commentList;
    public CommentAdapter(@NonNull Activity context, List<Comment> commentList) {
        super(context, R.layout.sample_of_comment,commentList);
        this.contex=context;
        this.commentList=commentList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_comment,null,true);

        TextView comment=view.findViewById(R.id.comment_text_id);

        Comment details=commentList.get(position);
        comment.setText(details.getComment());

        return view;
    }
}
