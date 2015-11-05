package com.zaietsv.images_writer;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 05.11.2015.
 */
public interface ImagesWriter {

    void writeImage(BufferedImage image, String path) throws ImagesWriterException;

}
