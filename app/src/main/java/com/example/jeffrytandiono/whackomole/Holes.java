package com.example.jeffrytandiono.whackomole;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Jeffrytandiono on 12/18/15.
 */
public class Holes {
    private ImageView hole;
    private boolean hitable;
//    ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
//    Future runningFuture;
    private Runnable r;
    private Handler handler;
    private Random rand;
    private int duration;
    private GameFragment frag;
    private MediaPlayer mpHit, mpFailHit;

    public void failHit() {
        mpFailHit.start();
        if (GameLib.getHeart() == 1) {
            GameLib.getHeartCurrent().setBackgroundResource(R.drawable.heart_empty);
            GameLib.setLose(true);
            if (GameLib.getHighscore() < GameLib.getScore()) {
                GameLib.setHighscore(GameLib.getScore());
                GameLib.get(frag.getActivity()).saveHighScore();
            }
            FragmentManager fm = frag.getActivity().getSupportFragmentManager();
            DialogLoseFragment dialog = DialogLoseFragment.newInstance();
            dialog.setTargetFragment(frag, 0);
            dialog.show(fm, "Lose");
        } else {
            GameLib.getHeartCurrent().setBackgroundResource(R.drawable.heart_empty);
            GameLib.setHeart(GameLib.getHeart() - 1);
//            Toast.makeText(frag.getActivity(), String.valueOf(GameLib.getHeart()), Toast.LENGTH_LONG).show();
        }
        GameLib.setNowMole(GameLib.getNowMole() - 1);
    }

    public void successHit() {
        mpHit.start();
        GameLib.setScore(GameLib.getScore() + 1);
        GameLib.getScoreView().setText(String.valueOf(GameLib.getScore()));
        GameLib.setNowMole(GameLib.getNowMole() - 1);
        if (GameLib.getScore()%25 == 0) {
            GameLib.setLevel(GameLib.getLevel()+1);
        }
//        if (GameLib.getScore() == 25) {
//            GameLib.setLevel(2);
//        } else if (GameLib.getScore() == 50) {
//            GameLib.setLevel(3);
//        } else if (GameLib.getScore() == 75) {
//            GameLib.setLevel(4);
//        } else if (GameLib.getScore() == 100) {
//            GameLib.setLevel(5);
//        } else if (GameLib.getScore() == 125) {
//            GameLib.setLevel(6);
//        } else if (GameLib.getScore() == 150) {
//            GameLib.setLevel(7);
//        } else if (GameLib.getScore() == 175) {
//            GameLib.setLevel(8);
//        } else if (GameLib.getScore() == 200) {
//            GameLib.setLevel(9);
//        } else if (GameLib.getScore() == 225) {
//            GameLib.setLevel(10);
//        } else if (GameLib.getScore() == 250) {
//            GameLib.setLevel(11);
//        } else if (GameLib.getScore() == 275) {
//            GameLib.setLevel(12);
//        } else if (GameLib.getScore() == 300) {
//            GameLib.setLevel(13);
//        } else if (GameLib.getScore() == 325) {
//            GameLib.setLevel(14);
//        } else if (GameLib.getScore() == 350) {
//            GameLib.setLevel(15);
//        } else if (GameLib.getScore() == 375) {
//            GameLib.setLevel(16);
//        } else if (GameLib.getScore() == 400) {
//            GameLib.setLevel(17);
//        } else if (GameLib.getScore() == 425) {
//            GameLib.setLevel(18);
//        } else if (GameLib.getScore() == 450) {
//            GameLib.setLevel(19);
//        } else if (GameLib.getScore() == 475) {
//            GameLib.setLevel(20);
//        } else if (GameLib.getScore() == 500) {
//            GameLib.setLevel(21);
//        } else if (GameLib.getScore() == 525) {
//            GameLib.setLevel(22);
//        } else if (GameLib.getScore() == 550) {
//            GameLib.setLevel(23);
//        } else if (GameLib.getScore() == 575) {
//            GameLib.setLevel(24);
//        } else if (GameLib.getScore() == 600) {
//            GameLib.setLevel(25);
//        }
    }

    public Holes(ImageView iv, final GameFragment frag) {
        this.frag = frag;
        hole = iv;
        hole.setBackgroundResource(R.drawable.hole);
        mpHit = MediaPlayer.create(frag.getActivity(), R.raw.punch_or_whack);
        mpFailHit = MediaPlayer.create(frag.getActivity(), R.raw.banana_peel_slip);
        hole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!GameLib.isLose()) {
                    if (hitable) {
                        hole.setBackgroundResource(R.drawable.hole);
                        hitable = false;
                        successHit();
                    } else {
                        //region Kalau hit lobang yang kosong dihitung fail
                        failHit();
                        //endregion
                    }
                }
            }
        });
        running();
    }

//    public void stopIterate() {
//        handler.removeCallbacksAndMessages(r);
//        runningFuture.cancel(true);
//    }

    public void running() {
        handler = new Handler();
        rand = new Random();
        duration = rand.nextInt(GameLib.getMaxDuration() - GameLib.getMinDuration() + 1) + GameLib.getMinDuration();

        r = new Runnable() {
            @Override
            public void run() {
                duration = rand.nextInt(GameLib.getMaxDuration() - GameLib.getMinDuration() + 1) + GameLib.getMinDuration();
                if (!GameLib.isLose()) {
                    //region Pengecekan apabila tidak hit mole yang keluar maka dihitung fail
                    if (hitable) {
                        failHit();
                    }
                    //endregion
                    if (rand.nextBoolean() && GameLib.getNowMole() < GameLib.getMaxMole()) {
                        GameLib.setNowMole(GameLib.getNowMole() + 1);
                        hole.setBackgroundResource(R.drawable.mole);
                        hitable = true;
                    } else {
                        hole.setBackgroundResource(R.drawable.hole);
                        hitable = false;
                    }
                    handler.postDelayed(this, duration);
                }
            }
        };
//        runningFuture = threadPoolExecutor.submit(r);
//        runningFuture.cancel(true);
        handler.postDelayed(r, duration);
    }

    public void removeRunnable() {
        handler.removeCallbacks(r);
    }
}
