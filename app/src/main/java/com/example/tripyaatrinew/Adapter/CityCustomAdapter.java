package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

public class CityCustomAdapter extends ArrayAdapter<PlaceDetails> {
    private Activity contex;
    private List<PlaceDetails> placeDetails;
    public CityCustomAdapter(@NonNull Activity context, List<PlaceDetails> placeDetails) {
        super(context, R.layout.sample_of_city,placeDetails);
        this.contex=context;
        this.placeDetails=placeDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_city,null,true);

        TextView city=view.findViewById(R.id.sample_city_name_id);
        TextView address=view.findViewById(R.id.sample_address_id);
        ImageView imageView=view.findViewById(R.id.sample_city_image_id);
        PlaceDetails details=placeDetails.get(position);
        city.setText(details.getCity_name());
        address.setText(details.getAddress());

        if (details.getCity_name().substring(0,1).equals("S")||details.getState_name().substring(0,1).equals("s")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("S", Color.BLUE);

            imageView.setImageDrawable(drawable);
        }


        if (details.getCity_name().substring(0,1).equals("B")||details.getCity_name().substring(0,1).equals("b")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("B", Color.YELLOW);


            imageView.setImageDrawable(drawable);
        }
        if (details.getCity_name().substring(0,1).equals("C")||details.getCity_name().substring(0,1).equals("c")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("C", Color.GREEN);


            imageView.setImageDrawable(drawable);
        }
        if (details.getCity_name().substring(0,1).equals("D")||details.getCity_name().substring(0,1).equals("d")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("D", Color.RED);

            imageView.setImageDrawable(drawable);
        }
        //start
        if (details.getCity_name().substring(0,1).equals("E")||details.getCity_name().substring(0,1).equals("e")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("E", Color.GRAY);

            imageView.setImageDrawable(drawable);
        }
        if (details.getCity_name().substring(0,1).equals("F")||details.getCity_name().substring(0,1).equals("f")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("F", Color.MAGENTA);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("G")||details.getCity_name().substring(0,1).equals("g")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("G", Color.LTGRAY);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("H")||details.getCity_name().substring(0,1).equals("h")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("H", Color.DKGRAY);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("I")||details.getCity_name().substring(0,1).equals("i")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("I", Color.CYAN);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("J")||details.getCity_name().substring(0,1).equals("j")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("J", Color.WHITE);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("K")||details.getCity_name().substring(0,1).equals("k")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("K", Color.RED);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("L")||details.getCity_name().substring(0,1).equals("l")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("L", Color.YELLOW);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("M")||details.getCity_name().substring(0,1).equals("m")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("M", Color.BLUE);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("N")||details.getCity_name().substring(0,1).equals("n")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("N", Color.GREEN);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("O")||details.getCity_name().substring(0,1).equals("o")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("O", Color.MAGENTA);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("P")||details.getCity_name().substring(0,1).equals("p")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("P", Color.GRAY);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("Q")||details.getCity_name().substring(0,1).equals("q")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Q", Color.RED);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("R")||details.getCity_name().substring(0,1).equals("r")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("R", Color.YELLOW);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("S")||details.getCity_name().substring(0,1).equals("s")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("S", Color.DKGRAY);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("T")||details.getCity_name().substring(0,1).equals("t")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("T", Color.LTGRAY);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("U")||details.getCity_name().substring(0,1).equals("u")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("U", Color.BLUE);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("V")||details.getCity_name().substring(0,1).equals("v")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("V", Color.YELLOW);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("W")||details.getCity_name().substring(0,1).equals("w")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("W", Color.RED);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("X")||details.getCity_name().substring(0,1).equals("x")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("X", Color.BLUE);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("Y")||details.getCity_name().substring(0,1).equals("y")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Y", Color.BLACK);

            imageView.setImageDrawable(drawable);
        }

        if (details.getCity_name().substring(0,1).equals("Z")||details.getCity_name().substring(0,1).equals("z")){
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("Z", Color.MAGENTA);

            imageView.setImageDrawable(drawable);
        }


        return view;

    }
}
