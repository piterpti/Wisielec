package com.example.piter.wisielec;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LetterButton extends Button{

    public final static int DEFAULT_COLOR = Color.argb(255, 100 ,100 ,100);
    public final static int CORRECT_COLOR = Color.argb(255, 0 ,255 ,0);
    public final static int INCORRECT_COLOR = Color.argb(255, 255 ,0 ,0);
    public final static int TEXT_COLOR = Color.argb(255, 230 ,230 ,230);

    private String letter;

    public LetterButton(Context context, String letter) {
        super(context);
        setTypeface(Typeface.create("monospace", Typeface.BOLD));
        setBackgroundColor(DEFAULT_COLOR);
        setTextColor(TEXT_COLOR);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        setText(letter.toUpperCase());
        this.letter = letter;
        setPadding(8, 20, 8, 20);
        setOnClickListener(new LetterClickHandler());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER_HORIZONTAL;

        setLayoutParams(params);
    }

    public String getLetter() {
        return letter;
    }
}
