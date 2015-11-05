package com.zaietsv.images_writer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Voww on 05.11.2015.
 */
public class ImagesWriterImpl implements ImagesWriter {

    @Override
    public void writeImage(BufferedImage image, String imgPath) throws ImagesWriterException {
        File imgFile = new File(imgPath);
        try {
            ImageIO.write(image, "png", imgFile);
        } catch (IOException e) {
            throw  new ImagesWriterException("Can't write image to the " + imgPath, e);
        }

    }
}
