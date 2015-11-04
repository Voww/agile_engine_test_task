package com.zaietsv.reader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Voww on 03.11.2015.
 */
public class ConcreteImageReadWriter extends AbstractImageReadWriter {

    @Override
    public BufferedImage readImage(String imgPath) throws ImageReadWriterException {
        File imgFile = new File(imgPath);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(imgFile);
        } catch (IOException e) {
            throw  new ImageReadWriterException("Can't read the image source " + imgPath, e);
        }
        return bufferedImage;
    }

    @Override
    public void writeImage(BufferedImage image, String imgPath) throws ImageReadWriterException {
        File imgFile = new File(imgPath);
        try {
            ImageIO.write(image, "png", imgFile);
        } catch (IOException e) {
            throw  new ImageReadWriterException("Can't write image to the " + imgPath, e);
        }

    }
}
