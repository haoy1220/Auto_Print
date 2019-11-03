package five;

import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Stack;

import static javax.imageio.ImageIO.read;

public class Print_Full implements Printable {


    static int i = 0;

    //    static Stack<File> aStack = new Stack<File>();
    static String path1 = "";


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
                    Image src = read(new FileInputStream(new File(path1)));
                    //System.out.println(path1+"开始打印第一张");
                    graphics2D.drawImage(src, (int) x, (int) y, 570, 820, component);

                    return PAGE_EXISTS;

                } catch (IOException e) {
                    //e.printStackTrace();
                    return PAGE_EXISTS;
                }

            default:

                return NO_SUCH_PAGE;
        }
    }

    public void printFull(String filePath, PrinterJob printerJob) throws PrinterException, IOException {
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        Paper paper = new Paper();
        paper.setSize(590, 840);
        paper.setImageableArea(10, 10, 570, 820);
        pageFormat.setPaper(paper);

        book.append(new Print_Full(), pageFormat);

//        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPageable(book);


        File aFile = new File(filePath);
        if (aFile.exists() && aFile.isDirectory()) {
            Stack<File> aStack = new Stack<File>();
            Find_Picture.getAllFile(aFile, aStack);
            //System.out.println(aStack.size());
            int len = aStack.size();
//            boolean a = printerJob.printDialog();
            if (len == 0) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "！！！该文件夹没有图片文件！！！请重新选择文件夹");
            } else {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "*****一共有" + len + "张图片，现在开始打印：*****");
                for (i = 1; i <= len; i++) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "一张为1页：第" + i + "页开始打印");
                    try {
                        path1 = aStack.pop().toString();
                        printerJob.print();
                    } finally {
                        path1 = "";
                    }
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "一张为1页：第" + i + "页打印结束咯");
                }
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "*****一张为1页已经全部打印完成，共" + len + "页！！！*****\n\n");
            }
        } else {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "！！！文件夹为空或不存在！！！请重新选择文件夹");
        }
    }
}


