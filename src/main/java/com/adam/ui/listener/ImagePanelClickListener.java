package com.adam.ui.listener;

import com.adam.config.IConfig;
import com.adam.util.MouseUtil;
import com.adam.util.WindowUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 面板点击事件
 * Created by Adam on 2019/8/31 11:10.
 */
public class ImagePanelClickListener implements MouseListener {

    private IConfig config;
    //记录点击时的鼠标位置
    private Point beforePoint;

    public ImagePanelClickListener(IConfig config) {
        this.config = config;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            beforePoint = MouseUtil.getMouseLocation();
            //System.out.println("双击！" + e.getPoint());
            Point offsetPoint = e.getPoint();
            try {
                Rectangle rectangle = WindowUtil.getRectangleByHWND(config.getHwnd());
                MouseUtil.movePointerTo((int)rectangle.getX() + config.getFirstImageXOffset() + (int) offsetPoint.getX(),
                        (int)rectangle.getY() + config.getFirstImageYOffset() + (int) offsetPoint.getY());
                MouseUtil.clickMouseButton("left");
            } catch (AWTException e1) {
                System.out.println("---鼠标异常事件----");
            } finally {
                try {
                    MouseUtil.movePointerTo(beforePoint);
                } catch (AWTException e1) {
                    System.out.println("---鼠标重置失败----");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
