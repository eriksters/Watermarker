package control;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.*;

public class FileController {
	
	private ArrayList<File> images; 
	private File directory;
	private Watermark wm;
	private File destinationDirectory;
	
	public FileController() {
		images = new ArrayList<>();
	}
	
	public void setWatermark(String source) throws IOException {
		wm = new Watermark(new File(source));
	}
	
	public void setDestination(String source) throws IOException {
		
		File dir = new File(source);
		if (dir.isDirectory()) {
			destinationDirectory = dir;
		} else {
			throw new IOException(source + " is not a directory or does not exist");
		}
	}
	
	public File setDirectory(String path) throws IOException {
		
		
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			directory = f;
			return directory;
		} else 
			throw new IOException("Pathname " + path + " not valid!");
	}
	
	/**
	 * 
	 * @return number of images found in the directory
	 */
	public int addImagesFromDirectory(File dirPath) {
		int fileCount = 0;
		File[] files = dirPath.listFiles();
		images.clear();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (!f.isDirectory()) {
				if (f.getName().contains(".png")) {
					addImage(f);
					fileCount++;
				}
			}
		}
		return fileCount;
		
	}
	
//	public void addWM(File waterMark, File source, File output) throws IOException {
//		Watermark wm = new Watermark(waterMark);
//		wm.addToImage(source, output);
//	}
	
	public ArrayList<File> getImages() {
		return images;
	}
	
	public void addImage(File img) {
		images.add(img);
	}
	
	public File getDirectory() {
		return directory;
	}
	
	public void processAll(float xOffset, float yOffset, float sizeRatio, float transparency) throws IOException {
		if (destinationDirectory == null) {
			throw new IOException("No output directory set");
		} else if (wm == null) {
			throw new IOException("No watermark set");
		} else if (images.size() < 1) {
			throw new IOException("There are no images to process");
		}
		
		float trs = (100 - transparency) / 100;
		float ratio = sizeRatio / 100;
		float xOff = xOffset / 100;
		float yOff = yOffset / 100;
		
		File destDir = createDestDir();
		
		for (File image : images) {
			String name = image.getName();
			
			File destFile = new File(destDir.getAbsolutePath() + "\\" + name);
			
			//sourceFile, destinationFile, xOffset Ratio, yOffset ratio, size ratio, transparency
			wm.addToImage(image, destFile, xOff, yOff, ratio, trs);
		}
		
	}
	
	
	
	public File createDestDir() throws IOException {
		Date date = new Date();
	    String strDateFormat = "YYYY-MM-dd_HH.mm.ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		System.out.println("DateTime: " + formattedDate);

		File destDir = new File(destinationDirectory.getAbsolutePath() + "\\" + formattedDate);
		try {
			System.out.println("Creating directory: " + destDir.getPath());
			destDir.mkdirs();
			
		} catch (SecurityException e) {
			throw new IOException("Can not create directory " + destDir.getAbsolutePath() + " because of security reasons!");
		}
		return destDir;
	}
	
/*//	public Image getSampleImage(File source) throws IOException {
//		
//		Toolkit t = Toolkit.getDefaultToolkit();
//		Image i = t.getImage(source.getAbsolutePath());
//		
//		return i;
//		
////		if (wm == null) {
////			BufferedImage img = ImageIO.read(source);
////			
////			ImageIcon ii = new ImageIcon(img);
////			
////			
////			return ii;
////		}
////		return null;
//		
//		
//		
//	}
*/	
}
