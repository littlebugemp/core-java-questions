import java.io.ObjectStreamException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PDPatternUsingWaitNotifyAll {

    public class MyBlockingQueue<E>{
        private Queue<E> queue;
        private int max = 15;
        private ReentrantLock lock = new ReentrantLock(true);
        private Object notFull = new Object();
        private Object notEmpty= new Object();

        public MyBlockingQueue(int size){
            queue = new LinkedList<>();
            this.max = size;
        }

        public synchronized void put(E e) throws InterruptedException {
                while(queue.size() == max){
                    notFull.wait();
                }
                queue.add(e);
                notEmpty.notifyAll();
        }

        public E take() throws InterruptedException {
                while (queue.size() == 0){
                    notEmpty.wait();
                }
                E item = queue.remove();
                notFull.notifyAll();
                return item;
        }
    }
}
