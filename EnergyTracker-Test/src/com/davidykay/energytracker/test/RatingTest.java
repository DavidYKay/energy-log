package com.davidykay.energytracker.test;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.davidykay.energytracker.model.DatabaseHelper;
import com.davidykay.energytracker.model.Rating;
import com.j256.ormlite.dao.Dao;

public class RatingTest extends AndroidTestCase {

  private static final String TEST_FILE_PREFIX = "test_";

  private Context mContext;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    RenamingDelegatingContext context = new RenamingDelegatingContext(
        getContext(), TEST_FILE_PREFIX);
    mContext = context;

  }

  public void testRating() throws SQLException {

    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
    Dao<Rating, Integer> ratingDao = databaseHelper.getDao();

    List<Rating> initialRatings = ratingDao.queryForAll();
    assertEquals(0, initialRatings.size());

    Rating rating = new Rating(5.0f);
    ratingDao.create(rating);

    List<Rating> addedRatings = ratingDao.queryForAll();
    assertEquals(1, addedRatings.size());

    ratingDao.delete(rating);

    List<Rating> finalRatings = ratingDao.queryForAll();
    assertEquals(0, finalRatings.size());

    // fail("Oops");

  }

}
