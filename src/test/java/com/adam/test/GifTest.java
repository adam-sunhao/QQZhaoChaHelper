package com.adam.test;

import com.adam.gif.AnimatedGifEncoder;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adam on 2019/7/28 22:29.
 */
public class GifTest {

    @Test
    public void testGenerateGif() throws IOException {

        BufferedImage src1 = ImageIO.read(new File("images/temp1.bmp"));
        BufferedImage src2 = ImageIO.read(new File("images/temp2.bmp"));
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
}
