package com.example.admin.mydailyexpenses;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4;

    TextView score, question;
    private QuestionLibrary mQuestions =  new QuestionLibrary();

    //    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
//
//    private TextView mScoreView;
//    private TextView mQuestionView;
//    private Button mButtonChoice1;
//    private Button mButtonChoice2;
//    private Button mButtonChoice3;
//
    private String mAnswer;
    private int mScore = 0;


    private int mQuestionLibraryLenght = mQuestions.mQuestions.length;
    Random r;
//    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        r= new Random();


        answer1 = (Button)findViewById(R.id.choice1);
        answer2 = (Button)findViewById(R.id.choice2);
        answer3 = (Button)findViewById(R.id.choice3);
        answer4 = (Button)findViewById(R.id.choice4);



        score = (TextView)findViewById(R.id.score);
        question = (TextView)findViewById(R.id.question);

        score.setText("Score: " +mScore);


        updateQuestion(r.nextInt(mQuestionLibraryLenght));

        //Start of Button Listener for Button1
        answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (answer1.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " +mScore);
                    updateQuestion(r.nextInt(mQuestionLibraryLenght));

                }else {
                    gameOver();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (answer2.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " +mScore);
                    updateQuestion(r.nextInt(mQuestionLibraryLenght));

                }else {
                    gameOver();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (answer3.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " +mScore);
                    updateQuestion(r.nextInt(mQuestionLibraryLenght));

                }else {
                    gameOver();
                }
            }
        });

        //End of Button Listener for Button3
        answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                gameOver();
            }
        });




    }

    private void updateQuestion(int num){
        question.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));

        mAnswer = mQuestions.getCorrectAnswer(num);

    }


//    private void updateScore(int point) {mScoreView.setText("" + mScore);}

    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder
                .setMessage("Game Over! Your score is " +mScore+" points.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(),QuizActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                finish();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
