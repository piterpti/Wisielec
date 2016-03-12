package com.example.piter.wisielec;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.piter.wisielec.Constants.letters;

public class MainScreen extends Activity {

    private static Animation fadeInAnimation;

    private static TypedArray images;
    private static String[] passwords;

    private static int TRY_COUNT;
    public static final String TAG = MainScreen.class.getSimpleName();
    public static String password;
    public static String encoded_password = "";
    public static boolean GAME_PLAYING;

    private static LinearLayout endLayout;
    private static LinearLayout lettersLayout;
    private static TextView password_tv;
    private ImageView wisi_iv;
    private static TextView end_tv;

    private static Context baseContext;

    public static MainScreen thisScreen;

    public static Toast winToast;
    public static Toast loseToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        baseContext = getApplicationContext();
        images = getResources().obtainTypedArray(R.array.images);
        passwords = getResources().getStringArray(R.array.passwords);
        thisScreen = this;

        lettersLayout = (LinearLayout) findViewById(R.id.letters_linearLayout);
        endLayout = (LinearLayout) findViewById(R.id.end_layout);
        password_tv = (TextView) findViewById(R.id.password_tv);
        wisi_iv = (ImageView) findViewById(R.id.wisi_iv);
        wisi_iv.setImageResource(images.getResourceId(0, -1));
        end_tv = (TextView) findViewById(R.id.end_tv);
        fadeInAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.fadein);
        fadeInAnimation.setFillAfter(true);

        winToast = Toast.makeText(this, "Zwycistwo!", Toast.LENGTH_LONG);
        winToast.setGravity(Gravity.TOP, 0, 40);

        loseToast = Toast.makeText(this, "Porazka!", Toast.LENGTH_LONG);
        loseToast.setGravity(Gravity.TOP, 0, 40);

        final Button retry_bt = (Button) findViewById(R.id.retry_bt);
        retry_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetToStart();
            }
        });
        resetToStart();
    }

    private void generatePassword() {
        Random generator = new Random();
        int lossNumber = generator.nextInt(passwords.length);

        password = passwords[lossNumber].toUpperCase();
        encodePassword();
        Log.d(TAG, password);
    }

    private void createLetters() {
        lettersLayout.removeAllViews();
        int count = 0;
        int verticalLayouts = letters.length / 5;
        for (int i = 0; i < verticalLayouts; i++) {
            LinearLayout layoutHorizontal = new LinearLayout(this);
            layoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            for (int z = 0; z < 5; z++) {
                layoutHorizontal.addView(new LetterButton(layoutHorizontal.getContext(), String.valueOf(letters[count++])));
            }
            lettersLayout.addView(layoutHorizontal);
        }
    }

    private void encodePassword() {
        encoded_password = "";
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) != ' ' && password.charAt(i) != '!') {
                encoded_password += "_";
            } else if (password.charAt(i) == '!') {
                encoded_password += "!";
            } else {
                encoded_password += " ";
            }
        }
        password_tv.setText(encoded_password);
    }

    public static boolean checkLetter(String letter) {
        boolean flag = false;
        for (int i = 0; i < encoded_password.length(); i++) {
            if (String.valueOf(password.charAt(i)).equals(letter)) {
                StringBuilder tempPaswd = new StringBuilder(encoded_password);
                tempPaswd.setCharAt(i, letter.charAt(0));
                encoded_password = tempPaswd.toString();
                flag = true;
            }
        }
        password_tv.setText(encoded_password);
        return flag;
    }

    public static void resetToStart() {
        GAME_PLAYING = true;
        endLayout.setVisibility(View.GONE);
        thisScreen.generatePassword();
        thisScreen.encodePassword();
        thisScreen.createLetters();
        password_tv.setText(encoded_password);
        TRY_COUNT = 0;
        thisScreen.wisi_iv.setImageResource(images.getResourceId(TRY_COUNT, -1));
    }


    public static void checkWin() {
        if (encoded_password.equals(password)) {
            winToast.show();
            endGame(true);
        }
    }

    public static void wrongLetter() {
        thisScreen.wisi_iv.setImageResource(images.getResourceId(++TRY_COUNT, -1));
        if (TRY_COUNT >= 9) {
            loseToast.show();
            endGame(false);
        }
    }

    /*
    * @ create retry panel
    * @ Variables
    *  - status - the status of completion of the game || true - win | false - lose
    * */
    public static void endGame(boolean status) {
        GAME_PLAYING = false;
        disable(lettersLayout);
        if (status) {
            end_tv.setText("Odgadnieto haslo! Pomylek: " + TRY_COUNT);
            end_tv.setTextColor(Color.GREEN);
        } else {
            end_tv.setText("Przegrana, haslo: \"" + password + "\"");
            end_tv.setTextColor(Color.RED);
        }
        endLayout.setVisibility(View.VISIBLE);
        endLayout.startAnimation(fadeInAnimation);
    }

    private static void disable(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                disable((ViewGroup) child);
            } else {
                child.setEnabled(false);
            }
        }
    }
}
