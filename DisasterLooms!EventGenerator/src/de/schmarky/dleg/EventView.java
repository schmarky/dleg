package de.schmarky.dleg;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class EventView extends Activity {

  private ViewPager viewPager;
  private ViewPagerAdapter adapter;
  private String[] sTitle;
  private String[] sContent;

  // private int iCurrentPage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // setContentView(R.layout.activity_event_view);
    setContentView(R.layout.viewpager_main);

    // init the variables...
    init();

    // get flag to allow or disallow swipe
    Bundle b = getIntent().getExtras();
    final int iShowAll = b.getInt("iShowAll");

    // Locate the ViewPager in viewpager_main.xml
    viewPager = (ViewPager) findViewById(R.id.pager);
    // Pass results to ViewPagerAdapter Class
    adapter = new ViewPagerAdapter(this, getsTitle(), getsContent());
    // Binds the Adapter to the ViewPager
    viewPager.setAdapter(adapter);

    // tell adapter to show or dont show the button
    if (iShowAll == 1) {
      adapter.setbButtonRandomShown(false);
    }

    // set OnTouchListener to prevent view change via swipe if needed
    viewPager.setOnTouchListener(new View.OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent e) {

        if (iShowAll == 1) {
          return false;
        } else {
          return true;
        }

      }
    });
    /*
     * // listen for page changes so we can track the current index
     * viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
     * 
     * public void onPageScrollStateChanged(int arg0) { }
     * 
     * public void onPageScrolled(int arg0, float arg1, int arg2) { }
     * 
     * public void onPageSelected(int currentPage) { // currentPage is the
     * position that is currently displayed. setPage(currentPage); }
     * 
     * });
     */
  }

  /**
   * init the globals
   */
  private void init() {
    sTitle = new String[8];
    sContent = new String[8];

    for (int i = 0; i < sTitle.length; i++) {
      int iId;
      // get the event name
      iId = getResources().getIdentifier(
          "name_2013_" + String.format("%02d", i + 1), "string",
          getPackageName());
      sTitle[i] = getString(iId);

      // get the event content
      iId = getResources().getIdentifier(
          "content_2013_" + String.format("%02d", i + 1), "string",
          getPackageName());
      sContent[i] = getString(iId);
    }
    // set the globals
    setsTitle(sTitle);
    setsContent(sContent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_event_view, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    // System.out.println("Item: " + adapter.getiCurrentItemPos());
    // System.out.println("ItemPos : " + adapter.getItemPosition(adapter));

    // Object curView = adapter.getCurrentItem( this, getPage() );

    /*
     * switch (item.getItemId()) { // toggle button visibility case R.id.random:
     * if (adapter.getButtonVisibility() == View.VISIBLE) {
     * adapter.setButtonVisibility(View.INVISIBLE); } else
     * adapter.setButtonVisibility(View.VISIBLE);
     * 
     * return true; default: return super.onOptionsItemSelected(item); }
     */
    return super.onOptionsItemSelected(item);
  }

  /*
   * getter setter
   */
  /**
   * @return the sTitle
   */
  public String[] getsTitle() {
    return sTitle;
  }

  /**
   * @param sTitle
   *          the sTitle to set
   */
  public void setsTitle(String[] sTitle) {
    this.sTitle = sTitle;
  }

  /**
   * @return the sContent
   */
  public String[] getsContent() {
    return sContent;
  }

  /**
   * @param sContent
   *          the sContent to set
   */
  public void setsContent(String[] sContent) {
    this.sContent = sContent;
  }

  /*
   * public void setPage(int p_iPage) { this.iCurrentPage = p_iPage; }
   * 
   * public int getPage() { return this.iCurrentPage; }
   */

}
