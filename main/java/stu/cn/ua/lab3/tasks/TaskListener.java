package stu.cn.ua.lab3.tasks;

public interface TaskListener<T> {

    void onSuccess(T result);

    void onError(Throwable error);
}
