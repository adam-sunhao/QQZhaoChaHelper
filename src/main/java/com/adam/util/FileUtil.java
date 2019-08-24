package com.adam.util;

import com.adam.exception.FileArrayIsEmptyException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Adam on 2019/7/28 16:37.
 */
public class FileUtil {

    /**
     * 将文件字节数组写出
     *
     * @param path      写出路径
     * @param fileArray 文件的字节数组
     * @throws IOException
     */
    public static void writeToFile(String path, byte[] fileArray) throws IOException, NullPointerException, FileArrayIsEmptyException {
        if (path == null) {
            throw new NullPointerException("文件路径为空!");
        }
        if (fileArray == null || fileArray.length == 0) {
            throw new FileArrayIsEmptyException("文件数组为空!");
        }
        //BufferedOutputStream 能提高写出速度
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));) {
            bos.write(fileArray);
        }
    }

    public static void writeToFile(BufferedImage bufferedImage, String path, String imageType) throws IOException {
        if (path == null || "".equals(path)) {
            throw new NullPointerException("文件路径为空!");
        }
        if (bufferedImage == null) {
            throw new NullPointerException("BufferedImage 为空!");
        }
        PictureUtil.isRightImageFormat(imageType);
        ImageIO.write(bufferedImage, imageType, new File(path));
    }

    /**
     * 把文件转成字节数组
     *
     * @param filePath 文件路径
     * @return 文件的字节数组
     * @throws IOException
     */
    public static byte[] fileToByteArray(String filePath) throws IOException {
        if (filePath == null) {
            throw new NullPointerException("文件路径不能为空!");
        }
        File file = new File(filePath);
        byte[] a = new byte[(int) file.length()];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            bis.read(a);
        }
        return a;
    }

}
