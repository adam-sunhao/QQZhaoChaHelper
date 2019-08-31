package com.adam.ui.listener;

import com.adam.config.IConfig;
import com.adam.ui.panel.ImagePanel;
import com.adam.util.ZhaoChaUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 找茬按钮点击事件
 * Created by Adam on 2019/8/31 10:57.
 */
public class ZhaoChaButtonListener implements ActionListener {
    private IConfig config;
    private ImagePanel imagePanel;

    public ZhaoChaButtonListener(IConfig config, ImagePanel imagePanel) {
        this.config = config;
        this.imagePanel = imagePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread() {
            @Override
            public void run() {
                if (config.isSave()) {
                    String imageName = ZhaoChaUtil.showDiffImage(config);
                    imagePanel.setImageName(imageName);
                } else {
                    Image image = ZhaoChaUtil.getDiffImage(config);
                    imagePanel.setImage(image);
                }
                imagePanel.repaint();
            }
        }.start();
    }
}
