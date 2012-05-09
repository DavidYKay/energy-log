package com.davidykay.energytracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.commonsware.android.syssvc.alarm.OnAlarmReceiver;

public class AlarmScheduler {

  private static final int MILLISECOND = 1;  
  private static final int SECOND = 1000 * MILLISECOND;  
  private static final int MINUTE = 60 * SECOND;  

  //private static final int PERIOD = 5 * MINUTE;  
  private static final int PERIOD = 10 * SECOND;  
  
  private Context mContext;
  
  public AlarmScheduler(Context context) {
    mContext = context;
  }
  
  public void scheduleAlarms() {
    AlarmManager mgr=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
    Intent i=new Intent(mContext, OnAlarmReceiver.class);
    PendingIntent pi=PendingIntent.getBroadcast(mContext, 0,
                                              i, 0);
    
    mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                      SystemClock.elapsedRealtime() + SECOND,
                      PERIOD,
                      pi);
  }
}
