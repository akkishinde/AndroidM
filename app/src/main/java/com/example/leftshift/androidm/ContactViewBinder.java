package com.example.leftshift.androidm;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;

/**
 * Created by Akshay on 09-10-2015.
 */
public class ContactViewBinder {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void bind(Contact contact, TextView textView) {
        textView.setText(contact.getName());
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(contact.getIcon(), 0, 0, 0);
    }
}
