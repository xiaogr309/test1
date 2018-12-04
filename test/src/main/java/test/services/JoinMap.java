package test.services;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 拆分数据为左右表
 *
 */
public class JoinMap extends Mapper<Object, Text, Text, Text> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        int c = 0;
        c++;
        System.out.println("mapcount:" + c);
        if (value.toString().length() == 0) {
            return;
        }
        String line = value.toString();//获取记录
        String relationtype = new String(); //左右表标识

        if (line.contains("addressID") || line.contains("factoryname")){
            return;
        }

        StringTokenizer itr = new StringTokenizer(line);

        String mapKey = new String();
        String mapValue = new String();
        int i = 0;
        while (itr.hasMoreTokens()){
            String token = itr.nextToken();


            if (token.charAt(0)>='0' && token.charAt(0) < '9'){
                 mapKey = token;
                //判断如果记录第几列位数字，第一列为数字则为右表，第二列位数字则为坐标
                if (i==0){
                    relationtype = "1"; //右表
                }else {
                    relationtype = "2"; //左表
                }
                continue;
            }

            mapValue += token + " ";
            i++;
        }

        context.write(new Text(mapKey), new Text(relationtype+"+"+mapValue));

    }
}
