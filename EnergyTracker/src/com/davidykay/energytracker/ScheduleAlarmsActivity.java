package com.davidykay.energytracker;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.inject.Inject;

public class ScheduleAlarmsActivity extends RoboActivity {

  @Inject private AlarmScheduler mAlarmScheduler;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.schedule);
  }

  public void onToggleClicked(View v) {
    // Perform action on clicks
    if (((ToggleButton) v).isChecked()) {
      Toast.makeText(ScheduleAlarmsActivity.this, "Notifications on", Toast.LENGTH_SHORT).show();
      mAlarmScheduler.scheduleAlarms();
    } else {
      Toast.makeText(ScheduleAlarmsActivity.this, "Notifications off", Toast.LENGTH_SHORT).show();
      mAlarmScheduler.cancelAlarms();
    }
  }

}
