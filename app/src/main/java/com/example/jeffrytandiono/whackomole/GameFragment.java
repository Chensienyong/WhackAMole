package com.example.jeffrytandiono.whackomole;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


public class GameFragment extends Fragment {
    private ArrayList<Holes> holes = new ArrayList<>();
    private ImageView exit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public void endGame() {
//        for (Holes hole : holes) {
//            hole.stopIterate();
//        }
//    }

    public void starterPack() {
        GameLib.setHeart(3);
        GameLib.setScore(0);
        GameLib.setLose(false);
        GameLib.setLevel(1);
        GameLib.setMaxDuration(5000);
        GameLib.setMinDuration(2000);
        GameLib.setMaxMole(2);
        GameLib.setNowMole(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        GameLib.setScoreView((TextView) v.findViewById(R.id.scoreView));
        GameLib.setHeartView1((ImageView) v.findViewById(R.id.heart1));
        GameLib.setHeartView2((ImageView) v.findViewById(R.id.heart2));
        GameLib.setHeartView3((ImageView) v.findViewById(R.id.heart3));
        GameLib.setLevelView((TextView) v.findViewById(R.id.level));
        starterPack();
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole1), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole2), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole3), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole4), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole5), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole6), GameFragment.this));
        holes.add(new Holes((ImageView)v.findViewById(R.id.hole7), GameFragment.this));
        holes.add(new Holes((ImageView) v.findViewById(R.id.hole8), GameFragment.this));
        holes.add(new Holes((ImageView) v.findViewById(R.id.hole9), GameFragment.this));
        holes.add(new Holes((ImageView) v.findViewById(R.id.hole10), GameFragment.this));
        holes.add(new Holes((ImageView) v.findViewById(R.id.hole11), GameFragment.this));
        holes.add(new Holes((ImageView) v.findViewById(R.id.hole12), GameFragment.this));
        exit = (ImageView)v.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }

    public void pauseGame() {
        for(Holes hole : holes) {
            hole.removeRunnable();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LibraryClass.removeStatusBar(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pauseGame();
    }
}
