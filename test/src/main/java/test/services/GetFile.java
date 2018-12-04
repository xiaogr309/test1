package test.services;

import java.io.File;
import java.util.ArrayList;

public class GetFile {
    public static ArrayList<String> getFile(String dir){
        File file = new File(dir);
        ArrayList<String> arrayList = new ArrayList<String>();
        if (file.isDirectory()){
            for (File file1 : file.listFiles()){
                if (file1.isFile()) {
                    arrayList.add(file1.toString());
                }
            }
        }
        return arrayList;
    }

}
