/**
 * 
 */
package de.schmarky.dleg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author MPO
 * 
 */
public class Scoring extends Activity implements
    SeekBar.OnSeekBarChangeListener {

  SeekBar sbColonies, sbCustomers, sbResources, sbShips, sbTechnologies;
  TextView txtColonies, txtCustomers, txtResources, txtShips, txtTechnologies,
      txtTotal;
  CheckBox cbHollywood;

  /*
   * (non-Javadoc)
   * 
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scoring);

    sbColonies = (SeekBar) findViewById(R.id.sbColonies);
    sbColonies.setOnSeekBarChangeListener(this);
    txtColonies = (TextView) findViewById(R.id.tvScoreColonies);
    //
    sbCustomers = (SeekBar) findViewById(R.id.sbCustomers);
    sbCustomers.setOnSeekBarChangeListener(this);
    txtCustomers = (TextView) findViewById(R.id.tvScoreCustomers);
    //
    sbResources = (SeekBar) findViewById(R.id.sbResources);
    sbResources.setOnSeekBarChangeListener(this);
    txtResources = (TextView) findViewById(R.id.tvScoreResources);
    //
    sbShips = (SeekBar) findViewById(R.id.sbShips);
    sbShips.setOnSeekBarChangeListener(this);
    sbShips.setEnabled(false); // initaly disabled
    txtShips = (TextView) findViewById(R.id.tvScoreShips);
    //
    sbTechnologies = (SeekBar) findViewById(R.id.sbTechnologies);
    sbTechnologies.setOnSeekBarChangeListener(this);
    txtTechnologies = (TextView) findViewById(R.id.tvScoreTechnologies);
    //
    cbHollywood = (CheckBox) findViewById(R.id.cbHollywood);
    cbHollywood.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        if (cbHollywood.isChecked()) {
          sbShips.setEnabled(true);
        } else {
          sbShips.setEnabled(false);
          sbShips.setProgress(0);
          txtShips.setText("0");
          //txtShips.setTextColor();
          calculateTotal();
        }

      }
    });
    txtTotal = (TextView) findViewById(R.id.tvScoreTotal);

  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    // TODO Auto-generated method stub
    // System.out.println( "onProgressChanged" );
    // Toast.makeText(Scoring.this, "Seekbar Value : " + progress,
    // Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    // System.out.println( "onStartTrackingTouch" );
    // Toast.makeText(Scoring.this, "Started Tracking Seekbar",
    // Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
    Integer iProgress;
    iProgress = seekBar.getProgress();

    switch (seekBar.getId()) {
    case R.id.sbColonies:
      txtColonies.setText(iProgress.toString());
      break;

    case R.id.sbCustomers:
      txtCustomers.setText(iProgress.toString());
      break;

    case R.id.sbResources:
      txtResources.setText(iProgress.toString());
      break;

    case R.id.sbTechnologies:
      txtTechnologies.setText(iProgress.toString());
      break;

    case R.id.sbShips:
      txtShips.setText(iProgress.toString());
      break;

    default:
      break;
    }
    calculateTotal();

    // Toast.makeText(Scoring.this, "Stopped Tracking Seekbar",
    // Toast.LENGTH_SHORT).show();
  }

  private void calculateTotal() {
    Integer iTotal = 0;

    iTotal += (sbColonies.getProgress()*3);
    iTotal += sbCustomers.getProgress();
    iTotal += (sbResources.getProgress()/5);
    iTotal += sbTechnologies.getProgress();
    iTotal += sbShips.getProgress();

    txtTotal.setText(iTotal.toString());
  }

}
