package com.hfad.joke.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView result= (TextView)findViewById(R.id.textResult);
        Bundle b= getIntent().getExtras();
        int score = b.getInt("score");

        result.setText("Well played...!! Your Score is " +score + " Thanks For Playing...!!");


    }
    public void playagain(View o) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
