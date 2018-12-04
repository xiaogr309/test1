package test.main;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import test.services.TableMap;
import test.services.TableReduce;

import java.io.IOException;

public class TableMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String input = args[0];
        String output = args[1];


        Configuration conf = new Configuration();
        Job job = new Job(conf, "Tablelink");

        job.setJarByClass(TableMain.class);

        //设置输出类型 map,reduce同类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //设置map，reduce处理类
        job.setMapperClass(TableMap.class);
        job.setReducerClass(TableReduce.class);

        //设置输入输出目录
        FileOutputFormat.setOutputPath(job, new Path(output));
        FileInputFormat.setInputPaths(job, new Path(input));

        System.exit(job.waitForCompletion(true ) ? 0 :1 );

    }
}
