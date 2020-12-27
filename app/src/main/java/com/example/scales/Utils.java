package com.example.scales;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

public class Utils {
    public final static int sREQUEST_CODE_SETTINGS = 100, sREQUEST_CODE_LOCATION_PERMISSION = 100;

    private static void showAlertDialog(Context context, String strTitle, String strMsg) {
        AlertDialog.Builder alertDialogBuilder = getDialogBasicsADB(context, strTitle, strMsg);

            alertDialogBuilder.setNeutralButton(context.getString(android.R.string.ok),
                    getNewEmptyOnClickListener());
        alertDialogBuilder.show();
    }

    @NonNull
    private static AlertDialog.Builder getDialogBasicsADB(Context context, String strTitle,
                                                          String strMsg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(strTitle).setMessage(strMsg).setIcon(ContextCompat.getDrawable(context, R.mipmap.ic_launcher)).setCancelable(true);
        return alertDialogBuilder;
    }
    @SuppressWarnings("WeakerAccess")
    @NonNull
    public static DialogInterface.OnClickListener getNewEmptyOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        };
    }
    public static void showInfoDialog(Context context, int titleID, int msgID) {
        showInfoDialog(context, context.getString(titleID), context.getString(msgID));
    }
    @SuppressWarnings("WeakerAccess")
    public static void showInfoDialog(Context context, String strTitle, String strMsg) {
        showAlertDialog(context, strTitle, strMsg);
    }
}