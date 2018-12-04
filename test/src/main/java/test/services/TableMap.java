package test.services;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * 遍历数据，生成左右表
 * 提交进行sort，shuffer，交给reduce
 *
 *
 */

public class TableMap extends Mapper<Object, Text, Text, Text> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String childname = new String();

        String parentname = new String();
        String relationType = new String();

        Text outputMapKey = new Text();
        Text outputMapValue = new Text();


        //读取分割记录
        StringTokenizer line = new StringTokenizer(value.toString());

        String[] values = new String[2];
        int i= 0;
        //遍历记录字段，存放数组
        while (line.hasMoreTokens()){
            values[i] = line.nextToken();
            i++;
        }

        //生成左表，右表记录
        //过滤第一行
        if (values[0].compareTo("child") != 0){
            //左表1,chiled作为key
            parentname = values[1];
            childname = values[0];
            relationType = "1";
            outputMapKey = new Text(parentname);
            outputMapValue = new Text(relationType + "-" + values[0] + "-" + values[1]);
            context.write(outputMapKey, outputMapValue);

            //右表 parent作为key
            relationType = "2";
            outputMapKey = new Text(childname);
            outputMapValue = new Text(relationType + "-" + values[0] + "-" + values[1]);

            System.out.println("map:" + values[0] +"-" + values[1]);

            context.write(outputMapKey, outputMapValue);


        }


    }
}
