package test.main;

import test.imp.HelloServerImp;
import test.services.HelloServerImpl;
import test.services.RpcServer;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    public static void main(String[] args)  {
        try {
            InputStream fileInputStream = new FileInputStream("/Users/qufenqi/Documents/tmp/111.txt");
            //byte[] b = fileInputStream.read();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream,10);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"GBK"));
            FileReader fileReader = new FileReader(new File("/Users/qufenqi/Documents/tmp/111.txt"));
      //      BufferedReader bufferedReader = new BufferedReader(fileReader);


            //System.out.println(bufferedInputStream.);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String aa = "fasfa";
//        byte[] b = aa.getBytes();
//        System.out.println(b.hashCode());
//        byte[] bb = {1,2,3,5};
//        String cc = new String(bb);
//        System.out.println("1==" + cc.toString());
//        byte b1 = (byte) 128;
//        char c = (char) -1;
//
//
//        short i  = 100;
//        Integer.hashCode(10);
//
//        Set set = new HashSet();


        Integer[] arr = new Integer[]{1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        //Set<int> set = new HashSet<>(Arrays.asList(arr));

        //List list = new ArrayList();
        List list1 = new ArrayList(Arrays.asList(arr));

        String[] st = new String[]{"1","2","1","3"};
        List list2 = Arrays.asList(st);

        System.out.println(list2.size());
        for (int a = 0; a<list2.size(); a++){
            System.out.println("1-" + list2.get(a));
        }
        Map map = new HashMap();








        String a = "   hello     word   1";
        System.out.println("---"+ a.replaceAll( " {1,}" ," "));

        String b = "12";
        System.out.println(Math.min(a.length(),b.length()));
        char aa[] = a.toCharArray();
        System.out.println(a.toCharArray()[5] +","+ a.toCharArray()[6]);




//        int k = 15;
//
//        long begintime = System.currentTimeMillis();
//        for (int ii = 0;ii<arr.length/2; ii++){
//            if ((arr[ii] + arr[arr.length-ii]) == k){
//                System.out.println(arr[ii] +","+ arr[arr.length-ii]);
//
//            }else if (arr[ii] + arr[arr.length-ii] > k){
//
//            }else {
//
//            }
////            for (int jj = ii+1; jj<arr.length;jj++){
////                int aaa = arr[ii] + arr[jj];
////                if (aaa == k){
////                    System.out.println(ii + "," + jj);
////                }
////            }
//        }
//        long endtime = System.currentTimeMillis();
//        System.out.println(endtime - begintime);
//        System.out.println(13%2);
//
//
//        for (int v = 0; v<arr.length-1; v++){
//            for (int b = 0; b<arr.length-1; b++){
//                if (arr[b] < arr[b+1]){
//                    int tmp = arr[b+1];
//                    arr[b+1] = arr[b];
//                    arr[b] = tmp;
//                }
//            }
//
//        }
//
//        for (int bbb = 0; bbb<arr.length; bbb++) {
//            System.out.println(arr[bbb]);
//        }








//        System.out.println();
//        HelloServerImp helloServer = new HelloServerImpl();
//        RpcServer rpcServer = new RpcServer();
//
//        rpcServer.register(helloServer,50001);

//        try {
//            ServerSocket serverSocket = new ServerSocket(50001);
//            Socket socket = serverSocket.accept();
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter out = new PrintWriter(socket.getOutputStream());
//            while (true) {
//                String in = br.readLine();
//                System.out.println(in);
//                out.println("hhhh");
//                out.flush();
//                if (in.equals("end")){
//                    break;
//                }
//            }
//
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Socket socket = null;
//        try {
//            socket = new Socket("localhost",50001);
//
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            Scanner scanner = new Scanner(System.in);
//            while (true){
//                String send = scanner.nextLine();
//                dataOutputStream.writeUTF("send:" + send);
//                String accpet = dataInputStream.readUTF();
//                System.out.println(accpet);
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
