package Model;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingDeque<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingDeque<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getProcessingTime());
    }

    public void run() {
        while (true) {
            Task task = null;
            if (!tasks.isEmpty()) {
                task = tasks.element();
            }
            if (task != null) {
                waitingPeriod.getAndDecrement();
                task.setProcessingTime(task.getProcessingTime() - 1);
                if (task.getProcessingTime() == 0) {
                    try {
                        tasks.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingDeque<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        String res = "Queue: ";
        if (tasks.isEmpty()) {
            return res + "closed\n";
        } else {
            for (Task task : tasks) {
                res += task.toString() + " ";
            }
        }
        return res + "\n";
    }

    public AtomicInteger getWaitingPeriod(){
        return waitingPeriod;
    }
}