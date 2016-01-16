package com.example.jeffrytandiono.whackomole;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainFragment extends Fragment {
    private ImageView play;
    private TextView highScore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        play = (ImageView)v.findViewById(R.id.play);
        highScore = (TextView)v.findViewById(R.id.highScoreView);
        try {
            GameLib.get(getActivity()).loadHighScore();
        } catch (Exception e) {
            GameLib.setHighscore(0);
            Log.e("Error", "Error loading highscore: ", e);
        }
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GameActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        LibraryClass.removeStatusBar(getActivity());
        highScore.setText("High Score : " + String.valueOf(GameLib.getHighscore()));
    }
}
