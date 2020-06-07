package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tripyaatrinew.Adapter.CommentAdapter;
import com.example.tripyaatrinew.model.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    RatingBar ratingBar;
    EditText comment_text;
    Button save_button;
    ListView listView;
    float rating;
    List<Comment> commentList;
    String passkey;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        final Bundle bundle=getIntent().getExtras();
        passkey=bundle.getString("pass");
        ratingBar=findViewById(R.id.rating_id);
        comment_text=findViewById(R.id.comment_edittext_id);
        save_button=findViewById(R.id.comment_save_button_id);

        listView=findViewById(R.id.comment_listview_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("Comment");

        commentList=new ArrayList<>();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating=v;
                if(rating==1||rating==.5)
                {
                    Toast.makeText(getApplicationContext(),"Sorry",Toast.LENGTH_SHORT).show();
                }
                else if(rating==2||rating==1.5)
                {
                    Toast.makeText(getApplicationContext(),"You always accept suggestion",Toast.LENGTH_SHORT).show();
                }
                else if(rating==3||rating==2.5)
                {
                    Toast.makeText(getApplicationContext(),"Good Enough",Toast.LENGTH_SHORT).show();
                }
                else if(rating==4||rating==3.5)
                {
                    Toast.makeText(getApplicationContext(),"Great! Thank You",Toast.LENGTH_SHORT).show();
                }
                else if(rating==4.5||rating==5)
                {
                    Toast.makeText(getApplicationContext(),"Awesome ! You are the best!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No Rating",Toast.LENGTH_SHORT).show();
                }
            }
        });
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment=comment_text.getText().toString();

                Comment comment1=new Comment(rating,comment,passkey);
                databaseReference.push().setValue(comment1);

                if (databaseReference!=null)
                {
                    comment_text.setText("");
                }
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Comment comment=dataSnapshot1.getValue(Comment.class);
                    if(passkey.equals(comment.getPlace()))
                    {
                        commentList.add(comment);
                    }

                }
                try {
                    CommentAdapter commentAdapter=new CommentAdapter(CommentActivity.this,commentList);
                    listView.setAdapter(commentAdapter);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
