package test.main;

import net.sf.json.JSONObject;
import sun.security.provider.MD5;
import test.services.HttpsUtil;
import test.util.Md5Util;

import java.io.FileOutputStream;
import java.io.IOException;

public class HttpsTest {
    public static void main(String[] args) throws IOException {
        String alipay_user_id = "";
        String signs = "";
        long currtimes = System.currentTimeMillis();
        //long currtimes = 1540973507726l;

        String sign = "";

        System.out.println(sign);
        String md5sign = Md5Util.md5(sign);
        System.out.println(md5sign + "--" + md5sign.length());


        String uri = "";
        uri = "";
        System.out.println(uri);
        byte[] bytes = HttpsUtil.doGet(uri);
        //JSONObject jsonObject = HttpsUtil.doGetJson(uri);
        System.out.println(new String (bytes,"utf-8"));
        System.out.println(bytes.length);
        System.out.println((char)bytes[0]);
//        FileOutputStream fos = new FileOutputStream("D:/bing.txt");
//        fos.write(bytes);
//        fos.close();
        System.out.println("done!");
        byte[] bytes1 = "hello world".getBytes();        //Verify original content
        System.out.println( new String(bytes1,"utf-8") );

        System.out.println(System.currentTimeMillis());

        char a = 1;
        byte b = 2;
        char c = 'b';
        byte d = 'v';
        int f = 'v';
        char h ='中';
        int t = '中';
        byte k = (byte) '中';
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(f);
//        System.out.println(h);
//        System.out.println(t);
//        System.out.println(k);
//        System.out.println(Md5Util.md5("2088"));
//        Integer integer = new Integer(2);
//        System.out.println(integer.toString(100));
//        int aa = 104;
//        int bb = 6;
//        String ggg = "xiaogxr123l";
//        char[] ac = ggg.toCharArray();
//        System.out.println("ac:" + ac.length);
//        if (ac[1] > 10){
//            System.out.println((char) aa);
//        }
//
//        System.out.println("111:" + ggg.substring(2,5));
//        System.out.println(Integer.toBinaryString(aa));
//        System.out.println(Integer.toBinaryString(bb));
//
//        System.out.println(5|6);
//        System.out.println(5&6);
//


    }
}
