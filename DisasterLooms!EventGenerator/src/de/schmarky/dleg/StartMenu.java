/**
 * 
 */
package de.schmarky.dleg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author mark
 * 
 */
public class StartMenu extends Activity implements OnClickListener {

  Button button1, button2, button3, button4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.start_menu);

    button1 = (Button) findViewById(R.id.bRandomEvent);
    button2 = (Button) findViewById(R.id.bAllEvents);
    button3 = (Button) findViewById(R.id.bScoring);
    button4 = (Button) findViewById(R.id.bSettings);

    button1.setOnClickListener(this);
    button2.setOnClickListener(this);
    button3.setOnClickListener(this);
    button4.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Intent newIntent;
    System.out.println(v.getId());
    System.out.println(R.id.bRandomEvent);

    // TODO Auto-generated method stub
    switch (v.getId()) {
    case R.id.bRandomEvent:
      newIntent = new Intent("de.schmarky.dleg.EVENTVIEW");
      newIntent.putExtra("iShowAll", 0);
      startActivity(newIntent);
      break;
    case R.id.bAllEvents:
      newIntent = new Intent("de.schmarky.dleg.EVENTVIEW");
      newIntent.putExtra("iShowAll", 1);
      startActivity(newIntent);
      break;
    case R.id.bScoring:
        newIntent = new Intent("de.schmarky.dleg.SCORING");
        startActivity(newIntent);
        break;
    case R.id.bSettings:
      newIntent = new Intent("de.schmarky.dleg.SETTINGS");
      startActivity(newIntent);
      break;

    default:
      break;
    }
  }

}
