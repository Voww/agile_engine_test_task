package com.zaietsv.reader;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 03.11.2015.
 */
public abstract class AbstractImageReadWriter implements ImageReadWriter {

    @Override
    public abstract BufferedImage readImage(String path) throws ImageReadWriterException;

    @Override
    public abstract void writeImage(BufferedImage image, String path) throws ImageReadWriterException;

}
