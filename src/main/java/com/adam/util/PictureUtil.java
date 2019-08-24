package com.adam.util;

import com.adam.exception.ImageTypeWrongException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam on 2019/7/28 16:38.
 */
public class PictureUtil {

    //图片类型
    public static final String IMAGE_TYPE_BMP = "BMP";
    //需要找不同的图片的宽度 px
    public static final int IMAGE_WIDTH = 381;
    //需要找不同的图片的高度 px
    public static final int IMAGE_HEIGHT = 286;
    //第一张图片X轴坐标相对窗口位置
    public static final int FIRSTIMAGE_X_OFFSET = 93;
    //public static final int FIRSTIMAGE_Y_OFFSET = 312;
    //第二张照片X轴相对窗口位置
    public static final int SECONDIMAGE_X_OFFSET = 550;
    //public static final int SECONDIMAGE_Y_OFFSET = 312;
    //一般来说图片是对齐的
    public static final int Y_OFFSET = 312;
    //判断两个像素点是否相同的阈值
    public static final int R_THRESHOLD = 16;
    public static final int G_THRESHOLD = 15;
    public static final int B_THRESHOLD = 20;

    /**
     * 获取屏幕指定位置截图字节数组，默认返回bmp格式
     *
     * @param rectangle
     * @return 图片的字节数组
     * @throws AWTException
     * @throws IOException
     */
    public static byte[] getScreenShot(Rectangle rectangle) throws AWTException, IOException {
        return getScreenShot(rectangle, IMAGE_TYPE_BMP);
    }

    public static byte[] getScreenShot(Rectangle rectangle, String imageType) throws AWTException, IOException {
        if (rectangle == null) {
            throw new NullPointerException("rectangle is null!");
        }
        BufferedImage bufferedImage = new Robot().createScreenCapture(rectangle);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if ("jpg".equals(imageType) || "png".equals(imageType)) {
            ImageIO.write(bufferedImage, imageType, stream);
        } else {
            ImageIO.write(bufferedImage, IMAGE_TYPE_BMP, stream);
        }
        return stream.toByteArray();
    }

    public static BufferedImage getBufferedImage(Rectangle rectangle) throws AWTException {
        return new Robot().createScreenCapture(rectangle);
    }

    public static BufferedImage getDiff(BufferedImage image1, BufferedImage image2) {
        int x = image1.getMinX();
        int y = image1.getMinY();
        int width = image1.getWidth();
        int height = image1.getHeight();
        for (int i = x; i < width; i++) {
            for (int j = y; j < height; j++) {
                int rgb1 = image1.getRGB(i, j);
                int rgb2 = image2.getRGB(i, j);
                /*if(Math.abs(rgb1 - rgb2) > 1000) {
                    image1.setRGB(i,j,16711935);
                }*/
                int r1 = (rgb1 >> 16) & 0xFF;
                int g1 = (rgb1 >> 8) & 0xFF;
                int b1 = (rgb1 >> 0) & 0xFF;
                int r2 = (rgb2 >> 16) & 0xFF;
                int g2 = (rgb2 >> 8) & 0xFF;
                int b2 = (rgb2 >> 0) & 0xFF;
                if (Math.abs(r1 - r2) > R_THRESHOLD && Math.abs(g1 - g2) > G_THRESHOLD && Math.abs(b1 - b2) > B_THRESHOLD) {
                    //image1.setRGB( i, j, new java.awt.Color(0,255,0).getRGB());
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
