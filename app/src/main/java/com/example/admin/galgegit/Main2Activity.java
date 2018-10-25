package com.example.admin.galgegit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "galge Activity 2";

    TextView textViewTitle, textViewWord, textViewErrors, textViewLettersUsed;
    EditText editTextGuess;
    Button buttonGuess, buttonBack;
    ArrayList<String> lettersWrong = new ArrayList<>();
    ImageView imageViewHangingMan;

    GalgeLogik galgeLogik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewTitle = (TextView) findViewById(R.id.textViewtitle);
        textViewWord = (TextView) findViewById(R.id.textViewWord);
        textViewErrors = (TextView) findViewById(R.id.textViewErrors);
        textViewLettersUsed = (TextView) findViewById(R.id.textViewLettersUsed);
        editTextGuess = (EditText) findViewById(R.id.editTextGuess);
        buttonGuess = (Button) findViewById(R.id.buttonGuess);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        imageViewHangingMan = (ImageView) findViewById(R.id.imageViewHangingMan);

        galgeLogik = new GalgeLogik();
        galgeLogik.nulstil();

        textViewWord.setText(galgeLogik.getSynligtOrd());

        textViewErrors.setText("" + galgeLogik.getAntalForkerteBogstaver() + " / 7 fejl.");

        buttonGuess.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String letterGuessed = "";
        if (v == buttonGuess) {
            letterGuessed = editTextGuess.getText().toString();
            letterGuessed = letterGuessed.toLowerCase();
            Log.i(TAG, "onClick: letter has been guessed on : " + letterGuessed);
            editTextGuess.getText().clear();
            galgeLogik.gætBogstav(letterGuessed);
            isGameFinished();
            guess(letterGuessed);

            Log.i(TAG, "onClick: used letters " + galgeLogik.getBrugteBogstaver());
            Log.i(TAG, "onClick: wrong letters " + lettersWrong.toString());
            galgeLogik.logStatus();
        }

        if (v == buttonBack) {
            Log.i(TAG, "onClick: finish activity 2");
            finish();
        }
    }

    private void guess(String letterGuessed) {
        if (galgeLogik.erSidsteBogstavKorrekt()) {
            Log.i(TAG, "onClick: correct guess");
            textViewWord.setText(galgeLogik.getSynligtOrd());
        } else {
            Log.i(TAG, "onClick: wrong guess");
            if (!letterGuessed.isEmpty() && !lettersWrong.contains(letterGuessed.toString())) {
                lettersWrong.add(letterGuessed);
            }
            textViewErrors.setText("" + galgeLogik.getAntalForkerteBogstaver() + " / 7 fejl.");
            textViewLettersUsed.setText(lettersWrong.toString());
            changeImage(galgeLogik.getAntalForkerteBogstaver());
        }
    }

    private void isGameFinished() {
        if (galgeLogik.erSpilletSlut()) {
            if (galgeLogik.erSpilletVundet()) {
                buttonGuess.setText("Du har vundet!");
                buttonGuess.setOnClickListener(null);
                hideKeyboard(this);
                startAnimationWon();
            } else

            if (galgeLogik.erSpilletTabt()) {
                buttonGuess.setText("Du har tabt!");
                hideKeyboard(this);
                buttonGuess.setOnClickListener(null);
                startAnimationLost();
            }
        }
    }

    private void startAnimationWon() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);
        animationSet.setFillEnabled(true);

        buttonGuess.startAnimation(animationSet);
    }

    private void startAnimationLost() {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation t = new TranslateAnimation(0, 0, 0, 200);
        t.setDuration(500);
        t.setFillAfter(true);
        t.setRepeatCount(-1);
        t.setRepeatMode(TranslateAnimation.REVERSE);
        animationSet.addAnimation(t);

        RotateAnimation r = new RotateAnimation(0f, 15f, 0, 0);
        r.setDuration(300);
        r.setRepeatCount(-1);
        r.setRepeatMode(RotateAnimation.REVERSE);
        animationSet.addAnimation(r);
        imageViewHangingMan.startAnimation(animationSet);

    }

    private void changeImage(int antalForkerteBogstaver) {
        switch (antalForkerteBogstaver) {
            case 1:
                imageViewHangingMan.setImageResource(R.drawable.wrong2);
                break;
            case 2:
                imageViewHangingMan.setImageResource(R.drawable.wrong3);
                break;
            case 3:
                imageViewHangingMan.setImageResource(R.drawable.wrong4);
                break;
            case 4:
                imageViewHangingMan.setImageResource(R.drawable.wrong5);
                break;
            case 5:
                imageViewHangingMan.setImageResource(R.drawable.wrong6);
                break;
            case 6:
               imageViewHangingMan.setImageResource(R.drawable.wrong7);
               startAnimationAlmostLost();
                break;
            case 7:
               imageViewHangingMan.setImageResource(R.drawable.wrong7);
                break;
        }
    }

    private void startAnimationAlmostLost() {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation r = new RotateAnimation(-1f, 1f, 0.5f, 0.5f);
        r.setDuration(30);
        r.setRepeatCount(-1);
        r.setRepeatMode(RotateAnimation.REVERSE);
        animationSet.addAnimation(r);
        imageViewHangingMan.startAnimation(animationSet);

    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}