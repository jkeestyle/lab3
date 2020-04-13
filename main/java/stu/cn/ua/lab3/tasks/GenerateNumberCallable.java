package stu.cn.ua.lab3.tasks;

import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateNumberCallable implements Callable<Integer> {
    private int bottom;
    private int top;

    public GenerateNumberCallable(int bottom,int top) {
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        Thread.sleep(5000);
        int number = bottom + random.nextInt(top - bottom);
        return number;
    }
}
