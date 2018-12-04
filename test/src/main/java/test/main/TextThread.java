package test.main;

import org.mortbay.thread.ThreadPool;
import test.model.UsMo;
import test.services.GetFile;
import test.services.LoadConfig;
import test.services.TThread;
import test.thread.MyThread;
import test.thread.TestThread;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TextThread{
//    public void run(){
//        while (true) {
//            System.out.println(Thread.currentThread().getName() );
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void test(){
//        MyThread myThread = new MyThread();
//        //ExecutorService executorService = Executors.newCachedThreadPool();
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//
//        executorService.schedule(myThread,2, TimeUnit.SECONDS);
//        executorService.schedule(myThread,2, TimeUnit.SECONDS);
//
//
//        executorService.shutdown();
//
//
//    }


    private static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
    public static void main(String[] args){




//        TextThread textThread = new TextThread();
//        textThread.test();

//       String aa = "1234567890";
//       aa.indexOf(10);
//       int a = 1;
//       long b = Integer.valueOf(aa);
//
//
//       System.out.println(aa.indexOf("4") + "," + b);

        int a = 1;
        int b = ++a;
        int c = 100;
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Long.toBinaryString(223372036854775807L));
        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
        System.out.println(Long.toBinaryString(Long.MIN_VALUE).length());
        int x = -10;
        //x ^= 12;
        System.out.println(5|6);
        System.out.println(5&6);


//        HashMap hashMap = new HashMap();
//        hashMap.put(100,"aaa");
//        a = hashMap.keySet().iterator().next().hashCode();
//        BigInteger cc = new BigInteger("922337203685477580000000000");
//        int dd = 11;
//        System.out.println(cc);
//
//        BigInteger bbb = BigInteger.valueOf(111111);
//        System.out.println(0.5 / 2);
////        cc = cc^dd;
////       // dd = dd^cc;
////        cc = cc^dd;
//
//        System.out.println(cc);
//        System.out.println(5/2);
//        String aaa = System.getProperty("user.dir");
//
//        String a1 = TextThread.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//
//        String a2 = new File(TextThread.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();
//
//
//        try {
//            a1 = java.net.URLDecoder.decode(a1,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        String jarPath = new File(a1).getParentFile().getAbsolutePath();
//        System.out.println(a1);
//        System.out.println(a2);







   //    LoadConfig loadConfig = new LoadConfig();



//        int num = 4;
//        int[] aaaa = new int[32];
//        for (int i=0;i<32;i++ )
//        {
//
//            aaaa[i]=num%2;
//            System.out.println();
//
//            num/=2;
//            System.out.println(num);
//
//        }

//        for (int j=31;j>=0; j--){
//            System.out.println(aaaa[j]);
//        }
//        List<UsMo> list = new ArrayList();
//        //UsMo usMo = new UsMo();
//
//        for (int i = 0;i<10 ; i++){
//            UsMo usMo = new UsMo();
//            usMo.setId(Long.valueOf(i));
//
//            list.add(usMo);
//
//        }
//
//        for (int m = 0; m<list.size(); m++){
//            System.out.println(list.get(m).getId());
//        }


        ExecutorService executorService = Executors.newCachedThreadPool();

        //ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,1000,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        //ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);
//        for (int i = 0; i< 10 ; i++){
//            //System.out.println(arrayBlockingQueue.remove());
//
////            System.out.println(arrayBlockingQueue.offer(i));
////            System.out.println(arrayBlockingQueue.size());
//            try {
//                arrayBlockingQueue.put(i);
//                System.out.println(arrayBlockingQueue.element());
//                System.out.println("size:" + arrayBlockingQueue.size());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        arrayBlockingQueue.offer(0);

//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.size());
        InsertThread insertThread = new InsertThread();
        Couseume couseume = new Couseume();
//        List<Callable<String>> list = new ArrayList<Callable<String>>();
//        InvokeTest invokeTest = new InvokeTest("xx");
//        list.add(invokeTest);
//        try {
//            List<Future<String>> future = executorService.invokeAll(list,1,TimeUnit.SECONDS);
//
//
//            for (Future<String> f:future){
//                System.out.println(f.isCancelled());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        executorService.execute(insertThread);
//        executorService.execute(couseume);
        System.out.println("--------------" + executorService.isShutdown());
        System.out.println(executorService.isTerminated());
//        arrayBlockingQueue.peek()





        ArrayList arrayList = GetFile.getFile("/Users/qufenqi/Documents/campus/campus_proce/split-id/uid");
        for (int i = 0 ;i<arrayList.size();i++){
         //   System.out.println(arrayList.get(i));
            //executorService.execute(new TThread(String.valueOf(i), (String) arrayList.get(i)));


        }

        Cosumer couseume1 = new Cosumer();
        Producer producer = new Producer();
        executorService.execute(couseume1);
        executorService.execute(producer);

    }


    public static void insert(int i) throws InterruptedException {
        arrayBlockingQueue.put(i);
        arrayBlockingQueue.offer(i);

        System.out.println("capcity:" + arrayBlockingQueue.size());
    }

    public static void cosumer() throws InterruptedException {
        System.out.println(arrayBlockingQueue.size());
        //System.out.println("cosu:" + arrayBlockingQueue.remove());
    }



    public static void cosumer1(){
        while (true){
            synchronized (arrayBlockingQueue){
                while (arrayBlockingQueue.size()==0){
                    try {
                        System.out.println("队列为空");
                        arrayBlockingQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        arrayBlockingQueue.notify();
                    }
                }
                System.out.println("取元素：" + arrayBlockingQueue.poll());
                arrayBlockingQueue.notify();
            }
        }
    }

    public static void producer(){
        while (true){
            synchronized (arrayBlockingQueue){
                while (arrayBlockingQueue.size()==5){
                    try {
                        System.out.println("生产队列已满");
                        arrayBlockingQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                arrayBlockingQueue.offer(1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                arrayBlockingQueue.notify();
            }
        }
    }

}

class Cosumer implements Runnable{

    @Override
    public void run() {
        TextThread.cosumer1();
    }
}

class Producer implements Runnable{

    @Override
    public void run() {
        TextThread.producer();
    }
}


class InsertThread implements Runnable {
    public InsertThread(){

    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            try {
                TextThread.insert(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Couseume implements Runnable{
    public Couseume(){

    }

    @Override
    public void run() {
        while (true){

            try {
                TextThread.cosumer();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
class InvokeTest implements Callable{

    private String name ;
    public InvokeTest(String name){
        this.name= name;
    }
    @Override
    public Object call() throws Exception {
        for (int i =0 ;i<5 ;i++) {
            System.out.println("name:" + name);
            Thread.sleep(1000);
        }
        return null;
    }
}

