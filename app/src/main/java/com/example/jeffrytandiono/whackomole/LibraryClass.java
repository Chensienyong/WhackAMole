package com.example.jeffrytandiono.whackomole;

import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jeffrytandiono on 12/13/15.
 */
public class LibraryClass {
    public static void removeStatusBar(FragmentActivity fa) {
        View decorView = fa.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
