/*
 * Print Odd and Even number using two threads.
 * First thread will print only even numbers and Second Thread Will print only odd number.
 * Below implementation is using Executor Service
 * */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class EvenOddPrinterUsingES {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);

        IntStream.rangeClosed(1,10).forEach(e->{
            CompletableFuture<Integer> oddCF = CompletableFuture.completedFuture(e)
                    .thenApplyAsync(x->{
                       if(x%2!=0){
                           System.out.println("Thread Name "+ Thread.currentThread().getName() + " "+x);
                       }
                       return e;
                    },service);
            oddCF.join();
            CompletableFuture<Integer> evenCF = CompletableFuture.completedFuture(e)
                    .thenApplyAsync(x->{
                        if(x%2==0){
                            System.out.println("Thread Name "+ Thread.currentThread().getName() + " "+x);
                        }
                        return e;
                    },service);
            evenCF.join();
        });
        service.shutdown();
    }
}
