package third;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;

public class Interface extends JFrame {

    static String billPath = ".\\电子发票\\";
    static String screenerPath = ".\\手机截图\\";
//    static String path = "";

    public static void main(String[] args) {
        new Interface();
    }

    public Interface() {

        //frame属性设置
        super("自动打印");
        this.setSize(558, 441);
        this.setLocation(300, 300);


        //发票模块
        JPanel panel = new JPanel();
        JLabel billLabel = new JLabel("电子发票路径：");
        billLabel.setBounds(10, 20, 120, 25);
        panel.add(billLabel);

        JTextField billText = new JTextField(20);
        billText.setBounds(140, 20, 165, 25);
        billText.setText(billPath);
        panel.add(billText);

        JButton selectBill = new JButton("浏览");
        selectBill.setBounds(300, 20, 30, 25);
        selectBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();

                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.showDialog(new JLabel(), "选择");
                File file = jFileChooser.getSelectedFile();
                billPath = file.getAbsolutePath().toString();
                billText.setText(billPath);
            }
        });
        panel.add(selectBill);

        JButton printBill = new JButton("只打印发票");
        printBill.setBounds(340, 20, 40, 25);
        printBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Print_Bill().printBill(billText.getText().toString());
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(printBill);

        //截图模块
        JLabel screenerLabel = new JLabel("手机截图路径：");
        screenerLabel.setBounds(10, 50, 120, 25);
        panel.add(screenerLabel);

        JTextField screenerText = new JTextField(20);
        screenerText.setBounds(140, 50, 165, 25);
        screenerText.setText(screenerPath);
        panel.add(screenerText);

        JButton selectScreener = new JButton("浏览");
        selectScreener.setBounds(300, 50, 30, 25);
        selectScreener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();

                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.showDialog(new JLabel(), "选择");
                File file = jFileChooser.getSelectedFile();
                screenerPath = file.getAbsolutePath().toString();
                screenerText.setText(screenerPath);
            }
        });
        panel.add(selectScreener);

        JButton printScreener = new JButton("只打印截图");
        printScreener.setBounds(340, 80, 30, 25);
        printScreener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Print_Screener().printScreener(screenerText.getText().toString());
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(printScreener);


        JButton printAll = new JButton("打印全部");
        printAll.setBounds(200, 120, 30, 25);
        printAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Print_Bill().printBill(billText.getText().toString());
                    new Print_Screener().printScreener(screenerText.getText().toString());
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(printAll);

        JTextArea tips = new JTextArea(20,20);
        tips.setLocation(100,300);
        panel.add(tips);

        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
