package one;

import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.*;

public class Print_Screener_Jpg implements Printable {

    static List<File> aList = new ArrayList<File>();
    static int i = 0;

    /**
     * @graphics //打印的图形环境
     * @pageFormat //打印页格式
     * @pageIndex //指明页号
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component component = null;

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.black);

        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        switch (pageIndex) {
            case 0:
                float[] dashi = {1.0f};

                graphics2D.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dashi, 0.0f));

                //第一张图

                //Image src = Toolkit.getDefaultToolkit().getImage("e:\\print\\screener\\1 (" + i + ").jpg");
                try {
                    Image src = read(new FileInputStream(new File("e:\\print\\screener\\1 (" + i + ").jpg")));
                    graphics2D.drawImage(src, (int) x, (int) y, 250, 390, component);


                    //第二张图
                    Image src2 = read(new FileInputStream(new File("e:\\print\\screener\\1 (" + (i + 1) + ").jpg")));
                    graphics2D.drawImage(src2, 280, (int) y, 250, 390, component);
                    int second_height = 400;

                    graphics2D.drawLine((int) x, (int) (y + second_height + 1), (int) (x + 500), (int) (y + second_height + 1));

                    //第三张图
                    Image src3 = read(new FileInputStream(new File("e:\\print\\screener\\1 (" + (i + 2) + ").jpg")));
                    graphics2D.drawImage(src3, (int) x, (int) (y + second_height + 4), 250, 390, component);

                    //第四张图
                    Image src4 = read(new FileInputStream(new File("e:\\print\\screener\\1 (" + (i + 3) + ").jpg")));
                    graphics2D.drawImage(src4, (int) 280, (int) (y + second_height + 4), 250, 390, component);

                    return PAGE_EXISTS;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            default:

                return NO_SUCH_PAGE;
        }
    }

    public static void main(String[] args) throws PrinterException {
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        Paper paper = new Paper();
        paper.setSize(590, 840);
        paper.setImageableArea(10, 10, 580, 830);
        pageFormat.setPaper(paper);

        book.append(new Print_Screener_Jpg(), pageFormat);

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPageable(book);


        File aFile = new File("e:\\print\\screener\\");
        if (aFile.exists() && aFile.isDirectory()) {
            Find_Picture.getAllFile(aFile, aList);
            for (i = 1; i <= aList.size(); i += 4) {
                printerJob.print();
            }
        }
    }
}


