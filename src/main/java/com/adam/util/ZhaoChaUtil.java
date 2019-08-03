package com.adam.util;

import com.adam.gif.AnimatedGifEncoder;
import com.sun.jna.platform.win32.WinDef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adam on 2019/7/29 23:07.
 */
public class ZhaoChaUtil {

    public static boolean showDiffImage(String windowName) {
        WinDef.HWND hwnd = WindowUtil.getHwndByWindowName(windowName);
        return showDiffImage(hwnd, null, null, true);
    }

    /**
     * 通过文件类型，文件名保存图片,
     * 默认保存在images/路径下
     *
     * @param hwnd 窗口句柄
     * @param imageType 图片类型
     * @param imageName 图片名称
     * @param saveDiffImg 是否保存差异的图片
     * @return 获取差异图片成功或失败
     */
    public static boolean showDiffImage(WinDef.HWND hwnd, String imageType, String imageName, boolean saveDiffImg) {
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if (rectangle == null) {
            return false;
        }
        try {
            String filepath = "";
            if(imageType == null) {
                imageType = "jpg";
            }
            if(imageName == null) {
                imageName = System.currentTimeMillis() + "";
            }
            filepath = "images/" + imageName + "." + imageType;
            /*Rectangle rect1 = new Rectangle((int) rectangle.getX() + PictureUtil.FIRSTIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);
            Rectangle rect2 = new Rectangle((int) rectangle.getX() + PictureUtil.SECONDIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);*/
            BufferedImage bufferedImage = getDiffImage(hwnd);
            if(bufferedImage != null && saveDiffImg) {
                FileUtil.writeToFile(bufferedImage,filepath,imageType);
            }
            return bufferedImage != null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static BufferedImage getDiffImage(WinDef.HWND hwnd) {
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if (rectangle == null) {
            return null;
        }
        try {
            Rectangle rect1 = new Rectangle((int) rectangle.getX() + PictureUtil.FIRSTIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);
            Rectangle rect2 = new Rectangle((int) rectangle.getX() + PictureUtil.SECONDIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);
            return PictureUtil.getDiff(PictureUtil.getBufferedImage(rect1),PictureUtil.getBufferedImage(rect2));
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

}
