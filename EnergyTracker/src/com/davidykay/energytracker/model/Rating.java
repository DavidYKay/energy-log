package com.davidykay.energytracker.model;

import java.util.Date;

import android.text.format.DateUtils;

import com.j256.ormlite.field.DatabaseField;

public class Rating {

  @DatabaseField(generatedId = true)
  long id;
  @DatabaseField
  public float value;
  @DatabaseField
  public Date time;

  public Rating() {
    // Required for ormlite
  }

  public Rating(float value) {
    this(value, new Date());
  }

  public Rating(float value, Date time) {
    super();
    this.value = value;
    this.time = time;
  }

  public float getValue() {
    return value;
  }

  public Date getTime() {
    return time;
  }

  public CharSequence getNiceTime() {
    return DateUtils.getRelativeTimeSpanString(time.getTime());
  }
}
