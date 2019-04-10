package views;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.*;

public class Test {

	FileController fc;
	
	@org.junit.Test
	public void test() throws IOException {
		fc = new FileController();
		
		fc.setDirectory("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes");
		fc.addImagesFromDirectory(fc.getDirectory());
		
		fc.setWatermark("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes\\wm.png");
		
		fc.setDestination("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes\\testing");
		
		fc.processAll(10f, 10f, 20f, 10f);
		
		//fc.addWM(wm, source, dest);
		
	}
	
	@org.junit.Test
	public void testDate() throws IOException {
		fc = new FileController();
		fc.setDestination("C:\\Users\\eriks\\OneDrive\\Attēli\\Testing");
		
		fc.createDestDir();
	}

}
