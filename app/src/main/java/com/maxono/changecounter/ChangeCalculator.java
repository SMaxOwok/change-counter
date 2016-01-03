package com.maxono.changecounter;


import android.util.Log;

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
        baseChange = Math.ceil(Double.valueOf(changeDue) * 100);
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

    public String sayTwenties() {
        String twentyString = "";
        if (Integer.parseInt(getTwenties()) > 1) {
            twentyString = twentyString.concat(getTwenties() + " twenties, ");
        } else if (Integer.parseInt(getTwenties()) == 1) {
            twentyString = twentyString.concat(getTwenties() + " twenty, ");
        } else {
            twentyString = twentyString.concat("");
        }

        return twentyString;
    }

    public String sayTens() {
        String tenString = "";
        if (Integer.parseInt(getTens()) > 1) {
            tenString = tenString.concat(getTens() + " tens, ");
        } else if (Integer.parseInt(getTens()) == 1) {
            tenString = tenString.concat(getTens() + " ten, ");
        } else {
            tenString = tenString.concat("");
        }

        return tenString;
    }

    public String sayFives() {
        String fiveString = "";
        if (Integer.parseInt(getFives()) > 1) {
            fiveString = fiveString.concat(getFives() + " fives, ");
        } else if (Integer.parseInt(getFives()) == 1) {
            fiveString = fiveString.concat(getFives() + " five, ");
        } else {
            fiveString = fiveString.concat("");
        }

        return fiveString;
    }

    public String sayOnes() {
        String oneString = "";
        if (Integer.parseInt(getOnes()) > 1) {
            oneString = oneString.concat(getOnes() + " ones, ");
        } else if (Integer.parseInt(getOnes()) == 1) {
            oneString = oneString.concat(getOnes() + " one, ");
        } else {
            oneString = oneString.concat("");
        }

        return oneString;
    }

    public String sayQuarters() {
        String quarterString = "";
        if (Integer.parseInt(getQuarters()) > 1) {
            quarterString = quarterString.concat(getQuarters() + " quarters, ");
        } else if (Integer.parseInt(getQuarters()) == 1) {
            quarterString = quarterString.concat(getQuarters() + " quarter, ");
        } else {
            quarterString = quarterString.concat("");
        }

        return quarterString;
    }

    public String sayDimes() {
        String dimeString = "";
        if (Integer.parseInt(getDimes()) > 1) {
            dimeString = dimeString.concat(getDimes() + " dimes, ");
        } else if (Integer.parseInt(getDimes()) == 1) {
            dimeString = dimeString.concat(getDimes() + " dime, ");
        } else {
            dimeString = dimeString.concat("");
        }

        return dimeString;
    }

    public String sayNickels() {
        String nickelString = "";
        if (Integer.parseInt(getNickels()) > 1) {
            nickelString = nickelString.concat(getNickels() + " nickels, ");
        } else if (Integer.parseInt(getNickels()) == 1) {
            nickelString = nickelString.concat(getNickels() + " nickel, ");
        } else {
            nickelString = nickelString.concat("");
        }

        return nickelString;
    }

    public String sayPennies() {
        String pennyString = "";
        if (Integer.parseInt(getPennies()) > 1) {
            pennyString = pennyString.concat(getPennies() + " pennies, ");
        } else if (Integer.parseInt(getPennies()) == 1) {
            pennyString = pennyString.concat(getPennies() + " penny, ");
        } else {
            pennyString = pennyString.concat("");
        }

        return pennyString;
    }
}
