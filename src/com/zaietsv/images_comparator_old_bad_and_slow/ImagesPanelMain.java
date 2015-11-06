package com.zaietsv.images_comparator_old_bad_and_slow;

import javax.swing.SwingUtilities;
/**
 * main class for testing ImagesPanel class
 * @author Voww
 *
 */
//
public class ImagesPanelMain {
	
	//main method for testing purposes
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {ImagesComparePanel.showUserInterface();
			}
		});
	}
}
