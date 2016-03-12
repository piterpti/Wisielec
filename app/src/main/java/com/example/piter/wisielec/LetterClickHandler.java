package com.example.piter.wisielec;

import android.graphics.Color;
import android.view.View;

/**
 * Created by Piter on 12/03/2016.
 */
public class LetterClickHandler implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if(! MainScreen.GAME_PLAYING) {
            return;
        }
        LetterButton button = (LetterButton) v;
        String letter = button.getLetter();
        button.setOnClickListener(null);

        if( MainScreen.checkLetter(letter)) {
            button.setBackgroundColor(LetterButton.CORRECT_COLOR);
            MainScreen.checkWin();
        } else {
            MainScreen.wrongLetter();
            button.setBackgroundColor(LetterButton.INCORRECT_COLOR);
        }


    }
}
