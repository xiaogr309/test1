package test;

import com.aliyun.oss.OSS;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

    private long lastTimeFileSize = 1000;  //上次文件大小
    /**
     * 实时输出日志信息
     * @param logFile 日志文件
     * @throws IOException
     */

    public void realtimeShowLog(File logFile) throws IOException {
        //指定文件可读可写
        final RandomAccessFile randomFile = new RandomAccessFile(logFile,"rw");
        //启动一个线程每10秒钟读取新增的日志信息
        ScheduledExecutorService exec =
                Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Runnable(){
            public void run() {
                try {
                    //获得变化部分的
                    randomFile.seek(lastTimeFileSize);
                    String tmp = "";
                    while( (tmp = randomFile.readLine())!= null) {
                        System.out.println(new String(tmp.getBytes("ISO8859-1")));
                    }
                    lastTimeFileSize = randomFile.length();
                    System.out.println(lastTimeFileSize);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        Test view = new Test();
        final File tmpLogFile = new File("/Users/qufenqi/Documents/test/mock.log");
        view.realtimeShowLog(tmpLogFile);
    }
//    public static void main(String[] args){
//        List list = new ArrayList();
//        try {
//            String s = (String) list.get(1);
//            System.out.println(s);
//        }catch (Exception e){
//            System.out.println("1");
//            System.out.println(e + ",=" + e.getMessage() );
//        }
//
//    }
}
