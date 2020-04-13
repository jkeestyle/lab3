package stu.cn.ua.lab3;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class GameActivity extends AppCompatActivity {
    private TextView answer;

    public static final String FIRST_NAME = "FIRST NAME";
    public static final String LAST_NAME = "LAST NAME";
    public static final String GENDER = "GENDER";
    public static final String BIRTHDAY = "BIRTHDAY";

    private static final String KEY_ANSWER = "ANSWER";

    private String ans;
    private int total;

    private static final String[] ANSWERS = new String[] {
            "Yes",
            "No",
            "Perhaps",
            "May well be",
            "Unlikely",
            "Don't know"
    };
    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentDateTime=(TextView)findViewById(R.id.currentDateTime);
        setInitialDateTime();

        EditText edit_question = findViewById(R.id.question);

        int first_name = getIntent().getIntExtra(FIRST_NAME,0);
        int last_name = getIntent().getIntExtra(LAST_NAME,0);
        int gender = getIntent().getIntExtra(GENDER,0);
        int birthday = getIntent().getIntExtra(BIRTHDAY,0);

        Button button = findViewById(R.id.answerButton);
        answer = findViewById(R.id.answer);
        button.setOnClickListener(v -> {

            int question = edit_question.length();
            if(question != 0) {
                total = first_name + last_name + gender + birthday + question;
                if (total % 6 == 0) {
                    ans = ANSWERS[0];
                } else if (total % 6 == 1) {
                    ans = ANSWERS[1];
                } else if (total % 6 == 2) {
                    ans = ANSWERS[2];
                } else if (total % 6 == 3) {
                    ans = ANSWERS[3];
                } else if (total % 6 == 4) {
                    ans = ANSWERS[4];
                } else if (total % 6 == 5) {
                    ans = ANSWERS[5];
                }

                initTextView();
            } else {
                Toast.makeText(this,"Error , enter correct question",Toast.LENGTH_SHORT).show();
            }
        });
    if(savedInstanceState != null) {
        ans = savedInstanceState.getString(KEY_ANSWER,"");
    }
    initTextView();
    }
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_ANSWER,ans);
    }

    private void initTextView() {
        answer.setText(ans);
    }
}
