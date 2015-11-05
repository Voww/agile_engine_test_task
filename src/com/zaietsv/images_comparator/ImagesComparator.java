package com.zaietsv.images_comparator;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 05.11.2015.
 */
public interface ImagesComparator {

    void compareImages(BufferedImage src_img_1, BufferedImage src_img_2, BufferedImage dst_img);
}
