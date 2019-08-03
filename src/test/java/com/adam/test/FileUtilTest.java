package com.adam.test;

import com.adam.util.FileUtil;
import com.adam.util.PictureUtil;
import com.adam.util.WindowUtil;
import com.sun.jna.platform.win32.WinDef;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Adam on 2019/7/28 17:03.
 */
public class FileUtilTest {

    private String windowName = "大家来找茬";

    @Test
    public void testGetPictureByRectangle() {
        long start = System.currentTimeMillis();
        WinDef.HWND hwnd = WindowUtil.getHwndByWindowName(windowName);
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if(rectangle == null) {
            return;
        }
        try {
            Rectangle rect1 = new Rectangle((int)rectangle.getX() + PictureUtil.FIRSTIMAGE_X_OFFSET,(int)rectangle.getY() + PictureUtil.Y_OFFSET,PictureUtil.IMAGE_WIDTH,PictureUtil.IMAGE_HEIGHT);
            Rectangle rect2 = new Rectangle((int)rectangle.getX() + PictureUtil.SECONDIMAGE_X_OFFSET,(int)rectangle.getY() + PictureUtil.Y_OFFSET,PictureUtil.IMAGE_WIDTH,PictureUtil.IMAGE_HEIGHT);
            String filePath = "images/" + start + "_1.bmp";
            String filePath2 = "images/" + start + "_2.bmp";
            //FileUtil.writeToFile(filePath, PictureUtil.getDiff(PictureUtil.getScreenShot(rect1),PictureUtil.getScreenShot(rect2)));
            FileUtil.writeToFile(filePath, PictureUtil.getScreenShot(rect1));
            FileUtil.writeToFile(filePath2, PictureUtil.getScreenShot(rect2));
        } catch (IOException | AWTException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        long end = System.currentTimeMillis();
        System.out.println("共计用时：" + (end - start) + "ms");
        Assert.assertTrue(true);
    }

    @Test
    public void testGetDiff() {
        long id = 1564323456703L;
        String filePath = "images/" + id + "_1.bmp";
        String filePath2 = "images/" + id + "_2.bmp";

        long start = System.currentTimeMillis();
        try {
            byte[] diffBytes = PictureUtil.getDiff(FileUtil.fileToByteArray(filePath),FileUtil.fileToByteArray(filePath2));
            FileUtil.writeToFile("images/" + id + "_different.bmp" , diffBytes);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        long end = System.currentTimeMillis();
        System.out.println("共计用时：" + (end - start) + "ms");
        Assert.assertTrue(true);
    }

    @Test
    public void testGetJpgImage() throws IOException, AWTException {

        WinDef.HWND hwnd = WindowUtil.getHwndByWindowName("TNT");
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if(rectangle == null) {
            return;
        }
        String filePath = "images/test.png";
        FileUtil.writeToFile(filePath, PictureUtil.getScreenShot(rectangle,"jpg"));

    }

    @Test
    public void testFileToByteArray() throws IOException {
        String filepath = "images/test.png";
        System.out.println(FileUtil.fileToByteArray(filepath).length);
    }
}
