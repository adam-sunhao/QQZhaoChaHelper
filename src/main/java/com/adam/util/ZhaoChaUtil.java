package com.adam.util;

import com.adam.config.IConfig;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Adam on 2019/7/29 23:07.
 */
public class ZhaoChaUtil {

    /**
     * 通过文件类型，文件名保存图片,
     * 默认保存在images/路径下
     *
     * @param config 配置相关
     * @return 获取差异图片成功或失败
     */
    public static String showDiffImage(IConfig config) {
        Rectangle rectangle = WindowUtil.getRectangleByHWND(config.getHwnd());
        if (rectangle == null) {
            return null;
        }
        try {
            String imageName = config.getImageName();
            String filepath = config.getSavePath() + imageName + "." + config.getImageType();
            BufferedImage bufferedImage = getDiffImage(config);
            if (bufferedImage != null && config.isSave()) {
                FileUtil.writeToFile(bufferedImage, filepath, config.getImageType());
            }
            return imageName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage getDiffImage(IConfig config) {
        Rectangle rectangle = WindowUtil.getRectangleByHWND(config.getHwnd());
        if (rectangle == null) {
            return null;
        }
        try {
            return PictureUtil.getDiff(rectangle, config);
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

}
