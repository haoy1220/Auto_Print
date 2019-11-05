package six;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;


public class Main_UI extends JFrame {
    private JPanel jPanel;
    static PrinterJob printerJob = PrinterJob.getPrinterJob();

    private JScrollPane jScrollPane;
    private static String filePath = ".\\";

    private JLabel fullLabel;
    private JTextField fileText;
    private JButton selectFile;
    private JButton printFull;

    private JButton printHalf;


    private JButton printFour;

    private JButton clearAll;
    private JTextArea tips;


    public static void main(String[] args) {
        new Main_UI();
    }

    public Main_UI() {
        super("自动排版打印工具v1.2--综测二部 温志浩");
        this.init();
        this.initListener();

    }

    public void init() {
        {
            jPanel = new JPanel();
            jScrollPane = new JScrollPane();
        }
        //整页
        {
            fullLabel = new JLabel("文件夹路径：");
            fileText = new JTextField(filePath, 30);
            selectFile = new JButton("选择文件夹");
            printFull = new JButton("一张图片打印成一页");

            jPanel.add(fullLabel);
            jPanel.add(fileText);
            jPanel.add(selectFile);
            jPanel.add(printFull);

        }

        //半页
        {

            printHalf = new JButton("两张图片打印成一页");

            jPanel.add(printHalf);

        }

        //1/4页
        {

            printFour = new JButton("四张图片打印成一页");

            jPanel.add(printFour);

        }

        //打印全部和清空记录
        {
            clearAll = new JButton("清空记录");

            jPanel.add(clearAll);
        }
        //记录框
        {
            tips = new JTextArea(16, 45);
            tips.setText("-----------说明------------------\n" +
                    "1.选定文件夹，选择你想要的打印方式打印即可；\n" +
                    "\n" +
                    "2.一张图片打印成一页：适合打印大小占满一页的图片或pdf；\n" +
                    "\n" +
                    "3.两张图片打印成一页：适合打印发票那样大小的图片或pdf；\n" +
                    "\n" +
                    "4.四张图片打印成一页：适合打印手机截图那样大小的图片或pdf.\n" +
                    "--------------------------------\n\n");
            JTextAreaOutputStream out = new JTextAreaOutputStream(tips);
            System.setOut(new PrintStream(out));//设置输出重定向
            tips.setLocation(10, 300);
            tips.setLineWrap(true);
            tips.setEditable(false);
            tips.setSelectionStart(tips.getText().length());
            jScrollPane = new JScrollPane(tips);
            jPanel.add(jScrollPane);
        }
        {
            this.setVisible(true);
            this.setContentPane(jPanel);
//            this.add(jPanel);
            this.setSize(540, 450);
            // 屏幕居中
            int windowWidth = this.getWidth(); // 获得窗口宽
            int windowHeight = this.getHeight(); // 获得窗口高
            Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
            Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
            int screenWidth = screenSize.width; // 获取屏幕的宽
            int screenHeight = screenSize.height; // 获取屏幕的高
            this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight
                    / 2 - windowHeight / 2);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void initListener() {


        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile(fileText);
            }
        });

        printFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(fileText, "full");
            }
        });


        printHalf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(fileText, "half");
            }
        });


        printFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(fileText, "four");
            }
        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tips.setText("");
            }
        });
    }

    public void selectFile(JTextField jTextField) {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = jFileChooser.showOpenDialog(null);
        if (result == jFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            jTextField.setText(path);
        } else {
        }
    }

    public void jobListener(final JTextField jTextField, final String string) {
        boolean a = printerJob.printDialog();
        if (a) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (string.equals("full")) {
                            new Print_Full().printFull(jTextField.getText(), printerJob);
                        } else if (string.equals("half")) {
                            new Print_Half().printHalf(jTextField.getText(), printerJob);
                        } else {
                            new Print_Four().printFour(jTextField.getText(), printerJob);
                        }
                    } catch (PrinterException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } else {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "***你取消了打印***");
        }
    }
}
