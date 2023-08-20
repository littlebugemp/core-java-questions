import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*implementation of producer consumer pattern
using custom blocking Queue and Condition class
 */

public class PCPatternUsingCustomBlockingQueue {

    public class MyBlockingQueue<E>{
        private Queue<E> queue;
        private int max = 15;
        private ReentrantLock lock = new ReentrantLock(true);
        private Condition notFull = lock.newCondition();
        private Condition notEmpty= lock.newCondition();

        public MyBlockingQueue(int size){
            queue = new LinkedList<>();
            this.max = size;
        }

        public void put(E e){
            lock.lock();
            try{
                while(queue.size() == max){
                    notFull.await();
                }
                queue.add(e);
                notEmpty.signalAll();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } finally {
                lock.unlock();
            }
        }

        public E take(){
            lock.lock();
            try{
                while (queue.size() == 0){
                    notEmpty.await();
                }
                E item = queue.remove();
                notFull.signalAll();
                return item;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }
    }
}
