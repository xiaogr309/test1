package test.services;

import org.apache.hadoop.hdfs.server.namenode.Content;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WebMap extends Mapper<Object, Text, Text, Text>{
    private IntWritable one = new IntWritable();
    private Content content;

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        for (int i = 0; i< line.length; i++){


        }
        super.map(key, value, context);
    }

}
