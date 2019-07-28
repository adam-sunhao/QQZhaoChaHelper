package com.adam.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;

/**
 * Created by Adam on 2019/7/28 13:41.
 */
public class WindowUtil {

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;

    /**
     * Handle to a window.
     * 获取指定标题的窗口句柄，如果有多个相同名字的窗口，
     * 只会返回状态栏从左往右第一个符合条件的窗口
     * @param windowName 窗口标题
     * @return
     */
    public static WinDef.HWND getHwndByWindowName(String windowName) {
        if(windowName == null) {
            throw new NullPointerException("Window's Name is null!");
        }
        return User32.INSTANCE.FindWindow(null, windowName);
    }

    /**
     * 将窗口置顶
     * @param hwnd 窗口句柄
     * @return
     */
    public static boolean setForegroundWindow(WinDef.HWND hwnd) {
        return User32.INSTANCE.SetForegroundWindow(hwnd);
    }

    /**
     * 通过窗口标题名获取窗口左上坐标，宽，高
     *
     * @param windowName 窗口标题名
     * @return
     */
    public static Rectangle getRectangleByWindowName(String windowName) {
        return getRectangleByHWND(getHwndByWindowName(windowName));
    }

    /**
     *
     * @param hwnd 窗口句柄
     * @return
     *         窗口相关位置信息
     * @throws NullPointerException
     *          如果句柄为空，抛出空指针异常
     *
     */
    public static Rectangle getRectangleByHWND(WinDef.HWND hwnd) throws NullPointerException{
        if (hwnd == null) {
            throw new NullPointerException("HWND is null!");
        }
        /*
         * SetForegroundWindow(hwnd)
         * 将窗口置顶，如果此时窗口最小化，则没有效果
         * 置不置前并不影响返回数据
         * */
        setForegroundWindow(hwnd);
        WinDef.RECT rect = new WinDef.RECT();
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        //判断此时窗口是否在后台(最小化)
        if(rect.top < 0 && rect.bottom < 0 && rect.left < 0 && rect.right < 0) {
            return null;
        }
        return rect.toRectangle();
    }

    /**
     * Changes the position and dimensions of the specified window. For a
     * top-level window, the position and dimensions are relative to the
     * upper-left corner of the screen. For a child window, they are relative to
     * the upper-left corner of the parent window's client area.
     *
     * @param hWnd
     *            A handle to the window.
     *
     * @param X
     *            The new position of the left side of the window.
     *
     * @param Y
     *            The new position of the top of the window.
     *
     * @param nWidth
     *            The new width of the window.
     *
     * @param nHeight
     *            The new height of the window.
     *
     * @param bRepaint
     *
     * @return
     */
    public static boolean changeWindowSizeAndPosition(WinDef.HWND hWnd, int X, int Y, int nWidth, int nHeight,
                                     boolean bRepaint) {
        return User32.INSTANCE.MoveWindow(hWnd, X, Y, nWidth, nHeight, bRepaint);
    }

    /**
     * 重绘窗口位置，使用默认的宽和高
     * @param hWnd
     *          窗口句柄
     * @param X
     *          窗口距离左侧位置
     * @param Y
     *          窗口距离顶端的位置
     * @param bRepaint
     *          是否重绘
     * @return
     *          移动窗口成功或失败
     */
    public static boolean changeWindowSizeAndPosition(WinDef.HWND hWnd, int X, int Y, boolean bRepaint) {
        return changeWindowSizeAndPosition(hWnd, X, Y, WINDOW_WIDTH, WINDOW_HEIGHT, bRepaint);
    }

    /**
     * 获取电脑屏幕大小
     * e.g: 1024 * 768
     * @return
     */
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

}

