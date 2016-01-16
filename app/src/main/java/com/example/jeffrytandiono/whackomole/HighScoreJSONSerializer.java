package com.example.jeffrytandiono.whackomole;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by Jeffrytandiono on 12/27/15.
 */
public class HighScoreJSONSerializer {
    private Context context;
    private String fileName;

    public HighScoreJSONSerializer(Context c, String f) {
        context = c;
        fileName = f;
    }

    public void saveHighScore(int score) throws IOException {
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(String.valueOf(score));
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
