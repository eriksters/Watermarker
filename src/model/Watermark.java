package model;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Watermark {
	
	BufferedImage watermarkImage;
	
	//int[] rightBottomOffset = new int[2];	//x=[0], y=[1]
	//int sizeRatio;
	
	public Watermark(File wm) throws IOException {
		
		try {
			watermarkImage = ImageIO.read(wm);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Could not read file " + wm.getName());
		}
	}
	
	/**
	 * Embeds an image watermark over a source image to produce
	 * a watermarked one.
	 * @param sourceImageFile The source image file.
	 * @param destImageFile The output image file.
	 */
	public void addToImage(File sourceImageFile, File destImageFile, double xOffset, double yOffset, float sizeRatio, float transparancy) {
	    try {
	        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
	 
	        // initializes necessary graphic properties
	        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparancy);
	        g2d.setComposite(alphaChannel);
	       
	       
	        //Calculates size of watermark
	        int ySize = (int) (sourceImage.getHeight() * sizeRatio);
	        double widthToHeightRatio = watermarkImage.getWidth()/watermarkImage.getHeight();
	        int xSize = (int) (ySize / widthToHeightRatio);
	        
	        // calculates the coordinate where the image is painted
	        int topLeftX = (int) (sourceImage.getWidth() * (1 - xOffset)) - xSize;
	        int topLeftY = (int) (sourceImage.getHeight() * (1 - yOffset)) - ySize;
	        

	        
	        // paints the image watermark
	        g2d.drawImage(watermarkImage, topLeftX, topLeftY, xSize, ySize, null);
	        
	        ImageIO.write(sourceImage, "png", destImageFile);
	        g2d.dispose();
	 
	        System.out.println("The image watermark is added to the image: " + destImageFile.getName());
	        System.out.println("topLeftX: " + topLeftX + ", topLeftY: " + topLeftY);
	        System.out.println("xSize: " + xSize + ", ySize: " + ySize + ", Width to height ratio: " + widthToHeightRatio);
	 
	    } catch (IOException ex) {
	        System.err.println(ex);
	    }
	}
}
