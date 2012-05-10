
package com.davidykay.energytracker;

import java.sql.SQLException;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.davidykay.energytracker.model.DatabaseHelper;
import com.davidykay.energytracker.model.Rating;
import com.j256.ormlite.dao.Dao;

public class RecordActivity extends RoboActivity {

  @InjectView (R.id.rating_bar) private RatingBar mRatingBar;
  //private RatingManager mRatingManager;

  private Context mContext;

  public RecordActivity() {
    mContext = this;
  }

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.record_energy);

    //mRatingManager = new RatingManager();

    Button recordButton = (Button) findViewById(R.id.record_button);
    recordButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        float value = mRatingBar.getRating();
        Rating rating = new Rating(value);

        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        Dao<Rating, Integer> ratingDao;
        try {
          ratingDao = databaseHelper.getDao();
          ratingDao.create(rating);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        finish();
      }

    });
  }
}
