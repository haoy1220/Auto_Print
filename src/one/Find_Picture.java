package one;

import java.io.File;
import java.util.List;

public class Find_Picture {

//    public static void main(String[] args) {
//        File aFile = new File("C:\\Users\\Unionman\\Desktop\\print\\");
//        if (aFile.exists() && aFile.isDirectory()) {
//            List<File> aList = new ArrayList<File>();
//            getAllFile(aFile, aList);
//            for (File file : aList) {
//                System.out.println(file);
//            }
//        }
//    }
//     || fileName.endsWith(".png")
//             || fileName.endsWith(".gif")

    public static void getAllFile(File aFile, List<File> aList) {
        File[] files = aFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllFile(file, aList);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                        aList.add(file);
                    }
                }
            }
        }
    }


}
