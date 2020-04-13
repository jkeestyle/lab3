package stu.cn.ua.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText edit_first_name = findViewById(R.id.first_name);
        EditText edit_last_name = findViewById(R.id.last_name);
        RadioGroup edit_gender = findViewById(R.id.radioGroup);
        DatePicker edit_birthday = findViewById(R.id.datePicker);

        findViewById(R.id.save).setOnClickListener(v ->{

            String correct_first_name = edit_first_name.getText().toString();
            String correct_last_name = edit_last_name.getText().toString();

            int first_name = edit_first_name.getText().hashCode();
            int last_name = edit_last_name.getText().hashCode();
            int gender = edit_gender.getCheckedRadioButtonId();
            int birthday = edit_birthday.getDayOfMonth() + edit_birthday.getMonth() + edit_birthday.getYear();
            if(correct_first_name.isEmpty() || correct_last_name.isEmpty()) {
                Toast.makeText(this,"Error , wrong fields",Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(GameActivity.FIRST_NAME, first_name);
                intent.putExtra(GameActivity.LAST_NAME, last_name);
                intent.putExtra(GameActivity.GENDER, gender);
                intent.putExtra(GameActivity.BIRTHDAY, birthday);

                startActivity(intent);
            }
        });
    }
}
