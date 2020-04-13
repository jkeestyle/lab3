package stu.cn.ua.lab3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements RetainFragment.MainStateListener {

    private RetainFragment retainFragment;

    private Button generateNumberButton;
    private ProgressBar progress;
    private TextView numberTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.startGameButton).setOnClickListener(v -> {
            Intent intent2 = new Intent ( this, GameActivity.class);
            startActivity(intent2);
        });
        findViewById(R.id.settingsButton).setOnClickListener(v -> {
            Intent intent = new Intent ( this, SettingsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.exitButton).setOnClickListener(v -> {
            finish();
        });

        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(RetainFragment.TAG);
        if(retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(retainFragment,RetainFragment.TAG)
                    .commit();
        }

        generateNumberButton = findViewById(R.id.generateNumberButton);
        progress = findViewById(R.id.progress);
        numberTextView = findViewById(R.id.numberTextView);

        generateNumberButton.setOnClickListener(v -> {
            retainFragment.generateNumber(0,100);
        });


        retainFragment.setListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        retainFragment.setListener(null);
    }

    @Override
    public void onNewState(MainViewState state) {
        generateNumberButton.setEnabled(state.isButtonEnabled);
        progress.setVisibility(state.showProgress ? View.VISIBLE : View.GONE);
        numberTextView.setVisibility(state.showResultText ? View.VISIBLE : View.GONE);
        numberTextView.setText(state.result);
    }
}
