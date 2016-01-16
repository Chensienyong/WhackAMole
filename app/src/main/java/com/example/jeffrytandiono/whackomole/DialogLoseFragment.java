package com.example.jeffrytandiono.whackomole;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jeffrytandiono on 12/28/15.
 */
public class DialogLoseFragment extends DialogFragment {
    public static DialogLoseFragment newInstance() {
        DialogLoseFragment frag = new DialogLoseFragment();
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity()
                .getLayoutInflater()
                .inflate(R.layout.dialog_layout, null);
        TextView level = (TextView)v.findViewById(R.id.levelNumberView);
        level.setText(String.valueOf(GameLib.getLevel()));
        TextView score = (TextView)v.findViewById(R.id.scoreNumberView);
        score.setText(String.valueOf(GameLib.getScore()));

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .create();
    }
}
