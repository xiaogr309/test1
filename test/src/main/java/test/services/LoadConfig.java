package test.services;

public class LoadConfig {

    static {
        System.out.println("jing2");
        //load();
    }

    private static LoadConfig loadConfig = new LoadConfig();



//构造方法
    public LoadConfig(){
        System.out.println("gz");
    }
//静态代码块
    static {
        System.out.println("jing");
        //load();
    }
//gouzao
    {
        System.out.println("gouzao");
    }
    //pt
    public static String load(){
        System.out.println("ff");
        return "aa";
    }

}
