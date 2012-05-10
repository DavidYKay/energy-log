package com.davidykay.energytracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.inject.Inject;

public class NotificationHelper {
  public static final int NOTIFY_ME_ID = 1337;

  private static final Class<RecordActivity> NOTIFICATION_CLASS = RecordActivity.class;

  private Context mContext;
  private NotificationManager mNotificationManager;
  
  //private int mCount;

  @Inject
  public NotificationHelper(Context context,
      NotificationManager notificationManager) {
    mContext = context;
    mNotificationManager = notificationManager;
  }

  public void showNotification() {
    Notification note = new Notification(R.drawable.ic_launcher, 
                            mContext.getString(R.string.notification_short),
        System.currentTimeMillis());

    note.flags |= Notification.FLAG_AUTO_CANCEL;
    note.defaults |= Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;

    PendingIntent i = PendingIntent.getActivity(mContext, 0, new Intent(
        mContext, NOTIFICATION_CLASS), 0);

    note.setLatestEventInfo(mContext,
                            mContext.getString(R.string.notification_title),
                            mContext.getString(R.string.notification_message),
                            i);

    // note.number = ++count;
    mNotificationManager.notify(NOTIFY_ME_ID, note);
  }

  public void clearNotification() {
    mNotificationManager.cancel(NOTIFY_ME_ID);
  }
}
