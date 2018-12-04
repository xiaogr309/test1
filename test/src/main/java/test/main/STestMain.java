package test.main;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import test.services.WordMap;

public class STestMain {




        public static class WebMap extends Mapper<Object, Text, Text, Text> {

            @Override
            public void map(Object key, Text value, Context context)
                    throws IOException, InterruptedException {

                // 切分
//                WordMap wordMap = new WordMap();
//                Text t = wordMap.aa;

                String[] line = value.toString().split(",");
                if (line.length == 3) {
                    String name = line[0]; // 姓名
                    String time = line[1]; // 停留时间
                    String infor = line[2]; // 信息
                    context.write(new Text(name + "," + time), new Text(time
                            + "," + infor));
                }
            }
        }

        public static class Partition extends Partitioner<Text, Text> {
            @Override
            public int getPartition(Text key, Text value, int number) {
                String name = key.toString().split(",")[0];
                int hash = name.hashCode();
                return Math.abs(hash % number);
            }
        }

        // 可以序列化
        public static class Sort extends WritableComparator {
            protected Sort() {
                super(Text.class, true);
            }

            @Override
            public int compare(WritableComparable w1, WritableComparable w2) {
                Text h1 = new Text(((Text) w1).toString().split(",")[0]);
                Text h2 = new Text(((Text) w2).toString().split(",")[0]);

                IntWritable M1 = new IntWritable(Integer.valueOf(((Text) w1)
                        .toString().split(",")[1]));
                IntWritable M2 = new IntWritable(Integer.valueOf(((Text) w2)
                        .toString().split(",")[1]));

                // 二次排序
                int R;
                if (h1.equals(h2)) {
                    R = M2.compareTo(M1);
                } else {
                    R = h1.compareTo(h2);
                }
                return R;
            }
        }

        public static class Group extends WritableComparator {
            protected Group() {
                super(Text.class, true);
            }

            @Override
            public int compare(WritableComparable w1, WritableComparable w2) {
                Text h1 = new Text(((Text) w1).toString().split(",")[0]);
                Text h2 = new Text(((Text) w2).toString().split(",")[0]);

                int R;
                if (h1.equals(h2)) {
                    R = 0;
                } else {
                    R = h1.compareTo(h2);
                }
                return R;
            }
        }

        public static class WebReduce extends
                Reducer<Text, Text, IntWritable, Text> {
            @Override
            public void reduce(Text key, Iterable<Text> values, Context context)
                    throws IOException, InterruptedException {
                int count = 0;
                String name = key.toString().split(",")[0];
                for (Text t : values) {
                    count++;
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(name);
                    buffer.append(",");
                    buffer.append(t.toString());
                    context.write(new IntWritable(count),
                            new Text(buffer.toString()));

                }
            }
        }

        public static void main(String[] args) throws IOException,
                ClassNotFoundException, InterruptedException {
            if (args.length != 4) {
                System.out.println("error");
                System.exit(0);
            }
            int SplitMB = Integer.valueOf(args[2]);
            String dst = args[0];
            String out = args[1];
            Configuration conf = new Configuration();
            conf.set("mapreduce.input.fileinputformat.split.maxsize",
                    String.valueOf(SplitMB * 1024 * 1024));
            conf.set("mapred.min.split.size", String.valueOf(SplitMB * 1024 * 1024));
            conf.set("mapreduce.input.fileinputformat.split.minsize.per.node",
                    String.valueOf(SplitMB * 1024 * 1024));
            conf.set("mapreduce.input.fileinputformat.split.minsize.per.rack",
                    String.valueOf(SplitMB * 1024 * 1024));
            // 删除目录
            Path outputPath = new Path(out);
            outputPath.getFileSystem(conf).delete(outputPath, true);
            Job job = new Job(conf,"wordc");


            FileInputFormat.addInputPath(job, new Path(dst));
            FileOutputFormat.setOutputPath(job, new Path(out));
            job.setNumReduceTasks(Integer.valueOf(args[3]));
            job.setPartitionerClass(Partition.class);
            job.setGroupingComparatorClass(Group.class);
            job.setSortComparatorClass(Sort.class);
            job.setMapperClass(WebMap.class);
            job.setReducerClass(WebReduce.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(Text.class);

            job.setJarByClass(STestMain.class);
            job.waitForCompletion(true);
 //       }
//    }
//
//    public final char value[];
//    public STestMain() {
//        this.value = new char[0];
//    }
//
//    public int compareTo1(String anotherString) {
//        int len1 = value.length;
//        int len2 = anotherString.value.length;
//        int lim = Math.min(len1, len2);
//        char v1[] = value;
//        char v2[] = anotherString.value;
//
//        int k = 0;
//        while (k < lim) {
//            char c1 = v1[k];
//            char c2 = v2[k];
//            if (c1 != c2) {
//                return c1 - c2;
//            }
//            k++;
//        }
//        return len1 - len2;
//    }

//    public static void main(String[] args) {
//        Integer a = 10;
//
//        String aa = "asd";
//        String bb = "ttt";
//
//        System.out.println(aa.compareTo("asf"));

//
//        try{
//            FileReader fileReader = new FileReader("/Users/qufenqi/Documents/campus/feiykt/seedid.txt");
//            char[] c = new char[64];
//            int hasRead = 0;
//            while ((hasRead = fileReader.read(c))>0){
//
//                System.out.println(new String(c,0,hasRead));
//                System.out.println("1");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        List list = new ArrayList();
//        list.add("qqyumidi");
//        list.add("corn");
//        list.add(100);
//
//        for (int i = 0; i < list.size(); i++) {
//            String name = (String) list.get(i); // 1
//            System.out.println("name:" + name);



//        Enumeration<String> days;
//        Vector<String> dayNames = new Vector<String>();
//        dayNames.add("Sunday");
//        dayNames.add("Monday");
//        dayNames.add("Tuesday");
//        dayNames.add("Wednesday");
//        dayNames.add("Thursday");
//        dayNames.add("Friday");
//        dayNames.add("Saturday");
//        days = dayNames.elements();
//        while (days.hasMoreElements()){
//            System.out.println(days.nextElement());
//        }

//        Vector vector = new Vector(3,2);
////        vector.addElement("10");
////        System.out.println(vector.get(0));
////        System.out.println(vector.size());

//
//        Hashtable hashtable = new Hashtable();
//
//        Enumeration enumeration;
//        String s;
//        double d;
//
//        hashtable.put("x","111");
//        hashtable.put("i","222");
//        hashtable.put("a","333");
//        hashtable.put("o","444");
//        hashtable.put("g","555");
//
//        enumeration = hashtable.keys();
//
//        while (enumeration.hasMoreElements()) {
//            //System.out.println(enumeration.nextElement());
//            hashtable.get(enumeration.nextElement());
//        }
//
//        Set set = new HashSet();
//        set.add("gg");
//        System.out.println(set.size());
//        Properties properties = new Properties();
//        properties.setProperty("xiao","123");
//        System.out.println(properties.get("xiao"));
//         set = properties.keySet();
////        set.add("ggg");
//        System.out.println(set.size());
//
//        Iterator is = set.iterator();
//        while (is.hasNext()){
//            System.out.println(is.next());
//        }
//
//        Socket socket = new Socket();
//        InetAddress inetAddress = new InetAddress();
//





    }

//    public void build01(){
//        try {
//            //DocumentHelper提供了创建Document对象的方法
//            Document document = DocumentHelper.createDocument();
//            //添加节点信息
//            Element rootElement = document.addElement("modules");
//            //这里可以继续添加子节点，也可以指定内容
//            rootElement.setText("这个是module标签的文本信息");
//            Element element = rootElement.addElement("module");
//
//            Element nameElement = element.addElement("name");
//            Element valueElement = element.addElement("value");
//            Element descriptionElement = element.addElement("description");
//            nameElement.setText("名称");
//            nameElement.addAttribute("language", "java");//为节点添加属性值
//            valueElement.setText("值");
//            valueElement.addAttribute("language", "c#");
//            descriptionElement.setText("描述");
//            descriptionElement.addAttribute("language", "sql server");
//            System.out.println(document.asXML()); //将document文档对象直接转换成字符串输出
//            Writer fileWriter = new FileWriter("/Users/qufenqi/Documents/tmp/module.xml");
//            //dom4j提供了专门写入文件的对象XMLWriter
//            XMLWriter xmlWriter = new XMLWriter(fileWriter);
//            xmlWriter.write(document);
//            xmlWriter.flush();
//            xmlWriter.close();
//            System.out.println("xml文档添加成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
