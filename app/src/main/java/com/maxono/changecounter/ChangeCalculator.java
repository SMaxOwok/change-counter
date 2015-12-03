package com.maxono.changecounter;


/**
 * Created by maxono on 12/2/15.
 */
public class ChangeCalculator {

    // Member variables
    public Integer mTwenties;
    public Integer mTens;
    public Integer mFives;
    public Integer mOnes;
    public Integer mQuarters;
    public Integer mDimes;
    public Integer mNickles;
    public Integer mPennies;
    public Integer remainingChange;
    public Double baseChange;

    // Methods
    public ChangeCalculator(String changeDue) {  // determine how many of each currency type
        baseChange = Double.parseDouble(changeDue) * 100;
        remainingChange = baseChange.intValue();

        if ((remainingChange / 2000) >= 1) {
            mTwenties = (remainingChange / 2000);
            remainingChange -= (mTwenties * 2000);
        } else {
            mTwenties = 0;
        }
        if ((remainingChange / 1000) >= 1) {
            mTens = (remainingChange / 1000);
            remainingChange -= (mTens * 1000);
        } else {
            mTens = 0;
        }
        if ((remainingChange / 500) >= 1) {
            mFives = (remainingChange / 500);
            remainingChange -= (mFives * 500);
        } else {
            mFives = 0;
        }
        if ((remainingChange / 100) >= 1) {
            mOnes = (remainingChange / 100);
            remainingChange -= (mOnes * 100);
        } else {
            mOnes = 0;
        }
        if ((remainingChange / 25) >= 1) {
            mQuarters = (remainingChange / 25);
            remainingChange -= (mQuarters * 25);
        } else {
            mQuarters = 0;
        }
        if ((remainingChange / 10) >= 1) {
            mDimes = (remainingChange / 10);
            remainingChange -= (mDimes * 10);
        } else {
            mDimes = 0;
        }
        if ((remainingChange / 5) >= 1) {
            mNickles = (remainingChange / 5);
            remainingChange -= (mNickles * 5);
        } else {
            mNickles = 0;
        }
        if (remainingChange >= 1) {
            mPennies = remainingChange;
            remainingChange -= mPennies;
        } else {
            mPennies = 0;
        }
    }

    public int getTwenties() {
        return mTwenties;
    }

    public int getTens() {
        return mTens;
    }

    public int getFives() {
        return mFives;
    }
    public int getOnes() {
        return mOnes;
    }

    public int getQuarters() {
        return mQuarters;
    }

    public int getDimes() {
        return mDimes;
    }

    public int getNickles() {
        return mNickles;
    }

    public int getPennies() {
        return mPennies;
    }
}
