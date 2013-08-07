package de.schmarky.dleg;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author mark
 * 
 */
public class ViewPagerAdapter extends PagerAdapter {
  // Declare Variables
  Context context;
  LayoutInflater inflater;
  private String[] sTitle;
  private String[] sContent;
  int iLastRandomTile = 0;
  boolean bButtonRandomShown = true;

  /**
   * @param context
   * @param p_sTitle
   * @param p_sContent
   */
  public ViewPagerAdapter(Context context, String[] p_sTitle,
      String[] p_sContent) {
    this.context = context;
    this.sTitle = p_sTitle;
    this.sContent = p_sContent;

  }

  /*
   * create new instance (non-Javadoc)
   * 
   * @see
   * android.support.v4.view.PagerAdapter#instantiateItem(android.view.ViewGroup
   * , int)
   */
  @Override
  public Object instantiateItem(ViewGroup container, int position) {

    // Declare Variables
    final TextView txtTitle;
    final TextView txtContent;
    final Button newEvent;

    inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View itemView = inflater.inflate(R.layout.activity_event_view, container,
        false);

    // Locate the TextViews in viewpager_item.xml
    txtTitle = (TextView) itemView.findViewById(R.id.tvTileName);
    txtContent = (TextView) itemView.findViewById(R.id.tvTileContent);
    newEvent = (Button) itemView.findViewById(R.id.button1);

    // Capture position and set to the TextViews
    txtTitle.setText(sTitle[position]);
    txtContent.setText(sContent[position]);

    // set custom font
    Typeface font = Typeface.createFromAsset(context.getAssets(),
        "zekton_rg.ttf");
    txtTitle.setTypeface(font);
    txtContent.setTypeface(font);

    // Add viewpager_item.xml to ViewPager
    ((ViewPager) container).addView(itemView);

    // hide button if needed
    if (isbButtonRandomShown() == false) {
      newEvent.setVisibility(View.INVISIBLE);
    } else {
      // add the onCicklistener to button
      newEvent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          RandomTileEvent(txtTitle, txtContent);
        }
      });
    }
    return itemView;
  }

  /**
   * Change the name and text of the tile
   * 
   * @param txtTitle
   * @param txtContent
   */
  public void RandomTileEvent(TextView txtTitle, TextView txtContent) {
    int iNumber;
    int iId;
    String sText;
    String sIdentifier;
    String sPackageName = context.getPackageName();
    Resources res = context.getResources();
    Random rRan = new Random();

    System.out.println("iTileNumber: " + iLastRandomTile);

    // get a random resource identifier
    do {
      // subtract one because .nextInt() is zero based
      // then add one to exclude the zero
      iNumber = rRan.nextInt(getCount()) + 1;
    } while (iNumber == iLastRandomTile);
    iLastRandomTile = iNumber;

    sIdentifier = "_2013_" + String.format("%02d", iLastRandomTile);

    // get the event name
    iId = res.getIdentifier("name" + sIdentifier, "string", sPackageName);
    sText = context.getString(iId);
    txtTitle.setText(sText);

    // get the event content
    iId = res.getIdentifier("content" + sIdentifier, "string", sPackageName);
    sText = context.getString(iId);
    txtContent.setText(sText);
  }

  @Override
  public int getCount() {
    return sTitle.length;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((RelativeLayout) object);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    // Remove viewpager_item.xml from ViewPager
    ((ViewPager) container).removeView((RelativeLayout) object);
  }

  /*
   * Getter setter
   */

  /**
   * @return the bShowButtonRandom
   */
  public boolean isbButtonRandomShown() {
    return bButtonRandomShown;
  }

  /**
   * @param bShowButtonRandom
   *          the bShowButtonRandom to set
   */
  public void setbButtonRandomShown(boolean bButtonRandomShown) {
    this.bButtonRandomShown = bButtonRandomShown;
  }

}