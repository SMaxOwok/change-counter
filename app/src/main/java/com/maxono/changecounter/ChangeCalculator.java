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
    public Integer mNickels;
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
            mNickels = (remainingChange / 5);
            remainingChange -= (mNickels * 5);
        } else {
            mNickels = 0;
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

    public String getNickels() {
        return String.valueOf(mNickels);
    }

    public String getPennies() {
        return String.valueOf(mPennies);
    }

    public String updateText(String value) {
        if (value == "0") {
            return "";
        } else {
            return value;
        }
    }

    public String sayBills() {
        String billString = "";
        if (Integer.parseInt(getTwenties()) > 1) {
            billString = billString.concat(getTwenties() + " twenties, ");
        } else if (Integer.parseInt(getTwenties()) == 1) {
            billString = billString.concat(getTwenties() + " twenty, ");
        } else {
            billString = billString.concat("");
        }
        if (Integer.parseInt(getTens()) > 1) {
            billString = billString.concat(getTens() + " tens, ");
        } else if (Integer.parseInt(getTens()) == 1) {
            billString = billString.concat(getTens() + " ten, ");
        } else {
            billString = billString.concat("");
        }
        if (Integer.parseInt(getFives()) > 1) {
            billString = billString.concat(getFives() + " fives, ");
        } else if (Integer.parseInt(getFives()) == 1) {
            billString = billString.concat(getFives() + " five, ");
        } else {
            billString = billString.concat("");
        }
        if (Integer.parseInt(getOnes()) > 1) {
            billString = billString.concat(getOnes() + " ones, ");
        } else if (Integer.parseInt(getOnes()) == 1) {
            billString = billString.concat(getOnes() + " one.");
        } else {
            billString = billString.concat("");
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
            coinString = coinString.concat("");
        }
        if (Integer.parseInt(getDimes()) > 1) {
            coinString = coinString.concat(getDimes() + " dimes, ");
        } else if (Integer.parseInt(getDimes()) == 1) {
            coinString = coinString.concat(getDimes() + " dime, ");
        } else {
            coinString = coinString.concat("");
        }
        if (Integer.parseInt(getNickels()) > 1) {
            coinString = coinString.concat(getNickels() + " nickles, ");
        } else if (Integer.parseInt(getNickels()) == 1) {
            coinString = coinString.concat(getNickels() + " nickle, ");
        } else {
            coinString = coinString.concat("");
        }
        if (Integer.parseInt(getPennies()) > 1) {
            coinString = coinString.concat(getPennies() + " pennies, ");
        } else if (Integer.parseInt(getPennies()) == 1) {
            coinString = coinString.concat(getPennies() + " penny.");
        } else {
            coinString = coinString.concat("");
        }

        return coinString;
    }
}
