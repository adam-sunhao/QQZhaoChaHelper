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

    public static boolean showDiffImage(String windowName, boolean gifImage) {
        WinDef.HWND hwnd = WindowUtil.getHwndByWindowName(windowName);
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if (rectangle == null) {
            return false;
        }

        try {
            Rectangle rect1 = new Rectangle((int) rectangle.getX() + PictureUtil.FIRSTIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);
            Rectangle rect2 = new Rectangle((int) rectangle.getX() + PictureUtil.SECONDIMAGE_X_OFFSET, (int) rectangle.getY() + PictureUtil.Y_OFFSET, PictureUtil.IMAGE_WIDTH, PictureUtil.IMAGE_HEIGHT);
            if (!gifImage) {
                String filePath = "images/temp.bmp";
                FileUtil.writeToFile(filePath, PictureUtil.getDiff(PictureUtil.getScreenShot(rect1), PictureUtil.getScreenShot(rect2)));
            } else {
                long start = System.currentTimeMillis();
                String filePath = "images/" + start + "_1.bmp";
                String filePath2 = "images/" + start + "_2.bmp";
                //FileUtil.writeToFile(filePath, PictureUtil.getDiff(PictureUtil.getScreenShot(rect1),PictureUtil.getScreenShot(rect2)));
                FileUtil.writeToFile(filePath, PictureUtil.getScreenShot(rect1));
                FileUtil.writeToFile(filePath2, PictureUtil.getScreenShot(rect2));
                BufferedImage src1 = ImageIO.read(new File("images/" + start + "_1.bmp"));
                BufferedImage src2 = ImageIO.read(new File("images/" + start + "_2.bmp"));
                //BufferedImage src3 = ImageIO.read(new File("c:/ship3.jpg"));
                AnimatedGifEncoder e = new AnimatedGifEncoder();
                e.setRepeat(0);
                e.start("images/temp.gif");
                e.setDelay(300); // 1 frame per sec
                e.addFrame(src1);
                e.setDelay(100);
                e.addFrame(src2);
                e.setDelay(100);
                //  e.addFrame(src2);
                e.finish();
            }
        } catch (IOException | AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
