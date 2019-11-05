package six;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class Find_Picture {


    public static void getAllFile(File aFile, Stack<File> aStack) throws IOException {
        File[] files = aFile.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".pdf") || fileName.endsWith(".PDF")) {
                    System.out.println("发现pdf文件:" + fileName + "，正在转化成图片......");
                    PDF_Picture.pdf_turn_picture(file);
                }
            }
        }

        files = aFile.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".bmp") || fileName.endsWith(".jpeg")
                        || fileName.endsWith(".JPG") || fileName.endsWith(".PNG") || fileName.endsWith(".GIF") || fileName.endsWith(".BMP") || fileName.endsWith(".JPEG")) {
                    aStack.push(file);
                }
            }
        }
    }
}
