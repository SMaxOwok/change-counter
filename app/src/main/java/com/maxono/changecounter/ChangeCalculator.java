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

    public String getTwenties() {
        return String.valueOf(mTwenties);
    }

    public String getTens() {
        return String.valueOf(mTens);
    }

    public String getFives() {
        return String.valueOf(mFives);
    }

    public String getOnes() {
        return String.valueOf(mOnes);
    }

    public String getQuarters() {
        return String.valueOf(mQuarters);
    }

    public String getDimes() {
        return String.valueOf(mDimes);
    }

    public String getNickles() {
        return String.valueOf(mNickles);
    }

    public String getPennies() {
        return String.valueOf(mPennies);
    }

    public String sayBills() {
        String billString = "";
        if (Integer.parseInt(getTwenties()) > 1) {
            billString = billString.concat(getTwenties() + " twenties, ");
        } else if (Integer.parseInt(getTwenties()) == 1) {
            billString = billString.concat(getTwenties() + " twenty, ");
        } else {
            billString = billString.concat("no twenties, ");
        }
        if (Integer.parseInt(getTens()) > 1) {
            billString = billString.concat(getTens() + " tens, ");
        } else if (Integer.parseInt(getTens()) == 1) {
            billString = billString.concat(getTens() + " ten, ");
        } else {
            billString = billString.concat(" no tens, ");
        }
        if (Integer.parseInt(getFives()) > 1) {
            billString = billString.concat(getFives() + " fives, ");
        } else if (Integer.parseInt(getFives()) == 1) {
            billString = billString.concat(getFives() + " five, ");
        } else {
            billString = billString.concat(" no fives, ");
        }
        if (Integer.parseInt(getOnes()) > 1) {
            billString = billString.concat(" and " + getOnes() + " ones, ");
        } else if (Integer.parseInt(getOnes()) == 1) {
            billString = billString.concat(" and " + getOnes() + " one.");
        } else {
            billString = billString.concat(" and no ones.");
        }

        return billString;
    }

    public String sayCoins() {
        String coinString = "";
        if (Integer.parseInt(getQuarters()) > 1) {
            coinString = coinString.concat(getQuarters() + " quarters, ");
        } else if (Integer.parseInt(getQuarters()) == 1) {
            coinString = coinString.concat(getQuarters() + " quarter, ");
        } else {
            coinString = coinString.concat("no quarters, ");
        }
        if (Integer.parseInt(getDimes()) > 1) {
            coinString = coinString.concat(getDimes() + " dimes, ");
        } else if (Integer.parseInt(getDimes()) == 1) {
            coinString = coinString.concat(getDimes() + " dime, ");
        } else {
            coinString = coinString.concat(" no dimes, ");
        }
        if (Integer.parseInt(getNickles()) > 1) {
            coinString = coinString.concat(getNickles() + " nickles, ");
        } else if (Integer.parseInt(getNickles()) == 1) {
            coinString = coinString.concat(getNickles() + " nickle, ");
        } else {
            coinString = coinString.concat(" no nickles, ");
        }
        if (Integer.parseInt(getPennies()) > 1) {
            coinString = coinString.concat(" and " + getPennies() + " pennies, ");
        } else if (Integer.parseInt(getPennies()) == 1) {
            coinString = coinString.concat(" and " + getPennies() + " penny.");
        } else {
            coinString = coinString.concat(" and no pennies.");
        }

        return coinString;
    }
}
