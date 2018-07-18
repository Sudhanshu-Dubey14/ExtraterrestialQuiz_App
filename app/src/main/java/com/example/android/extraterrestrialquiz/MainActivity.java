package com.example.android.extraterrestrialquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int result = 0;
    int attempted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitResult(View view) {
        singleChoice((RadioGroup) findViewById(R.id.Que1), (RadioButton) findViewById(R.id.Que1_Opt3));

        multipleChoice(R.id.Que2_Opt1, R.id.Que2_Opt2, R.id.Que2_Opt3, R.id.Que2_Opt4, 2, R.id.Que2_Opt1, R.id.Que2_Opt3, 0, 0);

        textAnswer(R.id.Ans3, "goldilocks zone", "habitable zone", "circumstellar habitable zone");

        singleChoice((RadioGroup) findViewById(R.id.Que4), (RadioButton) findViewById(R.id.Que4_Opt3));

        multipleChoice(R.id.Que5_Opt1, R.id.Que5_Opt2, R.id.Que5_Opt3, R.id.Que5_Opt4, 3, R.id.Que5_Opt1, R.id.Que5_Opt3, R.id.Que5_Opt4, 0);

        textAnswer(R.id.Ans6, "Pulsar", "pulsating star", "pulsars");

        singleChoice((RadioGroup) findViewById(R.id.Que7), (RadioButton) findViewById(R.id.Que7_Opt4));

        multipleChoice(R.id.Que8_Opt1, R.id.Que8_Opt2, R.id.Que8_Opt3, R.id.Que8_Opt4, 3, R.id.Que8_Opt1, R.id.Que8_Opt2, R.id.Que8_Opt4, 0);

        textAnswer(R.id.Ans9, "Nebula", "Nimbus", "Star Forming Regions");

        singleChoice((RadioGroup) findViewById(R.id.Que10), (RadioButton) findViewById(R.id.Que10_Opt2));

        Toast.makeText(this, getString(R.string.Result, result), Toast.LENGTH_LONG).show();

        if (attempted != 10)
            Toast.makeText(this, getString(R.string.attemptedMsg, attempted), Toast.LENGTH_LONG).show();

        result = 0;
        attempted = 0;

    }

    public void singleChoice(RadioGroup que, RadioButton correctOpt) {
        if (que.getCheckedRadioButtonId() != -1) {
            attempted++;
            RadioButton checked = (RadioButton) findViewById(que.getCheckedRadioButtonId());
            if (checked == correctOpt)
                result++;
            else
                checked.setBackgroundColor(Color.argb(100, 213, 0, 0));
        }

        correctOpt.setBackgroundColor(Color.argb(100, 100, 221, 23));

    }

    public void multipleChoice(int opt1_id, int opt2_id, int opt3_id, int opt4_id, int noOfCorrect, int correct1_id, int correct2_id, int correct3_id, int correct4_id) {
        CheckBox mcq_opts[] = {(CheckBox) findViewById(opt1_id), (CheckBox) findViewById(opt2_id), (CheckBox) findViewById(opt3_id), (CheckBox) findViewById(opt4_id)};
        CheckBox correct_opts[] = {(CheckBox) findViewById(correct1_id), (CheckBox) findViewById(correct2_id), (CheckBox) findViewById(correct3_id), (CheckBox) findViewById(correct4_id)};
        int correct = 0;
        for (int i = 0; i < 4; i++) {
            int incorrect = 0;
            for (int j = 0; j < noOfCorrect; j++)
                if (mcq_opts[i].isChecked()) {
                    if (mcq_opts[i] == correct_opts[j])
                        correct++;
                    else {
                        incorrect++;
                        mcq_opts[i].setBackgroundColor(Color.argb(100, 213, 0, 0));
                    }
                }
            if (incorrect == noOfCorrect)
                correct = 0;
        }
        for (int k = 0; k < noOfCorrect; k++)
            correct_opts[k].setBackgroundColor(Color.argb(100, 100, 221, 23));
        if (correct == noOfCorrect)
            result++;
        if (mcq_opts[0].isChecked() || mcq_opts[1].isChecked() || mcq_opts[2].isChecked() || mcq_opts[3].isChecked())
            attempted++;

    }

    public void textAnswer(int answer_id, String keyword1, String keyword2, String keyword3) {
        EditText answerView = (EditText) findViewById(answer_id);
        String enteredText = answerView.getText().toString().toLowerCase();
        if (answerView.getText().toString().trim().length() > 0)
            attempted++;
        if (enteredText.contains(keyword1.toLowerCase()) || enteredText.contains(keyword2.toLowerCase()) || enteredText.contains(keyword3.toLowerCase())) {
            result++;
            answerView.setBackgroundColor(Color.argb(100, 100, 221, 23));
        } else
            answerView.setBackgroundColor(Color.argb(100, 213, 0, 0));
    }

    public void reset(View view) {
        setContentView(R.layout.activity_main);
        result = 0;
        attempted = 0;
    }
}
