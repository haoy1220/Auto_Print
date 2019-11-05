package six;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


/*PDF转换图片：
    输入：pdf文件
    输出：在原文件目录输出图片
 */
public class PDF_Picture {
    public static void pdf_turn_picture(File file) throws IOException {
        String filePath = file.getAbsolutePath();
        String dest = file.getParent().toString();
        PDDocument document = null;
        try {

            document = PDDocument.load(new File(filePath));
            PDFRenderer renderer = new PDFRenderer(document);
            int count = document.getNumberOfPages();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + file.getName() + "共有" + count + "页");
            for (int i = 0; i < count; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                ImageIO.write(image, "png", new File(dest + File.separator + getFileNameWithoutSuffix(file) + "(" + i + ").png"));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "第" + (i + 1) + "页转化成功！");
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + file.getName() + "全部转换成功");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    public static String getFileNameWithoutSuffix(File file) {
        String file_name = file.getName();
        return file_name.substring(0, file_name.lastIndexOf("."));
    }
}
