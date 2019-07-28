package com.adam.util;

import com.adam.bean.Color;
import com.adam.bean.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Adam on 2019/7/28 16:38.
 */
public class PictureUtil {

    //图片类型
    public static final String IMAGE_TYPE = "BMP";
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

    /**
     * 获取屏幕指定位置截图字节数组
     *
     * @param rectangle
     * @return 图片的字节数组
     * @throws AWTException
     * @throws IOException
     */
    public static byte[] getScreenShot(Rectangle rectangle) throws AWTException, IOException {
        if(rectangle == null) {
            throw new NullPointerException("rectangle is null!");
        }
        BufferedImage bufferedImage = new Robot().createScreenCapture(rectangle);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, IMAGE_TYPE, stream);
        return stream.toByteArray();
    }

    /**
     * 比较两张图片的不同之处  不同点用黑色填充
     *
     * @param image1 图片1的字节数组
     * @param image2 图片2的字节数组
     * @return 不同点的图像字节数组
     * @throws Exception
     */
    public static byte[] getDiff(byte[] image1, byte[] image2) {
        byte[] newImage = image1;
        int width = getWidth(image1);
        int height = getHeight(image1);
        int x = 54;    //蓝颜色处于文件图像的第55位之后,这样从55位开始进行操作,每间隔3位都是处理蓝色
        int y = 55; //绿颜色处于文件图像的第56位之后,这样从56位开始进行操作,每间隔3位都是处理绿色
        int z = 56; //红颜色处于文件图像的第57位之后,这样从57位开始进行操作,每间隔3位都是处理红色
        //纠正位      因为每一扫描行的字节数必须是4的整数倍，所以加入纠正位
        int correct = width * 3 % 4 == 0 ? 0 : 4 - width * 3 % 4;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r1 = image1[z];    //在原图中,从第57位开始,每三位读一次,读出的是红色的数据
                int g1 = image1[y]; //在原图中,从第56位开始,每三位读一次,读出的是绿色的数据
                int b1 = image1[x]; //在原图中,从第55位开始,每三位读一次,读出的是蓝色的数据
                int r2 = image2[z];
                int g2 = image2[y];
                int b2 = image2[x];

                //比较两张图-原图片与比照图片的三原色是否不相同  在新的image里用黑色标识
                /*
                 * 0~255的颜色用byte-128~127表示
					其中0~127对应0~127
					-128~-1 对应128~255
				 */
                //if (r1 + g1 + b1 != r2 + g2 + b2) {
                if(Math.abs(r1 - r2) > 16 && Math.abs(g1 - g2) > 16 && Math.abs(b1 - b2) > 16) {
                    newImage[x] = 0;
                    newImage[y] = 0;
                    newImage[z] = -1;
                }
                //每间隔三位是一种颜色
                x += 3;
                y += 3;
                z += 3;
            }
            //因为每一扫描行的字节数必须是4的整数倍，所以加入纠正位
            x += correct;
            y += correct;
            z += correct;
        }
        return newImage;
    }

    /**
     * 根据图片中某点坐标取该点的颜色
     *
     * @param location
     * @return
     * @throws Exception
     */
    private static Color getColorByLocation(Location location, byte[] image) throws Exception {
        Color color = new Color();
        int width = getWidth(image);
        int height = getHeight(image);
        if ((location.getX() + 1) > width) {
            throw new Exception("宽度过大！");
        }
        if ((location.getY() + 1) > height) {
            throw new Exception("高度过大！");
        }
        int ly = height - location.getY() - 1;
        int correct = width * 3 % 4 == 0 ? 0 : 4 - width * 3 % 4;
        int x = ly * correct + location.getX() * 3 + ly * width * 3 + 54;
        int y = x + 1;
        int z = y + 1;
        color.setR(image[z]);
        color.setG(image[y]);
        color.setB(image[x]);
        return color;
    }

    /**
     * 取图片宽度
     */
    private static int getWidth(byte[] image) {
        return (int) (calc(image[21]) * Math.pow(256, 3) + calc(image[20]) * Math.pow(256, 2) + calc(image[19]) * 256 + calc(image[18]));
    }

    /**
     * 取图片高度
     */
    private static int getHeight(byte[] image) {
        return (int) (calc(image[25]) * Math.pow(256, 3) + calc(image[24]) * Math.pow(256, 2) + calc(image[23]) * 256 + calc(image[22]));
    }

    private static int calc(byte num) {
        return num >= 0 ? num : 256 + num;
    }

}
