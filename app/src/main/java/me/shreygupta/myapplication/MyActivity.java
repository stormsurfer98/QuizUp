package me.shreygupta.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

public class MyActivity extends Activity {
    private Button button01;
    private Button button02;
    private TextView textView01;
    private TextView textView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        button01 = (Button)findViewById(R.id.rules);
        button02 = (Button)findViewById(R.id.mathQuiz);
        textView01 = (TextView)findViewById(R.id.highScore);
        textView02 = (TextView)findViewById(R.id.appName);
        textView02.setTextSize(40);
        final Intent intent01 = new Intent(this, Instructions.class);
        final Intent intent02 = new Intent(this, MathQuiz.class);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent01);
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent02);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("scores.xml", MODE_PRIVATE);
        int highScore = sharedPref.getInt("score", 0);
        textView01.setText("High Score: " + highScore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
