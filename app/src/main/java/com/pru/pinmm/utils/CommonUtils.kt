package com.pru.pinmm.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pru.pinmm.R
import com.pru.pinmm.interfaces.DialogClickInterface
import java.text.SimpleDateFormat
import java.util.*


object CommonUtils {
    fun hideAlertDialog(alertDialog: AlertDialog?) {
        alertDialog?.dismiss()
    }

    fun hideKeyBoard(view: EditText?) {
        if (view != null) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @JvmStatic
    fun showAlertDialog(
        context: Context?,
        message: String?,
        isCancelable: Boolean,
        positiveBtnName: String?,
        negativeBtnName: String?,
        dialogClickInterface: DialogClickInterface
    ): AlertDialog {
        val builder1 = AlertDialog.Builder(
            context!!
        )
        builder1.setMessage(message)
        builder1.setCancelable(isCancelable)
        builder1.setPositiveButton(
            positiveBtnName
        ) { dialog, id -> dialogClickInterface.positiveClick(dialog) }
        if (negativeBtnName != null) {
            builder1.setNegativeButton(
                negativeBtnName
            ) { dialog, id -> dialogClickInterface.negativeClick(dialog) }
        }
        return builder1.create()
    }

    fun showCustomAlertDialog(context: Context, layoutView: View) {
        val dialog = Dialog(context, R.style.CustomFilterDialogTransparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(layoutView)
        dialog.show()
    }

    @JvmStatic
    fun getCurrentTimeStamp(): String? {
        return try {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            dateFormat.format(Date())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestForPermission(activity: Activity, permission: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permission, requestCode)
    }

    fun isPermissionGranted(grantResults: IntArray): Boolean {
        var permissionGranted = true
        if (grantResults.isNotEmpty()) {
            for (grantResult in grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    permissionGranted = false
                    break
                }
            }
        } else permissionGranted = false
        return permissionGranted
    }
}