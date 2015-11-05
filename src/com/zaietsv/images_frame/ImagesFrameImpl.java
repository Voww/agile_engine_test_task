package com.zaietsv.images_frame;

import com.zaietsv.images_comparator.ImagesComparator;
import com.zaietsv.images_comparator.ImagesComparatorImpl;
import com.zaietsv.images_reader.ImagesReader;
import com.zaietsv.images_reader.ImagesReaderException;
import com.zaietsv.images_reader.ImagesReaderImpl;
import com.zaietsv.images_writer.ImagesWriter;
import com.zaietsv.images_writer.ImagesWriterException;
import com.zaietsv.images_writer.ImagesWriterImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Voww on 05.11.2015.
 */
public class ImagesFrameImpl extends JPanel implements ImagesFrame, ActionListener {

    private static final int BI_HEIGHT = 300;
    private static final int BI_WIDTH = BI_HEIGHT * 2;

    private JFileChooser fileChooser;
    private JButton openImage1Button, openImage2Button, compareImagesButton;
    private JLabel firstImageLabel, secondImageLabel;
    private BufferedImage bufferedImage1, bufferedImage2;
    private String path1, path2;

    public ImagesFrameImpl() {
        File currentDir = new File("./resources/");
        fileChooser = new JFileChooser(currentDir);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(filter);

        openImage1Button = new JButton("Open image 1");
        openImage1Button.addActionListener(this);
        openImage2Button = new JButton("Open image 2");
        openImage2Button.addActionListener(this);
        compareImagesButton = new JButton("Compare images");
        compareImagesButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openImage1Button);
        buttonPanel.add(openImage2Button);
        buttonPanel.add(compareImagesButton);

        bufferedImage1 = new BufferedImage(BI_WIDTH, BI_HEIGHT, BufferedImage.TYPE_INT_RGB);
        bufferedImage2 = new BufferedImage(BI_WIDTH, BI_HEIGHT, BufferedImage.TYPE_INT_RGB);
        JPanel imgsPanel = new JPanel();
        firstImageLabel = new JLabel(new ImageIcon(bufferedImage1));
        firstImageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        secondImageLabel = new JLabel(new ImageIcon(bufferedImage2));
        secondImageLabel.setBorder(BorderFactory.createRaisedBevelBorder());

        imgsPanel.setLayout(new BorderLayout());
        imgsPanel.add(firstImageLabel, BorderLayout.CENTER);
        imgsPanel.add(secondImageLabel, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(imgsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void showFrame() {
        JFrame frame = new JFrame("Compare Two Images");
        frame.setVisible(true);
        frame.getContentPane().add(new ImagesFrameImpl());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openImage1Button) {
            openImage1();
        } else if (e.getSource() == openImage2Button) {
            openImage2();

        } else if (e.getSource() == compareImagesButton) {
            try {
                if (path1 != null && path2 != null) {

                    ImagesReader reader = new ImagesReaderImpl();
                    ImagesWriter writer = new ImagesWriterImpl();

                    BufferedImage image1 = reader.readImage(path1);
                    BufferedImage image2 = reader.readImage(path2);
                    BufferedImage dstImage = new BufferedImage(image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_INT_RGB);

                    ImagesComparator comparator = new ImagesComparatorImpl();
                    comparator.compareImages(image1, image2, dstImage);

                    writer.writeImage(dstImage, "dst_img.png");
                    BufferedImage bufferedImage = reader.readImage("dst_img.png");
                    BufferedImage resized = new BufferedImage(BI_WIDTH, BI_HEIGHT, bufferedImage.getType());

                    Graphics2D g = resized.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g.drawImage(bufferedImage, 0, 0, BI_WIDTH, BI_HEIGHT, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
                    g.dispose();

                    secondImageLabel.setIcon(new ImageIcon(resized));
                }
            } catch (ImagesReaderException e1) {
                System.out.println("An image Reading error");
                e1.printStackTrace();
            } catch (ImagesWriterException e2) {
                System.out.println("An image Writing error");
                e2.printStackTrace();
            }
        }
    }

    private void openImage1() {
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File ImageFile1 = fileChooser.getSelectedFile();
            path1 = ImageFile1.getAbsolutePath();

            if (bufferedImage1 != null) {
                try {
                    bufferedImage1 = ImageIO.read(ImageFile1);
                    BufferedImage resized = new BufferedImage(BI_WIDTH, BI_HEIGHT, bufferedImage1.getType());
                    Graphics2D graphics2d = resized.createGraphics();
                    graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    graphics2d.drawImage(bufferedImage1, 0, 0, BI_WIDTH, BI_HEIGHT, 0, 0, bufferedImage1.getWidth(), bufferedImage1.getHeight(), null);
                    graphics2d.dispose();
                    firstImageLabel.setIcon(new ImageIcon(resized));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void openImage2() {
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File imageFile2 = fileChooser.getSelectedFile();
            path2 = imageFile2.getAbsolutePath();

            try {
                bufferedImage2 = ImageIO.read(imageFile2);
                BufferedImage resized = new BufferedImage(BI_WIDTH, BI_HEIGHT, bufferedImage2.getType());
                Graphics2D graphics2d = resized.createGraphics();
                graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2d.drawImage(bufferedImage2, 0, 0, BI_WIDTH, BI_HEIGHT, 0, 0, bufferedImage2.getWidth(), bufferedImage2.getHeight(), null);
                graphics2d.dispose();
                secondImageLabel.setIcon(new ImageIcon(resized));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
