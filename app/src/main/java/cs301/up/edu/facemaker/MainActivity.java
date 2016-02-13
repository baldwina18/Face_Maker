package cs301.up.edu.facemaker;
/**
 * @author Alexa Baldwin
 * @version 12 February 2016
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener {

    protected Spinner hairSpinner;
    protected Spinner eyeSpinner;
    protected Spinner noseSpinner;
    protected Button randomFaceButton;
    protected SeekBar redSeekBar;
    protected SeekBar greenSeekBar;
    protected SeekBar blueSeekBar;
    protected Face face;
    protected CompoundButton skinRadioButton;
    protected CompoundButton hairRadioButton;
    protected CompoundButton eyesRadioButton;


    /**
     * onCreate - initializes all views and sets their corresponding listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         External Citation
            Date:       8 February 2016
            Problem:    Having trouble initializing Spinners
            Resource:   CS371 Lab 4 @author Steven R. Vegdahl
            Solution:   Adapted the lab code accordingly to fit this project
         */
        hairSpinner = (Spinner) findViewById(R.id.hairSpinner);
        String[] hairNames = getResources().getStringArray(R.array.hair_names);
        ArrayAdapter hairAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, hairNames);
        hairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(hairAdapter);
        hairSpinner.setOnItemSelectedListener(this);

        eyeSpinner = (Spinner) findViewById(R.id.eyeSpinner);
        String[] eyeNames = getResources().getStringArray(R.array.eye_names);
        ArrayAdapter eyeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, eyeNames);
        eyeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eyeSpinner.setAdapter(eyeAdapter);
        eyeSpinner.setOnItemSelectedListener(this);

        noseSpinner = (Spinner) findViewById(R.id.noseSpinner);
        String[] noseNames = getResources().getStringArray(R.array.nose_names);
        ArrayAdapter noseAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, noseNames);
        noseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noseSpinner.setAdapter(noseAdapter);
        noseSpinner.setOnItemSelectedListener(this);

        randomFaceButton = (Button) findViewById(R.id.randomFaceButton);
        randomFaceButton.setOnClickListener(this);

        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(this);

        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(this);

        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(this);

        face = (Face) findViewById(R.id.faceSurfaceView);

        hairRadioButton = (CompoundButton) findViewById(R.id.hairRadioButton);
        hairRadioButton.setOnCheckedChangeListener(this);

        skinRadioButton = (CompoundButton) findViewById(R.id.skinRadioButton);
        skinRadioButton.setOnCheckedChangeListener(this);

        eyesRadioButton = (CompoundButton) findViewById(R.id.eyesRadioButton);
        eyesRadioButton.setOnCheckedChangeListener(this);
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * onClick - performs the randomize action when the Random Face Button is clicked
     * @param v - view object that will be clicked
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.randomFaceButton) {
            face.randomize();
        }
        face.invalidate();
    }//onClick

    /**
     * onProgressChanged - checks if progress has been changed on each seek bar and
     * changes the color of the selected radio button according to that RGB value
     *
     * @param seekBar - SeekBar object to read from
     * @param progress - integer that tells the current value fo the seek bar
     * @param fromUser - boolean to see if the user moves the seek bar
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //converts RGB to integer value
        /**
         External Citation
            Date:       11 February 2016
            Problem:    Having trouble converting code to integer from RGB values
            Resource:   Classmate Levi Banks
            Solution:   Levi suggested the following line of code to convert to integer values
         */
        int color = Color.argb(255, this.redSeekBar.getProgress(), this.greenSeekBar.getProgress(),
                this.blueSeekBar.getProgress());
        //checks to see which radio button is currently selected
        if (hairRadioButton.isChecked()  && fromUser) {
            face.hairColor = color;
        } else if (eyesRadioButton.isChecked() && fromUser) {
            face.eyeColor = color;
        } else if (skinRadioButton.isChecked() && fromUser) {
            face.skinColor = color;
        }
        face.invalidate();
    }//onProgressChanged

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not used
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not used
    }

    /**
     * onItemSelected - for each spinner option, changes the face accordingly if that option is
     * selected
     * @param parent - tells which spinner we are using
     * @param view - view to edit
     * @param position - position in the array of options
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String pos = parent.getItemAtPosition(position).toString();
        if (pos.equals("Regular Eyes")){
            face.eyeStyle = 1;
        } else if (pos.equals("Diamond Eyes")){
            face.eyeStyle = 2;
        } else if (pos.equals("Pretty Eyes")) {
            face.eyeStyle = 3;
        } else if (pos.equals("Triangle Nose")){
            face.noseStyle = 1;
        } else if (pos.equals("Pig Nose")){
            face.noseStyle = 2;
        } else if (pos.equals("Regular Nose")) {
            face.noseStyle = 3;
        } else if (pos.equals("Spiked Hair")){
            face.hairStyleIndex = 0;
        } else if (pos.equals("Mohawk")){
            face.hairStyleIndex = 1;
        } else if (pos.equals("Long Hair")) {
            face.hairStyleIndex = 2;
        }
        face.invalidate();
    }//onItemSelected

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // not used
    }

    /**
     * onCheckChanged - determines which radio button is currently selected
     * @param buttonView - radio button to use
     * @param isChecked - boolean to see if the button is checked or not
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.hairRadioButton && buttonView.isChecked()) {
            redSeekBar.setProgress(Color.red(face.hairColor));
            blueSeekBar.setProgress(Color.blue(face.hairColor));
            greenSeekBar.setProgress(Color.green(face.hairColor));
        } else if (buttonView.getId() == R.id.skinRadioButton && buttonView.isChecked()) {
            redSeekBar.setProgress(Color.red(face.skinColor));
            blueSeekBar.setProgress(Color.blue(face.skinColor));
            greenSeekBar.setProgress(Color.green(face.skinColor));
        } else if (buttonView.getId() == R.id.eyesRadioButton && buttonView.isChecked()) {
            redSeekBar.setProgress(Color.red(face.eyeColor));
            blueSeekBar.setProgress(Color.blue(face.eyeColor));
            greenSeekBar.setProgress(Color.green(face.eyeColor));
        }
    }

}//class MainActivity
