package com.mrvilkaman.neuralnetwork.presentationlayer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static void toast(Context context, String text) {
        if (context != null) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(Context context, int textId) {
        if (context != null) {
            Toast.makeText(context, textId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showAlert(Context context, int resId) {
        if (context == null) {
            return;
        }
        showAlert(context, context.getString(resId));
    }

    public static void showAlert(Context context, String resStr) {
        if (context == null) {
            return;
        }

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(resStr)
                .positiveText(android.R.string.ok)
                .show();
    }

    public static String asString(EditText editText) {
        return editText.getText().toString();
    }

    public static void showAlert(Context context, int text, Runnable callback) {
        if (context == null) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(text)
                .positiveText(android.R.string.ok)
                .dismissListener(dialog -> callback.run())
                .show();
    }

    public static void showAlert(Context context, String text, Runnable callback) {
        if (context == null) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(text)
                .positiveText(android.R.string.ok)
                .dismissListener(dialog -> callback.run())
                .show();
    }

    public static boolean isConnectionEnable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if ((networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable() && networkInfo.isConnectedOrConnecting()))
            return true;
        return false;
    }

    public boolean isConnectionErrorsToServer() {
        final Boolean[] checker = {false};
        return false;
    }

}
