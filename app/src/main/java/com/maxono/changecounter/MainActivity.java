package com.maxono.changecounter;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    TextToSpeech speechEngine;

    protected static final int REQUEST_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);

        speechEngine = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speechEngine.setLanguage(Locale.US);
                }
            }
        });
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
            ((TextView)findViewById(R.id.twenties)).setText(mChangeCalculator.getTwenties());
            ((TextView)findViewById(R.id.tens)).setText(mChangeCalculator.getTens());
            ((TextView)findViewById(R.id.fives)).setText(mChangeCalculator.getFives());
            ((TextView)findViewById(R.id.ones)).setText(mChangeCalculator.getOnes());
            ((TextView)findViewById(R.id.quarters)).setText(mChangeCalculator.getQuarters());
            ((TextView)findViewById(R.id.dimes)).setText(mChangeCalculator.getDimes());
            ((TextView)findViewById(R.id.nickles)).setText(mChangeCalculator.getNickles());
            ((TextView)findViewById(R.id.pennies)).setText(mChangeCalculator.getPennies());

            // say the numbers out loud
            speechEngine.speak((mChangeCalculator.sayBills()), TextToSpeech.QUEUE_FLUSH, null);
            speechEngine.playSilentUtterance(1000, 1, "silence");
            speechEngine.speak((mChangeCalculator.sayCoins()), TextToSpeech.QUEUE_ADD, null);
        }
    }
}
