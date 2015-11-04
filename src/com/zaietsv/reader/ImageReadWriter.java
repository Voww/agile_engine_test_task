package com.zaietsv.reader;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 03.11.2015.
 */
public interface ImageReadWriter {

    BufferedImage readImage(String path) throws ImageReadWriterException;
    void writeImage (BufferedImage image, String path) throws ImageReadWriterException;

}
