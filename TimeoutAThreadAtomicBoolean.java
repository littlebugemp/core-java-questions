import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutAThreadAtomicBoolean {

    public static void main(String[] args) {
        TimeoutAThreadAtomicBoolean aBoolean = new TimeoutAThreadAtomicBoolean();
        aBoolean.process();
    }

    public void process(){
        //1: create a thread
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();
        //2: timeout for 10 minutes
        try {
            Thread.sleep(1000 * 60*10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //3: stop the thread
        task.keepRunning.set(false);
    }


    class Task implements Runnable{
        AtomicBoolean keepRunning = new AtomicBoolean();
        @Override
        public void run() {
            while(keepRunning.equals(true)){
                //do some task
            }
        }
    }
}
