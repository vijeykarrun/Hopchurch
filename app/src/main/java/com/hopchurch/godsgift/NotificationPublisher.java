package com.hopchurch.godsgift;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hopchurch.Application.MyApplicaionClass;

public class NotificationPublisher extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Builder builder = new Builder(MyApplicaionClass.getContext());
        builder.setContentTitle("Baby talk");
        builder.setContentText("Listen to what your baby says");
        builder.setSmallIcon(com.hopchurch.godsgift.R.mipmap.ic_launcher);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        builder.setContentIntent(PendingIntent.getActivity(MyApplicaionClass.getContext(), 0, new Intent(MyApplicaionClass.getContext(), MainActivity.class), 134217728));
        builder.setAutoCancel(true);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(16842960, builder.build());
    }
}
