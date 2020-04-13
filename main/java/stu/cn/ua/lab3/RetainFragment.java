package stu.cn.ua.lab3;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import stu.cn.ua.lab3.tasks.ExecutorServiceTask;
import stu.cn.ua.lab3.tasks.GenerateNumberCallable;
import stu.cn.ua.lab3.tasks.Task;
import stu.cn.ua.lab3.tasks.TaskListener;

public class RetainFragment extends Fragment {

    public static final String TAG = RetainFragment.class.getSimpleName();

    private MainViewState mainViewState = new MainViewState();
    private MainStateListener listener;

    private Task<Integer> currentTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setListener(MainStateListener listener) {
        this.listener = listener;
        if(listener != null) {
            listener.onNewState(mainViewState);
        }
    }

    public void generateNumber(int bottomBound,int topBound){
        currentTask = createGenerateNumberTask(bottomBound,topBound);

        mainViewState.isButtonEnabled = false;
        mainViewState.showProgress = true;
        mainViewState.showResultText = false;
        mainViewState.result = "";
        updateState();

        currentTask.execute(new TaskListener<Integer>(){
            @Override
            public void onSuccess(Integer result) {
                mainViewState.isButtonEnabled = true;
                mainViewState.showResultText = true;
                mainViewState.result = String.valueOf(result);
                mainViewState.showProgress = false;
                updateState();
            }
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error!",error);
                mainViewState.isButtonEnabled = true;
                mainViewState.showResultText = false;
                mainViewState.result = "";
                mainViewState.showProgress = false;
                updateState();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(currentTask != null) {
            currentTask.cancel();
        }
    }

    private void updateState() {
        if(listener != null) {
            listener.onNewState(mainViewState);
        }
    }

    private Task<Integer> createGenerateNumberTask(int bottomBound, int topBound) {
        return new ExecutorServiceTask<>(new GenerateNumberCallable(bottomBound,topBound));
    }

    public interface MainStateListener {
        void onNewState(MainViewState state);
    }
}
