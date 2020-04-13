package stu.cn.ua.lab3.tasks;

public interface Task<T> {
    void execute(TaskListener<T> listener);

    void cancel();
}
