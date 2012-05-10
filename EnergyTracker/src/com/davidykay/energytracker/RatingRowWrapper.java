package com.davidykay.energytracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingRowWrapper {

  View base;
  RatingBar ratingBar = null;
  TextView name     = null;
  TextView subTitle = null;
  ImageView image   = null;

  public RatingRowWrapper(View base) {
    this.base = base;
  }

  public RatingBar getRatingBar() {
    if (ratingBar == null) {
      ratingBar = (RatingBar)base.findViewById(R.id.rating);
    }
    return(ratingBar);
  }

  public TextView getTitle() {
    if (name == null) {
      name = (TextView)base.findViewById(R.id.name);
    }
    return(name);
  }

  public ImageView getImage() {
    if (image == null) {
      image = (ImageView)base.findViewById(R.id.article_image);
    }
    return(image);
  }

  public TextView getSubtitle() {
    if (subTitle == null) {
      subTitle = (TextView)base.findViewById(R.id.subtitle);
    }
    return(subTitle);
  }

}
