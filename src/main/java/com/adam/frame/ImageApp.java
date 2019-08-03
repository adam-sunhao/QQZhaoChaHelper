package com.adam.frame;

import com.adam.util.FileUtil;
import com.adam.util.PictureUtil;
import com.adam.util.WindowUtil;
import com.adam.util.ZhaoChaUtil;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Adam on 2019/7/28 22:39.
 */
public class ImageApp extends JFrame {

    public static final String WINDOW_NAME = "大家来找茬";
    public static WinDef.HWND hwnd = null;
    public static final String IMAGE_TYPE = "jpg";
    public static String imageName = "";
    static {
        hwnd = WindowUtil.getHwndByWindowName(WINDOW_NAME);
    }

    public ImageApp() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(390, 355);
        setTitle("找茬小助手1.0 Made By Adam");
        setResizable(false);
        getContentPane().setLayout(null);
        JPanel panel = new JPanel(new BorderLayout());

        JButton jButton = new JButton("开始找茬:)");
        jButton.setPreferredSize(new Dimension(390,40));

        JPanel imagePanel = new ImagePanel();
        //imagePanel.setBounds(0, 0, 381, 286);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ZhaoChaUtil.showDiffImage(WINDOW_NAME);
                if(hwnd == null) {
                    hwnd = WindowUtil.getHwndByWindowName(WINDOW_NAME);
                } else {
                    imageName = System.currentTimeMillis() + "";
                    if(ZhaoChaUtil.showDiffImage(hwnd,IMAGE_TYPE,imageName,true)) {
                        imagePanel.repaint();
                    }
                }
            }
        });
        panel.add(jButton,BorderLayout.NORTH);
        panel.add(imagePanel,BorderLayout.CENTER);

        setContentPane(panel);
        //getContentPane().add(panel);
        //setAlwaysOnTop(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageApp();
    }

    class ImagePanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            //super.paint(g);
            ImageIcon icon = new ImageIcon("images/" + imageName + "." + IMAGE_TYPE);
            g.drawImage(icon.getImage(), 0, 0, 381, 286, this);
        }
    }
}