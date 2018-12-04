package test.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import test.services.JoinMap;
import test.services.JoinReduce;

import java.io.IOException;

public class JoinMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2){
            System.out.println("参数有误");
            System.exit(1);
        }

        Configuration conf = new Configuration();
        String input = args[0];
        String output = args[1];
//        input.compareTo()
        Path path = new Path(output);
        FileSystem fileSystem = path.getFileSystem(conf);
        if (fileSystem.exists(path)){
            fileSystem.delete(path,true);
        }


        Job job = new Job(conf, "JoinMain");

        job.setJarByClass(JoinMain.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(JoinMap.class);
        job.setReducerClass(JoinReduce.class);


        FileInputFormat.setInputPaths(job, new Path(input) );
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean b = job.waitForCompletion(true);
    }
}
