package test.thread;


public class MyThread implements Runnable {

    @Override
    public void run(){
        for (int i = 0; i<=6 ;i++){
            System.out.println(Thread.currentThread().getName() + " is running .. " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
