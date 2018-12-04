package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread implements Runnable {
    static int i = 0;
    @Override
    public void run() {

        increace();
    }

    public  synchronized void increace(){
        for (int j=0; j<1 ;j++){

            i++;
            System.out.println(Thread.currentThread().getName()+ ":" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        System.out.println("---------pool--------");
//        for (int i=0;i<4 ;i++){
//            Integer index = i;
//
//            System.out.println(i + "---");
//          //  executorService.execute(new MyThread(index));
//        }
//        executorService.shutdown();
        TestThread testThread = new TestThread();
        TestThread testThread1 = new TestThread();
        Thread thread = new Thread(testThread);
        Thread thread1 = new Thread(testThread1);
//        Thread thread2 = new Thread(testThread);

      //  System.out.println(thread1.getName());
        thread.start();
        thread1.start();
//        thread2.start();
//
        try {
            thread.join();
            thread1.join();
 //           thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);





    }
}
