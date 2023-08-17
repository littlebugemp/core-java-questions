/*
* Print Odd and Even number using two threads.
* First thread will print only even numbers and Second Thread Will print only odd number.
* Below implementation is using java 7, Runnable interface and synchronized method with intra communication of threads.
* */

public class EvenOddPrinterUsing2Threads implements Runnable {

    public EvenOddPrinterUsing2Threads(Object object) {
        this.object = object;
    }

    static Object object = new Object();
    static int count = 1;

    @Override
    public void run() {

            while (count<10){
                if(count%2==0 && Thread.currentThread().getName().equals("even")){
                    synchronized (object) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " :" + count);
                        count++;
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(count%2!=0 && Thread.currentThread().getName().equals("odd")){
                    synchronized (object){
                        System.out.println("Thread "+Thread.currentThread().getName() + " :"+count );
                        count++;
                        object.notify();
                    }

                }
            }
        }


    public static void main(String[] args) {
        Runnable r1 = new EvenOddPrinterUsing2Threads(object);
        Runnable r2 = new EvenOddPrinterUsing2Threads(object);
        new Thread(r1,"even").start();
        new Thread(r2,"odd").start();

    }
}
