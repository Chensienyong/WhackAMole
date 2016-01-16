package com.example.jeffrytandiono.whackomole;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Jeffrytandiono on 12/19/15.
 */
public class GameLib {
    private static GameLib gl;
    private static Context context;
    private static int heart;
    private static int score;
    private static int level;
    private static int maxDuration;
    private static int minDuration;
    private static int nowMole;
    private static int maxMole;
    private static int highscore;
    private static boolean lose = false;
    private static TextView scoreView, levelView;
    private static ImageView heartView1, heartView2, heartView3;
    private static final String TAG = "HighScore", FILENAME = "highscore.json";
    private HighScoreJSONSerializer hsSerializer;

    private GameLib(Context appContext) {
        context = appContext;
        hsSerializer = new HighScoreJSONSerializer(context, FILENAME);
    }

    public static GameLib get(Context context) {
        if (gl == null)
            gl = new GameLib(context.getApplicationContext());
        return gl;
    }

    public static ImageView getHeartCurrent() {
        if (heart == 3) {
            return heartView3;
        } else if (heart == 2) {
            return heartView2;
        } else {
            return heartView1;
        }
    }

    public static void setHeartView1(ImageView heartView1) {
        GameLib.heartView1 = heartView1;
        GameLib.heartView1.setBackgroundResource(R.drawable.heart_full);
    }

    public static void setHeartView2(ImageView heartView2) {
        GameLib.heartView2 = heartView2;
        GameLib.heartView2.setBackgroundResource(R.drawable.heart_full);
    }

    public static void setHeartView3(ImageView heartView3) {
        GameLib.heartView3 = heartView3;
        GameLib.heartView3.setBackgroundResource(R.drawable.heart_full);
    }

    public static TextView getScoreView() {
        return scoreView;
    }

    public static void setScoreView(TextView scoreView) {
        GameLib.scoreView = scoreView;
    }

    public static boolean isLose() {
        return lose;
    }

    public static void setLose(boolean lose) {
        GameLib.lose = lose;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameLib.score = score;
    }

    public static int getHeart() {
        return heart;
    }

    public static void setHeart(int heart) {
        GameLib.heart = heart;
    }

    public static void setLevel(int level) {
        GameLib.level = level;
        GameLib.getLevelView().setText("Level " + String.valueOf(level));
        if (getMaxDuration() != 1000) {
            setMaxDuration(getMaxDuration() - 200);
        }
        if (getMinDuration() != 1000) {
            setMinDuration(getMinDuration() - 20);
        }
        if (getMinDuration() > getMaxDuration()) {
            setMinDuration(getMaxDuration());
        }
        if (level == 4 || level == 8 || level == 12 || level == 16 || level == 20 || level > 20) {
            if (getMaxMole() != 12) {
                setMaxMole(getMaxMole() + 1);
            }
        }
    }

    public static int getLevel() {
        return level;
    }

    public static int getMaxDuration() {
        return maxDuration;
    }

    public static void setMaxDuration(int maxDuration) {
        GameLib.maxDuration = maxDuration;
    }

    public static int getMinDuration() {
        return minDuration;
    }

    public static void setMinDuration(int minDuration) {
        GameLib.minDuration = minDuration;
    }

    public static TextView getLevelView() {
        return levelView;
    }

    public static void setLevelView(TextView levelView) {
        GameLib.levelView = levelView;
    }

    public static int getMaxMole() {
        return maxMole;
    }

    public static void setMaxMole(int maxMole) {
        GameLib.maxMole = maxMole;
    }

    public static int getNowMole() {
        return nowMole;
    }

    public static void setNowMole(int nowMole) {
        GameLib.nowMole = nowMole;
    }

    public void loadHighScore() throws IOException, JSONException {
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            setHighscore(Integer.valueOf(jsonString.toString()));
        } catch (FileNotFoundException e) {
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public boolean saveHighScore() {
        try {
            hsSerializer.saveHighScore(GameLib.highscore);
            Log.d(TAG, "high score saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving high score : ", e);
            return false;
        }
    }

    public static void setHighscore(int highscore) {
        GameLib.highscore = highscore;
    }

    public static int getHighscore() {
        return highscore;
    }
}
