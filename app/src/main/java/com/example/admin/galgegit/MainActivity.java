package com.example.admin.galgegit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonPlay, buttonInfo, buttonOther;
    final String TAG = "galge Activity 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = (Button) findViewById(R.id.play);
        buttonInfo = (Button) findViewById(R.id.information);
        buttonOther = (Button) findViewById(R.id.other);

        buttonPlay.setOnClickListener(this);
        buttonInfo.setOnClickListener(this);
        buttonOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonPlay) {
            Log.i(TAG, "onClick: start game");
            startActivity(new Intent(this, Main2Activity.class));
        }
        if (v == buttonInfo) {
            Log.i(TAG, "onClick: info");
            Toast.makeText(this, "information", Toast.LENGTH_SHORT).show();
        }
        if (v == buttonOther) {
            Log.i(TAG, "onClick: other");
            otherAnimation();
            buttonOther.setOnClickListener(null);
        }
    }

    private void otherAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);
        animationSet.setFillEnabled(true);

        buttonOther.startAnimation(animationSet);
    }
}
