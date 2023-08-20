import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern {
    public static void main(String[] args) {

        BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);
        //producer
        final Runnable producer = ()->{
            while (true){
                try {
                    queue.put(createItem());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        //consumer
        final Runnable consumer = () ->{
          while (true){
              try {
                  Item i = queue.take();
                  process(i);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();

    }

    private static Item createItem() {
        return null;
    }

    private static void process(Item item) {
        System.out.println("Item processed" + item);
    }

    class Item{
        private Integer type;
    }
}
