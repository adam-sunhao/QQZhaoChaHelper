package com.adam.ui.panel;

import com.adam.config.IConfig;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2019/8/24 19:03.
 */
public class ImagePanel extends JPanel {

    private Image image;
    private IConfig config;
    private String imageName;

    public void setImage(Image image) {
        this.image = image;
    }

    public void setConfig(IConfig config) {
        if(this.config == null) {
            this.config = config;
        }
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        if (config.isSave() && ("".equals(imageName) || imageName == null)) {
            return;
        }
        if (config.isSave() && !"".equals(imageName)) {
            ImageIcon icon = new ImageIcon(config.getSavePath() + this.imageName + "." + config.getImageType());
            image = icon.getImage();
        }
        if (image == null) {
            return;
        }
        g.drawImage(image, 0, 0, config.getImageWidth(), config.getImageHeight(), this);
    }
}
