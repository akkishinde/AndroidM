package com.example.leftshift.androidm;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    protected static final String ACTION_NOTIFICATION_DELETE
            = "com.example.leftshift.androidm.delete";
    private static final String TAG = "NotificationActivity";
    private NotificationManager mNotificationManager;
    private TextView txtNotificationCount;
    private Button btnAddNotification;
    private TextView txtNext;


    // Every notification needs a unique ID otherwise the previous one would be overwritten.
    private int mNotificationId = 0;
    private PendingIntent mDeletePendingIntent;
    private BroadcastReceiver mDeleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           updateNumberOfNotifications();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnAddNotification=(Button)findViewById(R.id.button);
        txtNext=(TextView)findViewById(R.id.txtNext);
        mNotificationManager = (NotificationManager) getApplication().getSystemService(
                Context.NOTIFICATION_SERVICE);
        txtNotificationCount = (TextView)findViewById(R.id.txtNotificationCount);
        btnAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotificationAndReadNumber();
            }
        });

        Intent deleteIntent = new Intent(NotificationActivity.ACTION_NOTIFICATION_DELETE);
        mDeletePendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                2323 /* requestCode */, deleteIntent, 0);
        updateNumberOfNotifications();

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, DirectShareActivity.class);
                startActivity(intent);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addNotificationAndReadNumber() {
        // [BEGIN create_notification]
        // Create a Notification and notify the system.
        final Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("This is a sample Notification")
                .setAutoCancel(true)
                .setDeleteIntent(mDeletePendingIntent);


        final Notification notification = builder.build();
        mNotificationManager.notify(++mNotificationId, notification);
        // [END create_notification]
        Log.i(TAG, "Add a notification");
        updateNumberOfNotifications();

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mDeleteReceiver, new IntentFilter(ACTION_NOTIFICATION_DELETE));
        updateNumberOfNotifications();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDeleteReceiver);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void updateNumberOfNotifications() {
        // [BEGIN get_active_notifications]
        // Query the currently displayed notifications.
        final StatusBarNotification[] activeNotifications = mNotificationManager
                .getActiveNotifications();
        // [END get_active_notifications]
        final int numberOfNotifications = activeNotifications.length;
        //txtNotificationCount.setText(getString(R.string.active_notifications+numberOfNotifications));
        txtNotificationCount.setText(String.valueOf(numberOfNotifications));
        Log.i(TAG, getString(R.string.active_notifications,numberOfNotifications));
    }

}
