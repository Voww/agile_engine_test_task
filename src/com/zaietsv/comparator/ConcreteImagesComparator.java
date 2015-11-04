package com.zaietsv.comparator;

import java.awt.image.BufferedImage;

/**
 * Created by Voww on 03.11.2015.
 */
public class ConcreteImagesComparator extends AbstractImagesComparator {
    public static  final double DEVIATION = 0.1;

    @Override
    public void compareImages(BufferedImage src_img_1, BufferedImage src_img_2, BufferedImage dst_img) {
        int full_width = src_img_1.getWidth();
        int full_height = src_img_1.getHeight();
        if (full_width == 1 && full_height == 1) {
            int rgb_1 = Math.abs(src_img_1.getRGB(0, 0));
            int rgb_2 = Math.abs(src_img_2.getRGB(0, 0));
            double dev;
            if (rgb_1 > rgb_2) {
                dev = (rgb_1 - rgb_2)/(double)rgb_1;
            } else {
                dev = (rgb_2 - rgb_1)/(double)rgb_2;
            }
            if (dev > DEVIATION) {
                dst_img.setRGB(0, 0, 0xFF0000);
            } /*else {
                dst_img.setRGB(0, 0, rgb_1);
            }*/
        } else {
            BufferedImage sub_img_1_0, sub_img_2_0, sub_dst_img_0, sub_img_1_1, sub_img_2_1, sub_dst_img_1;
            int half_height_0, half_height_1, half_width_0, half_width_1;
            if (full_height > full_width) {
                half_height_0 = full_height/2;
                half_height_1 = full_height - half_height_0;
                sub_img_1_0 = src_img_1.getSubimage(0, 0, full_width, half_height_0);
                sub_img_2_0 = src_img_2.getSubimage(0, 0, full_width, half_height_0);
                sub_dst_img_0 = dst_img.getSubimage(0, 0, full_width, half_height_0);
                sub_img_1_1 = src_img_1.getSubimage(0, half_height_0, full_width, half_height_1);
                sub_img_2_1 = src_img_2.getSubimage(0, half_height_0, full_width, half_height_1);
                sub_dst_img_1 = dst_img.getSubimage(0, half_height_0, full_width, half_height_1);
            } else {
                half_width_0 = full_width/2;
                half_width_1 = full_width - half_width_0;
                sub_img_1_0 = src_img_1.getSubimage(0, 0, half_width_0, full_height);
                sub_img_2_0 = src_img_2.getSubimage(0, 0, half_width_0, full_height);
                sub_dst_img_0 = dst_img.getSubimage(0, 0, half_width_0, full_height);
                sub_img_1_1 = src_img_1.getSubimage(half_width_0, 0, half_width_1, full_height);
                sub_img_2_1 = src_img_2.getSubimage(half_width_0, 0, half_width_1, full_height);
                sub_dst_img_1 = dst_img.getSubimage(half_width_0, 0, half_width_1, full_height);
            }
            compareImages(sub_img_1_0, sub_img_2_0, sub_dst_img_0);
            compareImages(sub_img_1_1, sub_img_2_1, sub_dst_img_1);
        }
    }
}
