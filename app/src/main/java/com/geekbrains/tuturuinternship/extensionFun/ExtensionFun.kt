package com.geekbrains.tuturuinternship.extensionFun

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.showToastShort(
    text: String,
    length: Int = Toast.LENGTH_SHORT)
{
    Toast.makeText(context, text, length).show()
}

fun View.showToastLong(
    text: String,
    length: Int = Toast.LENGTH_LONG)
{
    Toast.makeText(context, text, length).show()
}

fun View.showSnackBarAction(
    text: String,
    actionText: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (View) -> Unit,)
{
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.showSnackBarNoAction(
    text: String,
    length: Int = Snackbar.LENGTH_SHORT)

{
    Snackbar.make(this, text, length).show()
}