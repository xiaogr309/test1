package test.main;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import test.services.WordMap;
import test.services.WordReduce;

//import javax.xml.soap.Text;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MemMain {







 public static void main(String[] args) throws IOException {
        String input = args[0];
        String output = args[1];

        System.out.println(input);
        System.out.println(output);

        //设置配置
        JobConf conf = new JobConf(MemMain.class);
        conf.setJobName("Test10");

        //指定输出key，values 类型
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        //指定map，reduce，类
        conf.setMapperClass(WordMap.class);
       // conf.setCombinerClass(WordReduce.class);
        conf.setReducerClass(WordReduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        JobClient.runJob(conf);
        System.exit(0);


    }
}

