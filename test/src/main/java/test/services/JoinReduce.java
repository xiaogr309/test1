package test.services;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReduce extends Reducer<Text, Text, Text, Text> {
    private static int time = 0;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        System.out.println("reduceime:" + time);
        String[] factory = new String[10];
        int factorynum = 0;
        String[] address = new String[10];
        int addressnum = 0;

        //输出表头
        if (time == 0 ){
            context.write(new Text("factoryname"), new Text("addressname"));
            time++;
        }

        Iterator iterator = values.iterator();
        while (iterator.hasNext()){
            System.out.println(addressnum +","+factorynum);
            String record = iterator.next().toString();
            String[] recordArr = record.split("\\+");
            int len = recordArr.length;
            System.out.println("len=" + len);

            if (len != 2 ){
                continue;
            }
            //左右表标识
            String relationtype = recordArr[0];

            //右表
            if (relationtype.equals("1")){
                address[addressnum] = recordArr[1];
                addressnum++;
                System.out.println(recordArr[1]);
            }
            //左表
            if (relationtype.equals("2")){
                factory[factorynum] = recordArr[1];
                factorynum++;
                System.out.println(recordArr[1]);
            }
        }

        System.out.println(factory.length +"-"+address.length);
        //笛卡尔积
        for (int m = 0; m < factorynum; m++){
            for (int n = 0; n < addressnum; n++){
                context.write(new Text(factory[m]), new Text(address[n]));
            }
        }

    }
}
