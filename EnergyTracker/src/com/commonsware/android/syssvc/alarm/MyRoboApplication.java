package com.commonsware.android.syssvc.alarm;

import roboguice.application.RoboApplication;

public class MyRoboApplication extends RoboApplication {

  @Override
  public void onCreate() {
    super.onCreate();
    //ActiveAndroid.initialize(this);
  }

}
