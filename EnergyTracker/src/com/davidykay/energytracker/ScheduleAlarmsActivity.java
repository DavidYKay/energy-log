package com.davidykay.energytracker;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.inject.Inject;

public class ScheduleAlarmsActivity extends RoboActivity {

  public static final class Preferences {
    public static final String NOTIFICATIONS_ENABLED = "notifications_enabled";
  }


  @Inject private AlarmScheduler mAlarmScheduler;
  @InjectView(R.id.toggle_button) private ToggleButton mToggleButton;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.schedule);

    loadToggleState();
  }

  private void loadToggleState() {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    boolean toggleButtonEnabled = preferences.getBoolean(ScheduleAlarmsActivity.Preferences.NOTIFICATIONS_ENABLED, false);
    mToggleButton.setChecked(toggleButtonEnabled);
  }

  private void saveToggleState() {
    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
    editor.putBoolean(ScheduleAlarmsActivity.Preferences.NOTIFICATIONS_ENABLED,
                      mToggleButton.isChecked());
    editor.commit();
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
    saveToggleState();
  }

  public void onListClicked(View v) {
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
  }
  
  public void onGraphClicked(View v) {
    Intent i = new Intent(this, GraphActivity.class);
    startActivity(i);
  }

}
