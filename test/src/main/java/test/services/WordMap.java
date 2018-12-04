package test.services;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class WordMap extends MapReduceBase implements Mapper<Object,Text,Text,IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
//    protected Text aa = new Text();

    @Override
    public void map(Object key, Text values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        String[] arr = values.toString().split(",");

        for (int i = 0 ; i<arr.length;i++){
            System.out.println(arr[i]);
            word.set(arr[i]);
            output.collect(word, one);


        }

    }
}
