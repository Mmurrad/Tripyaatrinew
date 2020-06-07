package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.PlaceDetails;

import java.util.List;

public class StateCustomAdapter extends ArrayAdapter<PlaceDetails> {
    private Activity context;
    private List<PlaceDetails> placeDetails;

    public StateCustomAdapter(@NonNull Activity context, List<PlaceDetails> placeDetails) {
        super(context, R.layout.sample_of_statelist,placeDetails);
        this.context=context;
        this.placeDetails=placeDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_statelist,null,true);

        TextView state_name=view.findViewById(R.id.sample_state_name_id);
        ImageView image =  view.findViewById(R.id.image_view);


        //TextView area=view.findViewById(R.id.sample_area_id);

        PlaceDetails details=placeDetails.get(position);
        state_name.setText(details.getState_name());
        Log.d("check",details.getState_name().substring(0,1));

        if (details.getState_name().substring(0,1).equals("A")||details.getState_name().substring(0,1).equals("a")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("A", Color.BLUE);

            image.setImageDrawable(drawable);
        }
        if (details.getState_name().substring(0,1).equals("B")||details.getState_name().substring(0,1).equals("b")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("B", Color.YELLOW);


            image.setImageDrawable(drawable);
        }
        if (details.getState_name().substring(0,1).equals("C")||details.getState_name().substring(0,1).equals("c")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("C", Color.GREEN);


            image.setImageDrawable(drawable);
        }
        if (details.getState_name().substring(0,1).equals("D")||details.getState_name().substring(0,1).equals("d")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("D", Color.RED);

            image.setImageDrawable(drawable);
        }
        //start
        if (details.getState_name().substring(0,1).equals("E")||details.getState_name().substring(0,1).equals("e")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("E", Color.YELLOW);

            image.setImageDrawable(drawable);
        }
        if (details.getState_name().substring(0,1).equals("F")||details.getState_name().substring(0,1).equals("f")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("F", Color.CYAN);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("G")||details.getState_name().substring(0,1).equals("g")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("G", Color.LTGRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("H")||details.getState_name().substring(0,1).equals("h")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("H", Color.DKGRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("I")||details.getState_name().substring(0,1).equals("i")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("I", Color.GREEN);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("J")||details.getState_name().substring(0,1).equals("j")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("J", Color.GRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("K")||details.getState_name().substring(0,1).equals("k")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("K", Color.BLUE);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("L")||details.getState_name().substring(0,1).equals("l")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("L", Color.YELLOW);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("M")||details.getState_name().substring(0,1).equals("m")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("M", Color.BLUE);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("N")||details.getState_name().substring(0,1).equals("n")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("N", Color.RED);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("O")||details.getState_name().substring(0,1).equals("o")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("O", Color.GRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("P")||details.getState_name().substring(0,1).equals("p")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("P", Color.GREEN);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("Q")||details.getState_name().substring(0,1).equals("q")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Q", Color.BLUE);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("R")||details.getState_name().substring(0,1).equals("r")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("R", Color.DKGRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("S")||details.getState_name().substring(0,1).equals("s")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("S", Color.YELLOW);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("T")||details.getState_name().substring(0,1).equals("t")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("T", Color.RED);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("U")||details.getState_name().substring(0,1).equals("u")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("U", Color.MAGENTA);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("V")||details.getState_name().substring(0,1).equals("v")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("V", Color.BLUE);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("W")||details.getState_name().substring(0,1).equals("w")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("W", Color.DKGRAY);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("X")||details.getState_name().substring(0,1).equals("x")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("X", Color.YELLOW);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("Y")||details.getState_name().substring(0,1).equals("y")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Y", Color.GREEN);

            image.setImageDrawable(drawable);
        }

        if (details.getState_name().substring(0,1).equals("Z")||details.getState_name().substring(0,1).equals("z")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Z", Color.GRAY);

            image.setImageDrawable(drawable);
        }


        return view;
    }
}
