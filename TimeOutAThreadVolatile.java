import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeOutAThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        //1: create a task and submit to a thread
        MyTask task = new MyTask();
        Thread thread = new Thread(task);
        thread.start();

        //2: timeout for 10 minutes
        Thread.sleep(1000 * 60 * 10);
//        //or
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//        executorService.schedule(()->{
//            task.keepRunning = false;
//        },10, TimeUnit.MINUTES);

        //3: ask task to stop using volatile
        task.keepRunning = false;
    }

    static class MyTask implements Runnable{
        public volatile boolean keepRunning = true;
        @Override
        public void run() {
            while(keepRunning){
                //do some task here
            }
        }
    }
}
