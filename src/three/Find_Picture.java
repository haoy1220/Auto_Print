package three;

import java.io.File;
import java.util.Stack;

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

    public static void getAllFile(File aFile, Stack<File> aStack) {
        File[] files = aFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllFile(file, aStack);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".bmp") || fileName.endsWith(".jpeg")
                            || fileName.endsWith(".JPG") || fileName.endsWith(".PNG") || fileName.endsWith(".GIF") || fileName.endsWith(".BMP") || fileName.endsWith(".JPEG")
                            || fileName.endsWith(".pdf") || fileName.endsWith(".PDF")) {
                        aStack.push(file);
                    }
                }
            }
        }
    }
}
