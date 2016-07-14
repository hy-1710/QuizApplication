package com.hfad.joke.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Question> quesList;
    int score;
    int qid;
    Question currentQ;
    TextView txtQuestion, scored;
    RadioGroup radioGroup;
    RadioButton r1, r2, radioButton;
    Button next;
    private  static final  String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {

            qid=savedInstanceState.getInt("qid",0);
            score= savedInstanceState.getInt("score",0);
            Log.i(TAG, "onCreate: after rotate qid" +qid);
        }

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        scored = (TextView) findViewById(R.id.score);
        radioGroup = (RadioGroup) findViewById(R.id.r_grp);
        r1 = (RadioButton) findViewById(R.id.op_one);
        r2 = (RadioButton) findViewById(R.id.op_two);
        next = (Button) findViewById(R.id.next);
        QuizQuestion db = new QuizQuestion(this);
        quesList = db.gatAllQuestion();
        currentQ = quesList.get(qid);
        setQuestionView();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                //String value= (String) radioButton.getText();
                if (radioButton == null) {
                    //Toast.makeText(MainActivity.this, "plz select th option", Toast.LENGTH_SHORT).show();
                    getAnswer("skipped");
                } else {
                    getAnswer(radioButton.getText().toString());
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: before rotate"+qid);
        outState.putInt("qid",qid);
        outState.putInt("score",score);


    }

    private void getAnswer(String s) {
        if (currentQ.getANSWER().equals(s)) {

            score++;
            scored.setText("Score : " + score);
            qid++;
        } else {

//            Bundle b = new Bundle();
//            b.putInt("score", score);
//            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//            intent.putExtras(b);
//            startActivity(intent);
//            finish();
        }

        if (qid != quesList.size() - 1) {
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();

        }
    }

    private void setQuestionView() {
        radioGroup.clearCheck();
        txtQuestion.setText(currentQ.getQUESTION());
        r1.setText(currentQ.getOPTION_ONE());
        r2.setText(currentQ.getOPTION_TWO());


        if (qid == quesList.size() - 1) {
            next.setText("Done..!!");
        } else {
            next.setText("Next");
        }
    }


}
