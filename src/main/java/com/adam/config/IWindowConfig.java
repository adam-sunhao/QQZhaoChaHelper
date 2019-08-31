package com.adam.config;

import com.sun.jna.platform.win32.WinDef;

/**
 * Created by Adam on 2019/8/24 18:41.
 */
public interface IWindowConfig {

    String getWindowName();

    WinDef.HWND getHwnd();
}
