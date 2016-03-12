package com.example.piter.wisielec;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LetterButton extends Button{

    public final static int DEFAULT_COLOR = Color.argb(255, 100 ,100 ,100);
    public final static int CORRECT_COLOR = Color.argb(255, 0 ,255 ,0);
    public final static int INCORRECT_COLOR = Color.argb(255, 255 ,0 ,0);

    private String letter;

    public LetterButton(Context context, String letter) {
        super(context);
        setTypeface(Typeface.create("Monospace", Typeface.NORMAL));
        setBackgroundColor(DEFAULT_COLOR);
        setText(letter.toUpperCase());
        this.letter = letter;
        setPadding(8, 8, 8, 8);
        setOnClickListener(new LetterClickHandler());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        setLayoutParams(params);
    }

    public String getLetter() {
        return letter;
    }
}