package test.services;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class TableReduce extends Reducer<Text, Text, Text, Text> {

    private static int time = 0;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //输出表头
        if (time == 0){
            context.write(new Text("grandchild"),new Text("grandparent"));
            time++;
        }
        String[] grandchild =  new String[10];
        int grandchildnum = 0;
        String[] grandparent = new String[10];
        int grandparentnum = 0;


        Iterator item = values.iterator();
        //遍历同一个key的values记录，生成grandchild与grandparent列表
        while (item.hasNext()){
            String record = item.next().toString();
            String[] recordArr = record.split("-");
            //获取左右表标识
            char relationType = record.charAt(0);
            if (relationType == '1'){
                grandchild[grandchildnum] = recordArr[1];
                grandchildnum++;
                System.out.println(grandchildnum);
            }
            if (relationType == '2'){
                grandparent[grandparentnum] = recordArr[2];
                grandparentnum++;
                System.out.println(grandparentnum);
            }
            System.out.println(recordArr.length +"="+recordArr[0] + "," + recordArr[1] + "," +recordArr[2]);
        }

        System.out.println(grandchildnum + "," + grandparentnum);
        //求grandchild 与 grandparent 的笛卡尔积

        if (grandchildnum !=0 && grandparentnum != 0){
            for (int i = 0; i< grandchildnum; i++){
                for (int m = 0; m<grandparentnum; m++){
                    context.write(new Text(grandchild[i]), new Text(grandparent[m]));
                }
            }

        }


    }
}
