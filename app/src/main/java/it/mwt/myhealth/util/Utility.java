package it.mwt.myhealth.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class Utility {

    public static void showDialog(View view, String title, String message, String buttonName){
        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(
                buttonName,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public static void showToast(View view, String message, int duration){
        Toast toast = Toast.makeText(view.getContext(), message, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 500);
        toast.show();
    }
}
