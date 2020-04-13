package stu.cn.ua.lab3.tasks;

import android.os.Handler;
import android.os.Looper;

public abstract class BaseTask<T> implements Task<T> {

    private TaskListener<T> taskListener;

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private boolean executed;
    private boolean cancelled;

    @Override
    public void execute(TaskListener<T> listener) {
        if (executed) throw new RuntimeException("Task has been already executed");
        if (cancelled) return;
        executed = true;
        this.taskListener = listener;
        start();
    }

    @Override
    public void cancel() {
        if (!cancelled) {
            cancelled = true;
            taskListener = null;
            onCancelled();
        }
    }

    protected final void publishSuccess(T result) {
        runOnMainTread(() ->{
            if (taskListener != null) {
                taskListener.onSuccess(result);
                taskListener = null;
            }
        });
    }

    protected final void publishError(Throwable error) {
        runOnMainTread(() ->{
            if (taskListener != null) {
                taskListener.onError(error);
                taskListener = null;
            }
        });
    }

    private void runOnMainTread(Runnable action) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            action.run();
        }else {
            HANDLER.post(action);
        }
    }

    protected abstract void start();

    protected abstract void onCancelled();
}
