package com.zaietsv.images_reader;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 05.11.2015.
 */
public interface ImagesReader {

    BufferedImage readImage(String path) throws ImagesReaderException;
}
