package com.zaietsv.images_reader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Voww on 05.11.2015.
 */
public class ImagesReaderImpl implements ImagesReader {

    @Override
    public BufferedImage readImage(String imgPath) throws ImagesReaderException {
        File imgFile = new File(imgPath);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(imgFile);
        } catch (IOException e) {
            throw  new ImagesReaderException("Can't read the image source " + imgPath, e);
        }
        return bufferedImage;
    }
}
