package six;

import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EmptyStackException;
import java.util.Stack;

import static javax.imageio.ImageIO.read;

public class Print_Four implements Printable {

    static int i = 0;

    //    static Stack<File> aStack = new Stack<File>();
    static String path1 = "";
    static String path2 = "";
    static String path3 = "";
    static String path4 = "";

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
                    graphics2D.drawImage(src, (int) x, (int) y, 280, 400, component);


                    //第二张图
                    Image src2 = read(new FileInputStream(new File(path2)));
                    graphics2D.drawImage(src2, 300, (int) y, 280, 400, component);
                    int second_height = 420;

                    graphics2D.drawLine((int) x, (second_height), (int) (x + 580), (second_height));

                    //第三张图
                    Image src3 = read(new FileInputStream(new File(path3)));
                    graphics2D.drawImage(src3, (int) x, (int) (y + second_height), 280, 400, component);

                    //第四张图
                    Image src4 = read(new FileInputStream(new File(path4)));
                    graphics2D.drawImage(src4, 300, (int) (y + second_height), 280, 400, component);


                    return PAGE_EXISTS;

                } catch (IOException e) {
                    //e.printStackTrace();
                    return PAGE_EXISTS;
                }

            default:

                return NO_SUCH_PAGE;
        }
    }

    public void printFour(String filePath, PrinterJob printerJob) throws PrinterException, IOException {
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        Paper paper = new Paper();
        paper.setSize(590, 840);
        paper.setImageableArea(10, 10, 570, 820);
        pageFormat.setPaper(paper);

        book.append(new Print_Four(), pageFormat);

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
                for (i = 1; i <= len; i += 4) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "4张图片为1页：第" + (i / 4 + 1) + "页开始打印");
                    try {
                        path1 = aStack.pop().toString();
                        path2 = aStack.pop().toString();
                        path3 = aStack.pop().toString();
                        path4 = aStack.pop().toString();
                        printerJob.print();
                    } catch (EmptyStackException e) {
                        printerJob.print();
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "*第" + (i / 4 + 1) + "页只剩" + (len - i + 1) + "张图片，没打满整页！*");
                    } finally {
                        path1 = "";
                        path2 = "";
                        path3 = "";
                        path4 = "";
                    }
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "4张图片为1页：第" + (i / 4 + 1) + "页打印结束");
                }
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "*****4张图片为1页已经全部打印完成，共" + len + "页！！！*****\n\n");
            }
        } else {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "！！！文件夹为空或不存在！！！请重新选择文件夹");
        }
    }
}


