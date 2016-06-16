package com.gooner10.ifactortest.util;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import com.gooner10.ifactortest.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gooner10 on 6/5/16.
 */
public class ViewUtils {

    Map<Context, AlertDialog> alertHolder;
    Map<Context, ProgressDialog> progressHolder;

    private static ViewUtils ourInstance = new ViewUtils();

    public static ViewUtils getInstance() {
        return ourInstance;
    }

    private ViewUtils() {
        alertHolder = new HashMap<Context, AlertDialog>();
        progressHolder = new HashMap<Context, ProgressDialog>();
    }

    /**
     * Shows alert - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     *
     * @param context
     * @param title
     * @param message
     */
    public void showAlert(Context context, String title, String message) {
        showAlert(context, title, message, null);
    }

    /**
     * Shows alert - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     *
     * @param context
     * @param title
     * @param message
     * @param onClickListener
     */
    public void showAlert(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
        // Checks if an alert already exists - if so it gets dismissed
        hideAlert(context);

        // Checks if a progress dialog already exists - if so it gets dismissed
        hideProgress(context);

        // Adds default click listener
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            };
        }

        // Creates, stores, and shows
        AlertDialog alert = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton("Okay", onClickListener).create();
        alertHolder.put(context, alert);
        alert.show();
    }

    /**
     * Shows alert - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     *
     * @param context
     * @param title
     * @param message
     * @param onClickListener
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showAlert(Context context, String title, String message, int viewResourceId, DialogInterface.OnClickListener onClickListener) {
        // Checks if an alert already exists - if so it gets dismissed
        hideAlert(context);

        // Checks if a progress dialog already exists - if so it gets dismissed
        hideProgress(context);

        // Adds defaul click listener
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            };
        }

        // Creates, stores, and shows
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.input_dialog_box, null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton("Okay", onClickListener);
        alertBuilder.setView(dialogView);
        AlertDialog alert = alertBuilder.create();
        alertHolder.put(context, alert);
        alert.show();
    }

    /**
     * Hides an alert if one is shown for this context
     *
     * @param context
     */
    public void hideAlert(Context context) {
        AlertDialog alert = alertHolder.get(context);
        if (alert != null) {
            alert.dismiss();
            alert = null;

            alertHolder.remove(context);
        }
    }

    /**
     * Shows progress dialog - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     *
     * @param context
     * @param title
     * @param message
     */
    public void showProgress(Context context, String title, String message) {
        // Checks if an alert already exists - if so it gets dismissed
        hideAlert(context);

        // Checks if a progress dialog already exists - if so it gets dismissed
        hideProgress(context);

        ProgressDialog progress = ProgressDialog.show(context, title, message, true);
        progressHolder.put(context, progress);
    }

    /**
     * Hides a progress dialog if one is shown for this context
     *
     * @param context
     */
    public void hideProgress(Context context) {
        ProgressDialog progress = progressHolder.get(context);
        if (progress != null) {
            progress.dismiss();
            progress = null;

            progressHolder.remove(context);
        }
    }

}

