import java.util.concurrent.*;

public class TimeoutAThreadFuture {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(2);
        //1: start the task
        Future<?> future = service.submit(()->{
            while(!Thread.currentThread().isInterrupted()){

            }
        });
        //2: timeout for 10 minutes
        try {
            future.get(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        //3: stop the thread (by cancelling the future)
        future.cancel(true);
    }
}
