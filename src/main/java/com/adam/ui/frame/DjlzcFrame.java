package com.adam.ui.frame;

import com.adam.config.DjlzcConfig;
import com.adam.config.IConfig;
import com.adam.ui.panel.ImagePanel;
import com.adam.util.ZhaoChaUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam on 2019/8/24 19:11.
 */
public class DjlzcFrame extends JFrame {

    public DjlzcFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(390, 355);
        setTitle("找茬小助手1.0 Made By Adam");
        setResizable(false);
        getContentPane().setLayout(null);
        JPanel panel = new JPanel(new BorderLayout());

        JButton jButton = new JButton("开始找茬:)");
        jButton.setPreferredSize(new Dimension(390, 40));

        ImagePanel imagePanel = new ImagePanel();

        //获取大家来找茬配置
        IConfig config = new DjlzcConfig();
        imagePanel.setConfig(config);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (config.isSave()) {
                    String imageName = ZhaoChaUtil.showDiffImage(config);
                    imagePanel.setImageName(imageName);
                } else {
                    Image image = ZhaoChaUtil.getDiffImage(config);
                    imagePanel.setImage(image);
                }
                imagePanel.repaint();
            }
        });
        panel.add(jButton, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);

        setContentPane(panel);
        //getContentPane().add(panel);
        //setAlwaysOnTop(true);
        setVisible(true);
    }

}
