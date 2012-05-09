package com.davidykay.energytracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ScheduleAlarmsActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    new AlarmScheduler(this).scheduleAlarms();
    Toast.makeText(this, "Alarms active!", Toast.LENGTH_LONG).show();
    finish();
  }
}
