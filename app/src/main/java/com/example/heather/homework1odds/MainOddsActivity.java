//Heather Kramer <kramerh@stanford.edu>
//Game of Odds 1.0 - This game plays the popular dare game of "Odds".
//  The user chooses what the odds are that they will do a certain dare
//  out of any number between 2 and 22. Then the user selects a number from
//  1 to the max they chose. The computer also randomly chooses a number in
//  that range, and if the 2 numbers are the same, the user must do the dare.
package com.example.heather.homework1odds;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;


public class MainOddsActivity extends AppCompatActivity {
    public static final int MIN_ODDS = 2;
    public static final int MAX_ODDS = 22;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_odds);
        NumberPicker maxOdds = (NumberPicker) findViewById(R.id.numberPicker);
        if (maxOdds != null) {
            maxOdds.setMinValue(MIN_ODDS);
            maxOdds.setMaxValue(MAX_ODDS);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void playOdds(View view) {
        NumberPicker maxOdds = (NumberPicker) findViewById(R.id.numberPicker);
        assert maxOdds != null;
        int maxVal = maxOdds.getValue();
        EditText input = (EditText) findViewById(R.id.editText);
        assert input != null;
        int theirVal = Integer.parseInt(input.getText().toString());
        TextView message = (TextView) findViewById(R.id.resultMessageText);
        assert message != null;
        if (theirVal > maxVal || theirVal < 1) {
            message.setText("Number must be between 1 and " + maxVal);
        } else {
            int randNum = getRandomValue(maxVal);
            TextView displayNum = (TextView) findViewById(R.id.randomNumText);
            assert displayNum != null;
            displayNum.setText("What I said: " + Integer.toString(randNum));
            if (theirVal == randNum){
                message.setText("YOU LOSE! HAHA!");
            } else {
                message.setText("You beat the odds...\nCare to try your luck again?");
            }
        }

    }

    private int getRandomValue(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1; //from stackoverflow
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainOdds Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.heather.homework1odds/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainOdds Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.heather.homework1odds/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
