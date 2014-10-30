package me.shreygupta.myapplication;

/**
 * Created by Shrey on 9/16/14.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.lang.Math;
import android.content.*;
import android.app.AlertDialog;

public class MathQuiz extends Activity{
    private TextView textView01;
    private TextView textView02;
    private Button button01;
    private EditText editText01;
    private AlertDialog alertDialog;
    private int answer;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_math);
        answer = newQuestion();
        textView02 = (TextView)findViewById(R.id.score);
        button01 = (Button)findViewById(R.id.submit);
        editText01 = (EditText)findViewById(R.id.answer);
        alertDialog = new AlertDialog.Builder(this).create(); //http://www.wikihow.com/Show-Alert-Dialog-in-Android
        alertDialog.setTitle("You lost!");
        alertDialog.setMessage("You lost! Try again next time!");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText01.getText().toString().equals("")) {
                    if ((Integer) Integer.parseInt(editText01.getText().toString()) == answer) {
                        answer = newQuestion();
                        editText01.setText("");
                        score += 1;
                        textView02.setText("Score: " + ((Integer) (score)).toString());
                    } else {
                        SharedPreferences sharedPref = getSharedPreferences("scores.xml", MODE_PRIVATE);
                        int highScore = sharedPref.getInt("score", 0);
                        if (score > highScore) {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("score", score);
                            editor.apply();
                            alertDialog.setTitle("New high score!");
                            alertDialog.setMessage("You have a new high score of " + score + "! Try again next time!");
                        }
                        alertDialog.show();
                    }
                }
            }
        });
    }

    private int newQuestion() {
        String[] operators = new String[3];
        operators[0] = "+"; operators[1] = "-"; operators[2] = "*";
        int num1 = (int)(Math.random()*(100));
        int num2 = (int)(Math.random()*(100));
        String op = operators[(int)(Math.random()*(3))];
        textView01 = (TextView)findViewById(R.id.question);
        textView01.setText(new Integer(num1).toString() + " " + op + " " + new Integer(num2).toString());
        if(op.equals("+")) {
            return num1 + num2;
        }
        if(op.equals("-")) {
            return num1 - num2;
        }
        return num1 * num2;
    }
}