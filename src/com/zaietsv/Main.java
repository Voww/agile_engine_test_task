package com.zaietsv;

import com.zaietsv.comparator.ConcreteImagesComparator;
import com.zaietsv.comparator.ImagesComparator;
import com.zaietsv.reader.ConcreteImageReadWriter;
import com.zaietsv.reader.ImageReadWriter;
import com.zaietsv.reader.ImageReadWriterException;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        String img_1_url = "E:\\Work\\JAVA-training\\IdeaProjects\\agile_engine_test_task\\resources\\src_img_1.png";
        String img_2_url = "E:\\Work\\JAVA-training\\IdeaProjects\\agile_engine_test_task\\resources\\src_img_2.png";
        String img_dest_url = "E:\\Work\\JAVA-training\\IdeaProjects\\agile_engine_test_task\\resources\\dst_img.png";
        ImageReadWriter readWriter = new ConcreteImageReadWriter();
        ImagesComparator comparator = new ConcreteImagesComparator();
        try {
            BufferedImage img_1 = readWriter.readImage(img_1_url);
            BufferedImage img_2 = readWriter.readImage(img_2_url);
            //BufferedImage dst_img = new BufferedImage(img_1.getWidth(), img_1.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage dst_img = img_1;
            comparator.compareImages(img_1, img_2, dst_img);
            readWriter.writeImage(dst_img, img_dest_url);
        } catch (ImageReadWriterException e) {
            System.out.println("An image Reading error");
            e.printStackTrace();
        }

    }
}
