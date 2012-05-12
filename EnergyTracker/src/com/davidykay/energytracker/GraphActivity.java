package com.davidykay.energytracker;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

import com.davidykay.energytracker.model.DatabaseHelper;
import com.davidykay.energytracker.model.Rating;
import com.j256.ormlite.dao.Dao;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphActivity extends RoboActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //final DateFormat formatter = DateFormat.getDateInstance();
    final DateFormat formatter = DateFormat.getTimeInstance();

    LineGraphView graphView = new LineGraphView(this, "Energy Levels over Time") {
      @Override
      protected String formatLabel(double value, boolean isValueX) {
        if (isValueX) {
          // convert unix time to human time
          //return dateTimeFormatter.format(new Date((long) value*1000));
          //return formatter.format(new Date((long) value*1000));
          return formatter.format(new Date((long)value));
        } else return super.formatLabel(value, isValueX); // let the y-value be normal-formatted
      }
    };

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    Dao<Rating, Integer> ratingDao;
    List<Rating> ratingList = null;
    try {
      ratingDao = databaseHelper.getDao();
      ratingList = ratingDao.queryBuilder().orderBy("time", true).query();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    GraphViewData[] graphData = new GraphViewData[ratingList.size()];
    String[] labels = new String[ratingList.size()];
    for (int i = 0; i < ratingList.size(); i++) {
      Rating rating = ratingList.get(i);
      graphData[i] = new GraphViewData(
          rating.time.getTime(),
          rating.value
          );
      labels[i] = formatter.format(rating.time);
    }
    //final GraphViewData[] dummyData = new GraphViewData[] {
    //    new GraphViewData(1, 2.0d)
    //    , new GraphViewData(2, 1.5d)
    //    , new GraphViewData(2.5, 3.0d)
    //    , new GraphViewData(3, 2.5d)
    //    , new GraphViewData(4, 1.0d)
    //    , new GraphViewData(5, 3.0d)
    //};

    graphView.addSeries(new GraphViewSeries(
      graphData
    ));
    //graphView.setViewPort(2, 10);

    graphView.setDrawBackground(true);

    graphView.setScalable(true);

    //graphView.setHorizontalLabels(new String[]{ "A", "B", "C", "D", "E", });
    graphView.setVerticalLabels(new String[]{
      "5",
      "4",
      "3",
      "2",
      "1",
    });
    //graphView.setHorizontalLabels(new String[]{"Hello world"});


    setContentView(graphView);
  }

}
