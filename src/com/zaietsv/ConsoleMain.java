package com.zaietsv;

import com.zaietsv.images_comparator.ImagesComparator;
import com.zaietsv.images_comparator.ImagesComparatorImpl;
import com.zaietsv.images_reader.ImagesReader;
import com.zaietsv.images_reader.ImagesReaderException;
import com.zaietsv.images_reader.ImagesReaderImpl;
import com.zaietsv.images_writer.ImagesWriter;
import com.zaietsv.images_writer.ImagesWriterException;
import com.zaietsv.images_writer.ImagesWriterImpl;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Voww on 05.11.2015.
 */
public class ConsoleMain {

    public static void main(String[] args) {
        File currentDir = new File("resources/");
        String path = currentDir.getAbsolutePath();
        String img1Url, img2Url, imgDestUrl;
        if (args.length == 3) {
            img1Url = path + args[0];
            img2Url = path + args[1];
            imgDestUrl = path + args[2];
        } else {
            img1Url = path + "/src_img_1.png";
            img2Url = path + "/src_img_2.png";
            imgDestUrl = path + "/dst_img.png";
        }

        ImagesReader reader = new ImagesReaderImpl();
        ImagesWriter writer = new ImagesWriterImpl();
        ImagesComparator comparator = new ImagesComparatorImpl();
        try {
            BufferedImage img_1 = reader.readImage(img1Url);
            BufferedImage img_2 = reader.readImage(img2Url);
            BufferedImage dst_img = new BufferedImage(img_1.getWidth(), img_1.getHeight(), BufferedImage.TYPE_INT_RGB);
            comparator.compareImages(img_1, img_2, dst_img);
            writer.writeImage(dst_img, imgDestUrl);
        } catch (ImagesReaderException e) {
            System.out.println("An image Reading error");
            e.printStackTrace();
        } catch (ImagesWriterException e) {
            System.out.println("An image Writing error");
            e.printStackTrace();
        }
    }
}
