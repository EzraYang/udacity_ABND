package com.example.android.generalknowledgequiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scoreStr = calculateScore();

                // show a toast displaying score and corresponding message
                Context context = getApplicationContext();
                String toastMessage = congratsMessage(scoreStr);

                TextView nameField = (TextView) findViewById(R.id.name_field);
                String name = nameField.getText().toString();
                toastMessage += name;

                toastMessage += ", you scored " + scoreStr + "/6!";

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, toastMessage, duration);
                toast.show();

            }
        });
    }

    private String calculateScore() {
        int score = 0;
        String scoreStr = "";

        RadioButton ra1 = (RadioButton) findViewById(R.id.right_ans_1);
        RadioButton ra2 = (RadioButton) findViewById(R.id.right_ans_2);
        RadioButton ra3 = (RadioButton) findViewById(R.id.right_ans_3);
        RadioButton ra4 = (RadioButton) findViewById(R.id.right_ans_4);
        CheckBox ra5a = (CheckBox) findViewById(R.id.right_ans_5a);
        CheckBox ra5b = (CheckBox) findViewById(R.id.right_ans_5b);
        CheckBox wa5a = (CheckBox) findViewById(R.id.wrong_ans_5a);
        CheckBox wa5b = (CheckBox) findViewById(R.id.wrong_ans_5b);
        TextView ansFieldQ6 = (TextView) findViewById(R.id.ans_field_q6);
        String ans6 = ansFieldQ6.getText().toString();

        if (ra1.isChecked()) {
            score++;
        }

        if (ra2.isChecked()) {
            score++;
        }

        if (ra3.isChecked()) {
            score++;
        }

        if (ra4.isChecked()) {
            score++;
        }

        if (ra5a.isChecked() && ra5b.isChecked() && !wa5a.isChecked() && !wa5b.isChecked()) {
            score++;
        }

        if ( ans6.equals("Bern") || ans6.equals("bern") ){
            score++;
        }

        scoreStr += score;

        return scoreStr;

    }

    private String congratsMessage(String scoreStr) {
        String congratsMessage = "";

        switch (scoreStr) {
            case "6":
                congratsMessage = "Awesome, ";
                break;
            case "5":
                congratsMessage = "Awesome, ";
                break;
            case "4":
                congratsMessage = "Nice, ";
                break;
            case "3":
                congratsMessage = "Nice, ";
                break;
            case "2":
                congratsMessage = "Nice, ";
                break;
            default:
                congratsMessage = "Oops, ";
                break;
        }
        return congratsMessage;
    }

}














