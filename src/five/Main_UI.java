package five;

import javax.swing.*;
import java.awt.*;

public class Main_UI extends JFrame {
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    static String fullPath = ".\\";

    public static void main(String[] args) {
        new Main_UI();
    }

    public Main_UI(){
        super("自动排版打印工具v1.1--综测二部 温志浩");
        init();

    }
    public void init(){
        {
            jPanel = new JPanel();
            jScrollPane = new JScrollPane();
        }
        //
        {
            JLabel fullLabel = new JLabel("整页打印路径");
            JTextField fullText = new JTextField(fullPath);
            JButton selectFull = new JButton("选择文件夹");
            JButton fullPrint = new JButton("打印");

            jPanel.add(fullLabel);
            jPanel.add(fullText);
            jPanel.add(selectFull);
            jPanel.add(fullPrint);

        }
        //
        {
            JLabel halfLabel = new JLabel("半页打印路径");
            JTextField halfText = new JTextField(fullPath);
            JButton selectHalf = new JButton("选择文件夹");
            JButton halfPrint = new JButton("打印");

            jPanel.add(halfLabel);
            jPanel.add(halfText);
            jPanel.add(selectHalf);
            jPanel.add(halfPrint);

        }
        //
        {
            JLabel fullLabel = new JLabel("1/4页打印路径");
            JTextField fullText = new JTextField(fullPath);
            JButton selectFull = new JButton("选择文件夹");
            JButton fullPrint = new JButton("打印");

            jPanel.add(fullLabel);
            jPanel.add(fullText);
            jPanel.add(selectFull);
            jPanel.add(fullPrint);

        }
        {
            this.add(jPanel);
            this.setSize(800, 600);
            // 屏幕居中
            int windowWidth = this.getWidth(); // 获得窗口宽
            int windowHeight = this.getHeight(); // 获得窗口高
            Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
            Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
            int screenWidth = screenSize.width; // 获取屏幕的宽
            int screenHeight = screenSize.height; // 获取屏幕的高
            this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight
                    / 2 - windowHeight / 2);
            this.setVisible(true);
        }
    }
}
