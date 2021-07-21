package com.panpan.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Date 2021/7/19 23:35
 * @Author LiuPanpan
 */
public class ImageTest {
    @Test
    void Test(){
        try {
            File file = new File("D:/javaProject/mca/images/tankR.gif");
            System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()));
            BufferedImage image = ImageIO.read(new File("D:/javaProject/mca/images/tankR.gif"));
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            //this.getClass()
            assertNotNull(image2);
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
