package com.maxono.changecounter;

import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
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
                    speechEngine.setPitch(1.0f);
                    speechEngine.setSpeechRate(1.0f);
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

    public void sayBills(String mTwenties, String twenties, String mTens, String tens, String mFives, String fives, String mOnes, String ones) {
        if (Integer.valueOf(mTwenties) > 0) {
            speechEngine.speak(twenties, TextToSpeech.QUEUE_FLUSH, null);
            pause(1000);
        }
        if (Integer.valueOf(mTens) > 0) {
            speechEngine.speak(tens, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
        if (Integer.valueOf(mFives) > 0) {
            speechEngine.speak(fives, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
        if (Integer.valueOf(mOnes) > 0) {
            speechEngine.speak(ones, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
    }

    public void sayCoins(String mQuarters, String quarters, String mDimes, String dimes, String mNickels, String nickels, String mPennies, String pennies) {
        if (Integer.valueOf(mQuarters) > 0) {
            speechEngine.speak(quarters, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
        if (Integer.valueOf(mDimes) > 0) {
            speechEngine.speak(dimes, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
        if (Integer.valueOf(mNickels) > 0) {
            speechEngine.speak(nickels, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
        }
        if (Integer.valueOf(mPennies) > 0) {
            speechEngine.speak(pennies, TextToSpeech.QUEUE_ADD, null);
            pause(1000);
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
            sayBills(mChangeCalculator.getTwenties(), mChangeCalculator.sayTwenties(), mChangeCalculator.getTens(), mChangeCalculator.sayTens(), mChangeCalculator.getFives(), mChangeCalculator.sayFives(), mChangeCalculator.getOnes(), mChangeCalculator.sayOnes());
            sayCoins(mChangeCalculator.getQuarters(), mChangeCalculator.sayQuarters(), mChangeCalculator.getDimes(), mChangeCalculator.sayDimes(), mChangeCalculator.getNickels(), mChangeCalculator.sayNickels(), mChangeCalculator.getPennies(), mChangeCalculator.sayPennies());

            // make replay buttons work
            findViewById(R.id.buttonBillReplay).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    sayBills(mChangeCalculator.getTwenties(), mChangeCalculator.sayTwenties(), mChangeCalculator.getTens(), mChangeCalculator.sayTens(), mChangeCalculator.getFives(), mChangeCalculator.sayFives(), mChangeCalculator.getOnes(), mChangeCalculator.sayOnes());
                }
            });

            findViewById(R.id.buttonCoinsReplay).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    sayCoins(mChangeCalculator.getQuarters(), mChangeCalculator.sayQuarters(), mChangeCalculator.getDimes(), mChangeCalculator.sayDimes(), mChangeCalculator.getNickels(), mChangeCalculator.sayNickels(), mChangeCalculator.getPennies(), mChangeCalculator.sayPennies());
                }
            });
        }
    }
}
