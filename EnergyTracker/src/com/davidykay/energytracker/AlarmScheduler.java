package com.davidykay.energytracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.commonsware.android.syssvc.alarm.OnAlarmReceiver;
import com.google.inject.Inject;

public class AlarmScheduler {

  private static final int MILLISECOND = 1;
  private static final int SECOND = 1000 * MILLISECOND;
  private static final int MINUTE = 60 * SECOND;

  private static final int PERIOD = 15 * MINUTE;

  private Context mContext;
  private AlarmManager mAlarmManager;

  @Inject
  public AlarmScheduler(Context context) {
    mContext = context;
    mAlarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
  }
  

  public void cancelAlarms() {
    mAlarmManager.cancel(createPendingIntent());
  }

  public void scheduleAlarms() {
    cancelAlarms();
    mAlarmManager.setRepeating(
        AlarmManager.ELAPSED_REALTIME_WAKEUP,
        SystemClock.elapsedRealtime() + SECOND, 
        PERIOD, 
        createPendingIntent());
  }
  
  private PendingIntent createPendingIntent() {
    Intent i = new Intent(mContext, OnAlarmReceiver.class);
    PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, i, 0);
    return pi;
  }
}
