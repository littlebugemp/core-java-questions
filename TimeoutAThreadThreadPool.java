import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeoutAThreadThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        //1: start the task
        service.execute(()->{
            while(!Thread.currentThread().isInterrupted()){
                return;
            }
        });
        //2: timeout for 10 minutes
        try {
            Thread.sleep(1000 * 60 *10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //3: stop the thread
        service.shutdownNow();
    }
}
