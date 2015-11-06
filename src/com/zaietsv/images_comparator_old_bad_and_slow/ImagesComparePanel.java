package com.zaietsv.images_comparator_old_bad_and_slow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User interface for images comparing.
 * @author Voww
 *
 */
public class ImagesComparePanel extends JPanel implements ActionListener {

	//class constants 
	//serial UID
	private static final long serialVersionUID = -3476736034399366407L;
	
	//user friendly interface dimensions
	private static final int BI_HEIGHT = 300;
	private static final int BI_WIDTH = BI_HEIGHT * 2;
	public static int SCALE_DISTANCE = 7;
	public static int HALF_SCALE_DISTANCE = (SCALE_DISTANCE + 1) / 2;
	public static double DEVIATION = 0.1;
	
	//class variables
	//list of specified areas
	private LinkedBlockingQueue<Area> areas = new LinkedBlockingQueue<Area>();
	
	//images path
	private String path1;
	private String path2;
	
	//buffered images to process 
	private BufferedImage bufferedImage1 = new BufferedImage(BI_WIDTH, BI_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bufferedImage2 = new BufferedImage(BI_WIDTH, BI_HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//images visual representation
	private JLabel firstImageLabel;
	private JLabel secondImageLabel;
	
	//controls
	private JButton openImage1Button;
	private JButton openImage2Button;
	private JButton compareImagesButton;
	private JFileChooser fileChooser;
	
	//constructor of user interface panel
	public ImagesComparePanel() {
	
		//file chooser block for images
		File currentDir = new File("./");
		fileChooser = new JFileChooser(currentDir);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		fileChooser.setFileFilter(filter);
		  
		//control buttons block
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
		  
		//user interface panel block
		JPanel imgsPanel = new JPanel();
		firstImageLabel = new JLabel(new ImageIcon(bufferedImage1));
		firstImageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		secondImageLabel = new JLabel(new ImageIcon(bufferedImage2));
		secondImageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//adding elements into the user interface panel
		imgsPanel.setLayout(new BorderLayout());
		imgsPanel.add(firstImageLabel, BorderLayout.CENTER);
		imgsPanel.add(secondImageLabel, BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		add(imgsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	//opens first image and creates it`s visual representation (label) on the user panel
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
	              bufferedImage1 = resized;
	              firstImageLabel.setIcon(new ImageIcon((Image) bufferedImage1));
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }
	  }
	}
	
	//opens second image and creates it`s visual representation (label)
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
	          bufferedImage2 = resized;
	          secondImageLabel.setIcon(new ImageIcon((Image) bufferedImage2));
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }
	}

	//creates and fills user interface window
	static void showUserInterface() {
	  JFrame frame = new JFrame("Compare Two Images");
	  frame.getContentPane().add(new ImagesComparePanel());
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.pack();
	  frame.setLocationRelativeTo(null);
	  frame.setVisible(true);
	}
	
	//action for this class event listener
	public void actionPerformed(ActionEvent e) {
		
	//opens images and places them into panel
	  if (e.getSource() == openImage1Button) {
	      openImage1();
	  } else if (e.getSource() == openImage2Button) {
	      openImage2();
	      
	   //compares images and writes out the result to a file and on the panel
	  } else if (e.getSource() == compareImagesButton) {
	      try {
	          if (path1 != null && path2 != null) {
	        	  
	              BufferedImage image1 = ImageIO.read(new File(path1));
	              BufferedImage image2 = ImageIO.read(new File(path2));
	              
	              compareImages(image1, image2, path2);
	              
	              BufferedImage bufferedImage = ImageIO.read(new File(path2.replace(".", "_result.")));
	              BufferedImage resized = new BufferedImage(BI_WIDTH, BI_HEIGHT, bufferedImage.getType());
	              
	              Graphics2D g = resized.createGraphics();
	              g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	              g.drawImage(bufferedImage, 0, 0, BI_WIDTH, BI_HEIGHT, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
	              g.dispose();
	              
	              bufferedImage = resized;
	              secondImageLabel.setIcon(new ImageIcon((Image) bufferedImage));
	          }
	      } catch (IOException e1) {
	          e1.printStackTrace();
	      }
	  }
	}
	
	//compares images pixels by identity and creates new result image specified with rectangles
	public void compareImages(BufferedImage sourceImage1, BufferedImage sourceImage2, String path) {
		
	  areas = new LinkedBlockingQueue<Area>();
	  //long counter = 0;
	  
	  if (sourceImage1.getWidth() == sourceImage2.getWidth() && sourceImage1.getHeight() == sourceImage2.getHeight()) {
	      for (int x = 0; x < sourceImage1.getWidth(); x++) {
	          for (int y = 0; y < sourceImage1.getHeight(); y++) {
	        	  
	        	  //places different pixels into a new or the existing area
	              if ((sourceImage1.getRGB(x, y) > ((1 - DEVIATION) * sourceImage2.getRGB(x, y))) 
	            		   || (sourceImage1.getRGB(x, y) < ((1 + DEVIATION) * sourceImage2.getRGB(x, y)))) {
	            	  //System.out.println(counter ++);
	            	  
	                  if (areas.isEmpty()) {
	                      createArea(new Point(x, y));
	                  } else {
	                      chooseArea(new Point(x, y));
	                  }
	              }
	          }
	      }
	      
	      //creates result image
	      try {
	          BufferedImage resultImage = ImageIO.read(new File(path));
	          
	          //preares result image
	          Graphics2D g2d = resultImage.createGraphics();
	          g2d.setColor(new Color(255, 0, 0));
	          g2d.setStroke(new BasicStroke(1));
	          
	        //marks deviation areas by colored rectangles
	          for (Area area : areas) {
	              g2d.draw(area.getPolygon().getBounds());
	          }
	          
	          //writes result image into a new file
	          ImageIO.write(resultImage, "png", new File(path.replace(".", "_result.")));
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }
	}
	
	//creates new area for specified pixel
	private synchronized void createArea(Point point) {
	  if (areas == null) {
	      areas = new LinkedBlockingQueue<Area>();
	  }
	  Area area = new Area();
	  area.setInitialPoint(point);
	  area.getArea().add(new Point(point.x, point.y));
	  areas.add(area);
	}
	
	// adds specified pixel into existing area
	private synchronized void chooseArea(Point point) {
	  synchronized (areas) {
		  
	      boolean runnedFirst = true;
	      Area minArea = null;
	      int minDistance = 0;
	      
	      for (Area area : areas) {
	          for (Point p : area.getArea()) {
	              if (runnedFirst) {
	                  minDistance = distance(p, point);
	                  minArea = area;
	                  runnedFirst = false;
	              } else {
	                  int distance = distance(p, point);
	                  if (minDistance > distance) {
	                      minArea = area;
	                      minDistance = distance;
	                  }
	              }
	          }
	      }
	      if (minDistance >= HALF_SCALE_DISTANCE && minDistance <= SCALE_DISTANCE) {
	          minArea.getArea().add(new Point(point.x, point.y));
	      } else if (minDistance < HALF_SCALE_DISTANCE) {
	          minArea.getInitialPoint().setLocation(point.x, point.y);
	      } else {
	          createArea(point);
	      }
	  }
	}
	
	//calculates a distance between two points
	public static int distance(Point p1, Point p2) {
	  return (int) Math.sqrt(Math.pow((double) (p1.x - p2.x), 2) + Math.pow((double) (p1.y - p2.y), 2));
	}
}
