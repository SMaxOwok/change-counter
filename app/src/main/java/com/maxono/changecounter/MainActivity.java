package com.maxono.changecounter;

import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    protected static final int REQUEST_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
            try {
                startActivityForResult(i, REQUEST_OK);
            } catch (Exception e) {
                Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_OK && resultCode==RESULT_OK) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            ((TextView)findViewById(R.id.text1)).setText(thingsYouSaid.get(0));

            // determine change due
            String change = thingsYouSaid.get(0).toString().replace("$","");
            ChangeCalculator mChangeCalculator = new ChangeCalculator(change);

            // update text to display change due
            ((TextView)findViewById(R.id.twenties)).setText(String.valueOf(mChangeCalculator.getTwenties()) + " - $20");
            ((TextView)findViewById(R.id.tens)).setText(String.valueOf(mChangeCalculator.getTens()));
            ((TextView)findViewById(R.id.fives)).setText(String.valueOf(mChangeCalculator.getFives()) + " - $5");
            ((TextView)findViewById(R.id.ones)).setText(String.valueOf(mChangeCalculator.getOnes()) + " - $1");
            ((TextView)findViewById(R.id.quarters)).setText(String.valueOf(mChangeCalculator.getQuarters()));
            ((TextView)findViewById(R.id.dimes)).setText(String.valueOf(mChangeCalculator.getDimes()));
            ((TextView)findViewById(R.id.nickles)).setText(String.valueOf(mChangeCalculator.getNickles()));
            ((TextView)findViewById(R.id.pennies)).setText(String.valueOf(mChangeCalculator.getPennies()));
        }
    }
}
