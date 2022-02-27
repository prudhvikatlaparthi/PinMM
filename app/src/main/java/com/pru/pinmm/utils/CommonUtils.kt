package com.pru.pinmm.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.pru.pinmm.interfaces.DialogClickInterface;

public class CommonUtils {

    public static void hideKeyBoard(EditText view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static AlertDialog showAlertDialog(Context context,
                                              String message,
                                              boolean isCancelable,
                                              String positiveBtnName,
                                              String negativeBtnName,
                                              DialogClickInterface dialogClickInterface) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(isCancelable);
        builder1.setPositiveButton(
                positiveBtnName,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogClickInterface.positiveClick(dialog);
                    }
                });
        if (negativeBtnName != null) {
            builder1.setNegativeButton(
                    negativeBtnName,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialogClickInterface.negativeClick(dialog);
                        }
                    });
        }

        return builder1.create();
    }

    public void hideAlertDialog(AlertDialog alertDialog) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
