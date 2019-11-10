package five;

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
    private static String fullPath = ".\\";
    private static String halfPath = ".\\";
    private static String fourPath = ".\\";

    private JLabel fullLabel;
    private JTextField fullText;
    private JButton selectFull;
    private JButton printFull;

    private JLabel halfLabel;
    private JTextField halfText;
    private JButton selectHalf;
    private JButton printHalf;

    private JLabel fourLabel;
    private JTextField fourText;
    private JButton selectFour;
    private JButton printFour;

    private JButton printAll;
    private JButton clearAll;
    private JTextArea tips;


    public static void main(String[] args) {
        new Main_UI();
    }

    public Main_UI() {
        super("自动排版打印工具v1.2");
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
            fullLabel = new JLabel("整页打印路径");
            fullText = new JTextField(fullPath,25);
            selectFull = new JButton("选择文件夹");
            printFull = new JButton("打印");

            jPanel.add(fullLabel);
            jPanel.add(fullText);
            jPanel.add(selectFull);
            jPanel.add(printFull);

        }

        //半页
        {
            halfLabel = new JLabel("半页打印路径");
            halfText = new JTextField(halfPath,25);
            selectHalf = new JButton("选择文件夹");
            printHalf = new JButton("打印");

            jPanel.add(halfLabel);
            jPanel.add(halfText);
            jPanel.add(selectHalf);
            jPanel.add(printHalf);

        }

        //1/4页
        {
            fourLabel = new JLabel("1/4页打印路径");
            fourText = new JTextField(fourPath,25);
            selectFour = new JButton("选择文件夹");
            printFour = new JButton("打印");

            jPanel.add(fourLabel);
            jPanel.add(fourText);
            jPanel.add(selectFour);
            jPanel.add(printFour);

        }

        //打印全部和清空记录
        {
//            printAll = new JButton("打印全部");
            clearAll = new JButton("清空记录");

//            jPanel.add(printAll);
            jPanel.add(clearAll);
        }
        //记录框
        {
            tips = new JTextArea(16, 45);
            tips.setText("-----------说明------------------\n" +
                    "1.选定文件夹，打印即可；\n" +
                    "\n" +
                    "2.整页打印指一张图片打印成一页；\n" +
                    "\n" +
                    "3.半页打印指两张图片打印成一页；\n" +
                    "\n" +
                    "4.1/4页打印指四张图片打印成一页.\n" +
                    "--------------------------------\n\n" );
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
            this.setSize(560, 450);
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


        selectFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile(fullText);
            }
        });

        printFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(fullText,"full");
            }
        });

        selectHalf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile(halfText);
            }
        });

        printHalf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(halfText,"half");
            }
        });

        selectFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile(fourText);
            }
        });

        printFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobListener(fourText,"four");
            }
        });

//        printAll.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                jobListener(fullText,"full");
//                jobListener(halfText,"half");
//                jobListener(fourText,"four");
//            }
//        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tips.setText("");
            }
        });
    }

    public void selectFile(JTextField jTextField){
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

    public void jobListener(final JTextField jTextField, final String string){
        boolean a = printerJob.printDialog();
        if (a) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (string.equals("full")){
                            new Print_Full().printFull(jTextField.getText(), printerJob);
                        }else if (string.equals("half")){
                            new Print_Half().printHalf(jTextField.getText(), printerJob);
                        }else {
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
