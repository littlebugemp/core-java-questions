import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class ScatterGatherUsingCompletableFuture {

    String url1 = "amazon.com";
    String url2 = "ebay.com";
    String url3 = "walmart.com";

    private Set<Integer> getPrices(int productId) throws ExecutionException, InterruptedException, TimeoutException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(url1, productId, prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(url2, productId, prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(url3, productId, prices));
        CompletableFuture<Void> allTasks= CompletableFuture.allOf(task1,task2,task3);
        allTasks.get(3, TimeUnit.SECONDS);
        return prices;
    }

    private class Task implements Runnable{
        private String url;
        private int productId;
        private Set<Integer> prices;

        Task(String url, int productId, Set<Integer> prices){
            //
        }

        @Override
        public void run() {
            int price = 0;
            //make http call
            prices.add(price);
        }
    }
}
