package test.services;

import java.io.*;

public class TThread implements Runnable {
    private String threadName ;
    private String fileName;
    public TThread(String threadName,String fileName){
        this.threadName=threadName;
        this.fileName = fileName;
    }
    @Override
    public void run() {
        //File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String a = null;
            System.out.println(Thread.currentThread().getContextClassLoader().toString());
            while ((a = bufferedReader.readLine()) != null){
                System.out.println(threadName + "==" +a.trim());
                Thread.sleep(10);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
