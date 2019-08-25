package com.adam.config;

import com.adam.util.WindowUtil;
import com.sun.jna.platform.win32.WinDef;

/**
 * 大家来找茬
 * Created by Adam on 2019/8/24 17:15.
 */
public class DjlzcConfig implements IConfig {

    //图片相关
    private String imageType = "jpg";
    private String savePath = "images/";
    private boolean save = false;

    //需要找不同的图片的宽度 px
    private int imageWidth = 381;
    //需要找不同的图片的高度 px
    private int imageHeight = 286;
    //第一张图片X轴坐标相对窗口位置
    private int firstImageXOffset = 93;
    //第二张照片y轴相对窗口位置
    private int firstImageYOffset = 312;
    //第二张照片X轴相对窗口位置
    private int secondImageXOffset = 550;
    //第二张照片y轴相对窗口位置
    private int secondImageYOffset = 312;
    //判断两个像素点是否相同的阈值
    private int rThreshold = 16;
    private int gThreshold = 15;
    private int bThreshold = 20;

    //窗口名
    private String windowName = "大家来找茬";
    //窗口句柄
    private WinDef.HWND hwnd = null;

    @Override
    public String getImageName() {
        return System.currentTimeMillis() + "";
    }

    @Override
    public String getImageType() {
        return this.imageType;
    }

    @Override
    public String getSavePath() {
        return this.savePath;
    }

    @Override
    public boolean isSave() {
        return this.save;
    }

    @Override
    public int getImageWidth() {
        return this.imageWidth;
    }

    @Override
    public int getImageHeight() {
        return this.imageHeight;
    }

    @Override
    public int getFirstImageXOffset() {
        return this.firstImageXOffset;
    }

    @Override
    public int getFirstImageYOffset() {
        return this.firstImageYOffset;
    }

    @Override
    public int getSecondImageXOffset() {
        return this.secondImageXOffset;
    }

    @Override
    public int getSecondImageYOffset() {
        return this.secondImageYOffset;
    }

    @Override
    public int getRThreshold() {
        return this.rThreshold;
    }

    @Override
    public int getGThreshold() {
        return this.gThreshold;
    }

    @Override
    public int getBThreshold() {
        return this.bThreshold;
    }

    @Override
    public String getWindowName() {
        return this.windowName;
    }

    @Override
    public WinDef.HWND getHwnd() {
        /*if (hwnd == null) {
            hwnd = WindowUtil.getHwndByWindowName(this.windowName);
        }
        return hwnd;*/
        return WindowUtil.getHwndByWindowName(this.windowName);
    }
}
