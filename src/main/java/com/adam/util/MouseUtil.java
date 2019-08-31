package com.adam.util;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by Adam on 2019/8/31 10:29.
 */
public class MouseUtil {

    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取鼠标当前位置
     *
     * @return
     */
    public static Point getMouseLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    /**
     * 将光标移动到坐标x,y
     *
     * @param x
     * @param y
     */
    public static void movePointerTo(int x, int y) throws AWTException {
        if (robot == null) {
            robot = new Robot();
        }
        robot.mouseMove(x, y);
    }

    public static void movePointerTo(Point point) throws AWTException {
        movePointerTo((int) point.getX(), (int) point.getY());
    }

    /**
     * 鼠标点击
     *
     * @param type left or right
     * @throws AWTException
     */
    public static void clickMouseButton(String type) throws AWTException {
        if (robot == null) {
            robot = new Robot();
        }
        if ("left".equals(type)) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else if ("right".equals(type)) {
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        }

    }

}
