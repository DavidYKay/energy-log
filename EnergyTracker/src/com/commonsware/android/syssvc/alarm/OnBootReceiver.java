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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.davidykay.energytracker.AlarmScheduler;

public class OnBootReceiver extends BroadcastReceiver {

  
  @Override
  public void onReceive(Context context, Intent intent) {
    
    new AlarmScheduler(context).scheduleAlarms();
  }
}
