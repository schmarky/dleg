package de.schmarky.dleg;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyList extends ListActivity {

  
/** Called when the activity is first created. */

  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    // Create an array of Strings, that will be put to our ListActivity
    ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(this,
        getModel());
    setListAdapter(adapter);
  }

  private List<Model> getModel() {
    List<Model> list = new ArrayList<Model>();
    
    String[] sTitle = new String[7];

    for (int i = 0; i < sTitle.length; i++) {
      int iId;
      // get the event name
      iId = getResources().getIdentifier(
          "name_2013_" + String.format("%02d", i + 1), "string",
          getPackageName());
      sTitle[i] = getString(iId);
      list.add(get(getString(iId)));
      // Initially select all of the items
      list.get(i).setSelected(true);
    }

    return list;
  }

  private Model get(String s) {
    return new Model(s);
  }

} 
