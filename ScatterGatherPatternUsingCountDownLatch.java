import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScatterGatherPatternUsingCountDownLatch {

    ExecutorService pool = Executors.newFixedThreadPool(3);
        String url1 = "amazon.com";
        String url2 = "ebay.com";
        String url3 = "walmart.com";

    private Set<Integer> getPrices(int productId) throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(3);
        pool.submit(new Task(url1,productId,prices,latch));
        pool.submit(new Task(url2,productId,prices,latch));
        pool.submit(new Task(url3,productId,prices,latch));

        latch.await(3, TimeUnit.SECONDS);

        return prices;
    }


    private class Task implements Runnable{
        private String url;
        private int productId;
        private Set<Integer> prices;
        private CountDownLatch latch;

        Task(String url, int productId, Set<Integer> prices, CountDownLatch latch){
            //
        }

        @Override
        public void run() {
            int price = 0;
            //make http call
            prices.add(price);
            latch.countDown();
        }
    }
}
