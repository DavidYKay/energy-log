/***
  Copyright (c) 2008-2011 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.

  From _The Busy Coder's Guide to Advanced Android Development_
http://commonsware.com/AdvAndroid
*/

package com.commonsware.android.syssvc.alarm;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.davidykay.energytracker.MainActivity;
import com.davidykay.energytracker.NotificationHelper;

public class OnAlarmReceiver extends BroadcastReceiver {

  private NotificationManager mNotificationManager;
  private NotificationHelper mNotificationHelper;

  @Override
  public void onReceive(Context context, Intent intent) {
    WakefulIntentService.acquireStaticLock(context);

    mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationHelper = new NotificationHelper(context, mNotificationManager);
    
    mNotificationHelper.showNotification();
  }

  private void startService(Context context) {
    context.startService(new Intent(context, AppService.class));
  }

  private void startMainActivity(Context context) {
    Intent activityIntent = new Intent(context, MainActivity.class);
    activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(activityIntent);
  }

}
