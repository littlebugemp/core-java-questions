import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenOddPrinterUsingIntPredicate {

    private static Object object = new Object();
    private static IntPredicate evenCondition = e->e%2==0;
    private static IntPredicate oddCondition = e->e%2!=0;

    public static void main(String[] args) {
        CompletableFuture.runAsync(()->printResults(evenCondition));
        CompletableFuture.runAsync(()->printResults(oddCondition));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printResults(IntPredicate condition){
        IntStream.rangeClosed(1,10)
                .filter(condition).forEach(EvenOddPrinterUsingIntPredicate::execute);
    }

    public static void execute(int i){
        synchronized (object){
            System.out.println("Thread Name "+ Thread.currentThread().getName() + " "+ i);
            object.notify();
            try {
                object.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

