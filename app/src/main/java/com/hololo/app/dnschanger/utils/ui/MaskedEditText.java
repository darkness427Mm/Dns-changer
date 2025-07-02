package com.hololo.app.dnschanger.utils.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText; // <<--- این خط برای حل مشکل اضافه شده است

import java.util.LinkedList;
import java.util.List;

public class MaskedEditText extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener {

    private String mask = "";
    private char charRepresentation = '#';
    private List<String> rawToMask;
    private RawText rawText;
    private boolean editing;
    private int oldStringLength;
    private boolean isDeleting;

    public MaskedEditText(Context context) {
        super(context);
        init();
    }

    public MaskedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setMask(String mask) {
        this.mask = mask;
        rawToMask = new LinkedList<>();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c != charRepresentation) {
                rawToMask.add(String.valueOf(c));
            }
        }
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (editing) return;
                editing = true;

                //
                if (isDeleting) {
                    isDeleting = false;
                    editing = false;
                    return;
                }
                //

                StringBuilder sb = new StringBuilder();
                int current = 0;
                for (char c : mask.toCharArray()) {
                    if (c == charRepresentation && current < s.length()) {
                        sb.append(s.charAt(current));
                        current++;
                    } else if (c != charRepresentation) {
                        sb.append(c);
                    }
                }
                setText(sb.toString());
                setSelection(length());
                editing = false;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldStringLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isDeleting = s.length() < oldStringLength;
            }

        });
    }

    public String getRawText() {
        return getText().toString().replaceAll("[^0-9]", "");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
