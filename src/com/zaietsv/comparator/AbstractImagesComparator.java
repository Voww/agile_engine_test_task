package com.zaietsv.comparator;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 03.11.2015.
 */
public abstract class AbstractImagesComparator implements ImagesComparator {


    @Override
    public abstract void compareImages(BufferedImage src_img_1, BufferedImage src_img_2, BufferedImage dst_img);
}
