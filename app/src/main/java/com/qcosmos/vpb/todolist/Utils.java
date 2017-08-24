package com.qcosmos.vpb.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by vpb on 8/22/17.
 */

public final class Utils {
    public static void showKeyboard(View view, Activity activity) {
        InputMethodManager imm =(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
    }

    public static void dismissKeyboard(View view, Activity activity) {
        InputMethodManager imm =(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String capitalizeString(String str) {
        if(str != null && !str.trim().isEmpty()) {
            if(str.length() > 1) {
                str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
            } else {
                str = str.toUpperCase();
            }
        }
        return str;
    }
}
