package com.davidykay.energytracker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.davidykay.energytracker.model.DatabaseHelper;
import com.davidykay.energytracker.model.Rating;
import com.j256.ormlite.dao.Dao;

public class MainActivity extends RoboListActivity {
  private void writeFileToDir() throws IOException {
    String FILENAME = "hello_file";
    String string = "hello world!";

    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

    Log.v("File", getFilesDir().toString());

    fos.write(string.getBytes());
    fos.close();
  }

  @InjectView(R.id.add_button) Button mAddButton;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    try {
      writeFileToDir();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    mAddButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(intent);
      }

    });
  }

  @Override
  public void onResume() {
    super.onResume();

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    Dao<Rating, Integer> ratingDao;
    List<Rating> ratingList = null;
    try {
      ratingDao = databaseHelper.getDao();
      ratingList = ratingDao.queryForAll();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assert (ratingList != null);

    //List<Rating> ratingList = Rating.getAllRatings();
    Rating[] ratings = new Rating[ratingList.size()];
    ratingList.toArray(ratings);
    setListAdapter(new ArticleAdapter(this, R.layout.rating_row, ratings));
  }

  private class ArticleAdapter extends ArrayAdapter<Rating> {

    private Rating[] mRatings;
    private int mRowViewId;

    public ArticleAdapter (Context context, int textViewResourceId, Rating[] ratings) {
      super(context, textViewResourceId, ratings);
      mRatings = ratings;
      mRowViewId = textViewResourceId;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
      RatingRowWrapper wrapper;
      if (row == null) {
        LayoutInflater inflater = getLayoutInflater();
        row = inflater.inflate(mRowViewId, null);
        wrapper = new RatingRowWrapper(row);
        row.setTag(wrapper);
      } else {
        wrapper = (RatingRowWrapper)row.getTag();
      }

      Rating rating = mRatings[position];

      // actual logic to populate row from Cursor goes here
      wrapper.getTitle().setText(rating.getNiceTime());

      //wrapper.getName().setText(rating.value);

      float value = rating.value;
      String starString = "";
      for (int i = 0; i < value; i++) {
        starString += "*";
      }

      wrapper.getSubtitle().setText(
          starString
          );

      //String thumbUrl = cursor.getString(cursor.getColumnIndex(Ratings.THUMB_URL));
      //mImageLoader.DisplayImage(
      //    thumbUrl,
      //    wrapper.getMyImage()
      //);

      return(row);
    }
  }
}
