package com.maxono.changecounter;

import android.os.Build;
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
                    speechEngine.setPitch(1.1f);
                    speechEngine.setSpeechRate(.2f);
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

    protected void pause(int duration) {
        if (Build.VERSION.SDK_INT >= 21) {
            speechEngine.playSilentUtterance(duration, speechEngine.QUEUE_ADD, null);
        } else {
            speechEngine.playSilence(duration, speechEngine.QUEUE_ADD, null);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_OK && resultCode==RESULT_OK) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // determine change due
            String change = thingsYouSaid.get(0).replace("$","");
            final ChangeCalculator mChangeCalculator = new ChangeCalculator(change);

            // update text to display change due
            ((TextView)findViewById(R.id.twenties)).setText(mChangeCalculator.updateText(mChangeCalculator.getTwenties()));
            ((TextView)findViewById(R.id.tens)).setText(mChangeCalculator.updateText(mChangeCalculator.getTens()));
            ((TextView)findViewById(R.id.fives)).setText(mChangeCalculator.updateText(mChangeCalculator.getFives()));
            ((TextView)findViewById(R.id.ones)).setText(mChangeCalculator.updateText(mChangeCalculator.getOnes()));
            ((TextView)findViewById(R.id.quarters)).setText(mChangeCalculator.updateText(mChangeCalculator.getQuarters()));
            ((TextView)findViewById(R.id.dimes)).setText(mChangeCalculator.updateText(mChangeCalculator.getDimes()));
            ((TextView)findViewById(R.id.nickles)).setText(mChangeCalculator.updateText(mChangeCalculator.getNickels()));
            ((TextView)findViewById(R.id.pennies)).setText(mChangeCalculator.updateText(mChangeCalculator.getPennies()));

            // say the numbers out loud
            speechEngine.speak((mChangeCalculator.sayBills()), TextToSpeech.QUEUE_FLUSH, null);
            pause(1000);
            speechEngine.speak((mChangeCalculator.sayCoins()), TextToSpeech.QUEUE_ADD, null);

            // make replay buttons work
            findViewById(R.id.buttonBillReplay).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    speechEngine.speak((mChangeCalculator.sayBills()), TextToSpeech.QUEUE_FLUSH, null);
                }
            });

            findViewById(R.id.buttonCoinsReplay).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    speechEngine.speak((mChangeCalculator.sayCoins()), TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }
    }
}
