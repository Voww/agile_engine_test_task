package com.zaietsv.images_comparator;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 05.11.2015.
 */
public class ImagesComparatorImpl implements ImagesComparator {
    public static  final double DEVIATION = 0.1;

    @Override
    public void compareImages(BufferedImage srcImg1, BufferedImage srcImg2, BufferedImage dstImg) {

        int fullWidth = srcImg1.getWidth();
        int fullHeight = srcImg1.getHeight();
        if (fullWidth == 1 && fullHeight == 1) {
            int rgb_1 = srcImg1.getRGB(0, 0);
            int rgb_2 = srcImg2.getRGB(0, 0);
            int absRgb1 = Math.abs(rgb_1);
            int absRgb2 = Math.abs(rgb_2);
            double dev;
            if (absRgb1 > absRgb2) {
                dev = (absRgb1 - absRgb2)/(double)absRgb1;
            } else {
                dev = (absRgb2 - absRgb1)/(double)absRgb2;
            }
            if (dev > DEVIATION) {
                dstImg.setRGB(0, 0, 0xFF0000);
            } else {
                dstImg.setRGB(0, 0, rgb_1);
            }
        } else {
            BufferedImage subImg_1_0, subImg_2_0, subDstImg_0, subImg_1_1, subImg_2_1, subDstImg_1;
            int halfHeight_0, halfHeight_1, halfWidth_0, halfWidth_1;
            if (fullHeight > fullWidth) {
                halfHeight_0 = fullHeight/2;
                halfHeight_1 = fullHeight - halfHeight_0;
                subImg_1_0 = srcImg1.getSubimage(0, 0, fullWidth, halfHeight_0);
                subImg_2_0 = srcImg2.getSubimage(0, 0, fullWidth, halfHeight_0);
                subDstImg_0 = dstImg.getSubimage(0, 0, fullWidth, halfHeight_0);
                subImg_1_1 = srcImg1.getSubimage(0, halfHeight_0, fullWidth, halfHeight_1);
                subImg_2_1 = srcImg2.getSubimage(0, halfHeight_0, fullWidth, halfHeight_1);
                subDstImg_1 = dstImg.getSubimage(0, halfHeight_0, fullWidth, halfHeight_1);
            } else {
                halfWidth_0 = fullWidth/2;
                halfWidth_1 = fullWidth - halfWidth_0;
                subImg_1_0 = srcImg1.getSubimage(0, 0, halfWidth_0, fullHeight);
                subImg_2_0 = srcImg2.getSubimage(0, 0, halfWidth_0, fullHeight);
                subDstImg_0 = dstImg.getSubimage(0, 0, halfWidth_0, fullHeight);
                subImg_1_1 = srcImg1.getSubimage(halfWidth_0, 0, halfWidth_1, fullHeight);
                subImg_2_1 = srcImg2.getSubimage(halfWidth_0, 0, halfWidth_1, fullHeight);
                subDstImg_1 = dstImg.getSubimage(halfWidth_0, 0, halfWidth_1, fullHeight);
            }
            compareImages(subImg_1_0, subImg_2_0, subDstImg_0);
            compareImages(subImg_1_1, subImg_2_1, subDstImg_1);
        }
    }
}
