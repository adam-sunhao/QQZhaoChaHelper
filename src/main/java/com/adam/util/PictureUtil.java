package com.adam.util;

import com.adam.config.IZcConfig;
import com.adam.exception.ImageTypeWrongException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam on 2019/7/28 16:38.
 */
public class PictureUtil {

    //每2^SQUARE * 2^SQUARE 像素作为一个点
    public static final int SQUARE = 2;

    public static BufferedImage getBufferedImage(Rectangle rectangle) throws AWTException {
        return new Robot().createScreenCapture(rectangle);
    }

    /**
     * @param rectangle 窗口所在的Rectangle
     * @param config
     * @return
     * @throws AWTException
     */
    public static BufferedImage getDiff(Rectangle rectangle, IZcConfig config) throws AWTException {
        Rectangle rect1 = new Rectangle((int) rectangle.getX() + config.getFirstImageXOffset(), (int) rectangle.getY() + config.getFirstImageYOffset(), config.getImageWidth(), config.getImageHeight());
        Rectangle rect2 = new Rectangle((int) rectangle.getX() + config.getSecondImageXOffset(), (int) rectangle.getY() + config.getSecondImageYOffset(), config.getImageWidth(), config.getImageHeight());
        return getDiff(rect1, rect2, config);
    }

    public static BufferedImage getDiff(Rectangle rect1, Rectangle rect2, IZcConfig config) throws AWTException {
        return getDiff(getBufferedImage(rect1), getBufferedImage(rect2), config);
    }

    public static BufferedImage getDiff(BufferedImage image1, BufferedImage image2, IZcConfig config) {
        int x = image1.getMinX();
        int y = image1.getMinY();
        int width = image1.getWidth();
        int height = image1.getHeight();

        byte[][] flags = new byte[(width >> SQUARE) + 1][(height >> SQUARE) + 1];
        //int offset = (int)Math.pow(2,SQUARE);
        for (int i = x; i < width; i++) {
            for (int j = y; j < height; j++) {
                int iFlag = i >> SQUARE;
                int jFlag = j >> SQUARE;
                if (flags[iFlag][jFlag] == 1) {
                    continue;
                }
                int rgb1 = image1.getRGB(i, j);
                int rgb2 = image2.getRGB(i, j);
                int r1 = (rgb1 >> 16) & 0xFF;
                int g1 = (rgb1 >> 8) & 0xFF;
                int b1 = (rgb1 >> 0) & 0xFF;
                int r2 = (rgb2 >> 16) & 0xFF;
                int g2 = (rgb2 >> 8) & 0xFF;
                int b2 = (rgb2 >> 0) & 0xFF;
                if (Math.abs(r1 - r2) > config.getRThreshold() && Math.abs(g1 - g2) > config.getGThreshold() && Math.abs(b1 - b2) > config.getBThreshold()) {
                    flags[iFlag][jFlag] = 1;
                }
            }
        }

        for (int i = x; i < width; i++) {
            for (int j = y; j < height; j++) {
                if (flags[i >> SQUARE][j >> SQUARE] == 1) {
                    image1.setRGB(i, j, ((255 & 0xFF) << 24) |
                            ((0 & 0xFF) << 16) |
                            ((255 & 0xFF) << 8) |
                            ((0 & 0xFF) << 0));
                }
            }
        }
        return image1;
    }

    public static void isRightImageFormat(String imageType) {
        if (imageType == null) {
            throw new NullPointerException("请输入文件类型!");
        }
        List<String> imageTypeList = Arrays.asList("jpg", "png", "bmp");
        if (!imageTypeList.contains(imageType.toLowerCase())) {
            throw new ImageTypeWrongException("图片类型有误!");
        }
    }
}
