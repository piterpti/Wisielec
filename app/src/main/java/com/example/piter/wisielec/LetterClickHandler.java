package com.example.piter.wisielec;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Piter on 12/03/2016.
 */
public class LetterClickHandler implements View.OnClickListener {

    public static ValueAnimator colorAnimation;

    @Override
    public void onClick(View v) {
        if(! MainScreen.GAME_PLAYING) {
            return;
        }
        final LetterButton button = (LetterButton) v;
        String letter = button.getLetter();
        button.setOnClickListener(null);

        if( MainScreen.checkLetter(letter)) {
            colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), LetterButton.DEFAULT_COLOR, LetterButton.CORRECT_COLOR);
            MainScreen.checkWin();
        } else {
            MainScreen.wrongLetter();
            colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), LetterButton.DEFAULT_COLOR, LetterButton.INCORRECT_COLOR);
        }
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }
}
