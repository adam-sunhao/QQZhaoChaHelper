package com.adam.test;

import com.adam.util.WindowUtil;
import com.sun.jna.platform.win32.WinDef;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by Adam on 2019/7/28 14:20.
 */
public class WindowUtilTest {

    private String windowName = "大家来找茬";

    @Test
    public void testGetRectangle() {
        Rectangle rectangle = WindowUtil.getRectangleByWindowName(windowName);
        if(rectangle != null) {
            System.out.println(rectangle);
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testChangeWindowSizeAndPosition() {
        WinDef.HWND hwnd = WindowUtil.getHwndByWindowName(windowName);
        Rectangle rectangle = WindowUtil.getRectangleByHWND(hwnd);
        if(rectangle == null) {
            Assert.assertTrue(true);
        } else {
            WindowUtil.changeWindowSizeAndPosition(hwnd,(int)rectangle.getX(),(int)rectangle.getY(),true);
        }
    }

    @Test
    public void testGetScreenSize() {
        Dimension dimension = WindowUtil.getScreenSize();
        System.out.println("电脑屏幕宽度:" + dimension.getWidth());
        System.out.println("电脑屏幕高度:" + dimension.getHeight());
    }

}
