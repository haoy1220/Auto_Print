package three;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class Interface extends JFrame {

    static String billPath = ".\\电子发票\\";
    static String screenerPath = ".\\手机截图\\";
    //    static String path = "";
    static PrinterJob printerJob = PrinterJob.getPrinterJob();


    public static void main(String[] args) {
        Interface mainInterface = new Interface();
    }

    public Interface() {

        //frame属性设置
        super("自动排版打印工具v1.0--综测二部 温志浩");
        this.setSize(558, 450);
        this.setLocation(350, 100);


        final JTextArea tips = new JTextArea(16, 45);
        JTextAreaOutputStream out = new JTextAreaOutputStream(tips);
        System.setOut(new PrintStream(out));//设置输出重定向
        System.setErr(new PrintStream(out));//将错误输出也重定向,用于e.pritnStackTrace
        //发票模块
        JPanel panel = new JPanel();
        JLabel billLabel = new JLabel("电子发票路径：");
        billLabel.setBounds(10, 20, 120, 25);
        panel.add(billLabel);

        final JTextField billText = new JTextField(20);
        billText.setBounds(140, 20, 165, 25);
        billText.setText(billPath);
        panel.add(billText);

        JButton selectBill = new JButton("浏览");
        selectBill.setBounds(300, 20, 30, 25);
        selectBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(".");
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = jFileChooser.showOpenDialog(null);
//                jFileChooser.showDialog(new JLabel(), "选择");
                if (result == jFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    billPath = file.getAbsolutePath().toString();
                    billText.setText(billPath);
                } else {

                }
            }
        });
        panel.add(selectBill);

        JButton printBill = new JButton("只打印发票");
        printBill.setBounds(340, 20, 40, 25);
        printBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a = printerJob.printDialog();
                if (a) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new Print_Bill().printBill(billText.getText().toString(), printerJob);
                            } catch (PrinterException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "***你取消了打印***");
                }
            }
        });
        panel.add(printBill);

        //截图模块
        JLabel screenerLabel = new JLabel("手机截图路径：");
        screenerLabel.setBounds(10, 50, 120, 25);
        panel.add(screenerLabel);

        final JTextField screenerText = new JTextField(20);
        screenerText.setBounds(140, 50, 165, 25);
        screenerText.setText(screenerPath);
        panel.add(screenerText);

        JButton selectScreener = new JButton("浏览");
        selectScreener.setBounds(300, 50, 30, 25);
        selectScreener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(".");

//                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                jFileChooser.showDialog(new JLabel(), "选择");
//                File file = jFileChooser.getSelectedFile();
//                screenerPath = file.getAbsolutePath().toString();
//                screenerText.setText(screenerPath);

                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = jFileChooser.showOpenDialog(null);
//                jFileChooser.showDialog(new JLabel(), "选择");
                if (result == jFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    screenerPath = file.getAbsolutePath().toString();
                    screenerText.setText(screenerPath);
                } else {

                }
            }
        });
        panel.add(selectScreener);

        JButton printScreener = new JButton("只打印截图");
        printScreener.setBounds(340, 80, 30, 25);
        printScreener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a = printerJob.printDialog();
                if (a) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new Print_Screener().printScreener(screenerText.getText().toString(), printerJob);
                            } catch (PrinterException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "***你取消了打印***");
                }
            }
        });
        panel.add(printScreener);


        //综合
        JButton printAll = new JButton("打印全部");
        printAll.setBounds(100, 120, 30, 25);
        printAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a = printerJob.printDialog();
                if (a) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new Print_Bill().printBill(billText.getText().toString(), printerJob);

                                new Print_Screener().printScreener(screenerText.getText().toString(), printerJob);
                            } catch (PrinterException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ：").format(System.currentTimeMillis()) + "***你取消了打印***");
                }
            }
        });
        panel.add(printAll);


        tips.setLocation(10, 300);
        tips.setLineWrap(true);
        tips.setEditable(false);
//        JTextAreaOutputStream out = new JTextAreaOutputStream(tips);
//        System.setOut(new PrintStream(out));//设置输出重定向
//        System.setErr(new PrintStream(out));//将错误输出也重定向,用于e.pritnStackTrace
        tips.setSelectionStart(tips.getText().length());
        JScrollPane scrollPane = new JScrollPane(tips);


        JButton clearAll = new JButton("清空记录");
        clearAll.setBounds(150, 120, 30, 25);
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tips.setText("");
            }
        });
        panel.add(clearAll);


        panel.add(scrollPane);

        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
