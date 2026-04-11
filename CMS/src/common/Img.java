package common;
/*
 * this is the class for image icons source
 */

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Img {
		public final static ImageIcon applogo = new ImageIcon("assets/img/applogo.png");
		public final static ImageIcon clogoing = new ImageIcon("assets/img/logo1.jpg");
		public final static ImageIcon adminloginlogo = new ImageIcon("assets/icon/adminlogin.png");
		public final static ImageIcon facultyloginlogo = new ImageIcon("assets/icon/facultylogin.png");
		public final static ImageIcon studentloginlogo = new ImageIcon("assets/icon/studentlogin.png");
		public final static ImageIcon usericon = new ImageIcon("assets/icon/userid.png");
		public final static ImageIcon passicon = new ImageIcon("assets/icon/password1.png");
		
		public ImageIcon getimageicon(String url) {
			ImageIcon img = new ImageIcon(url);
			return img;
		}
		
		public ImageIcon getroundimage(BufferedImage img) {
			
			BufferedImage inputImage = img;
	       
			 int diameter = 132 ;// Math.min(inputImage.getWidth(), inputImage.getHeight());
		        BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

		        Graphics2D g2 = mask.createGraphics();

		        // Enable anti-aliasing for smooth edges
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		        // Create a circular clip shape
		        g2.fill(new Ellipse2D.Double(0, 0, diameter, diameter));

		        g2.dispose();

		        // Prepare output image
		        BufferedImage output = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		        g2 = output.createGraphics();

		        // Enable anti-aliasing
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		        // Set the clip to the circular mask
		        g2.setClip(new Ellipse2D.Double(0, 0, diameter, diameter));

		        // Draw the original image centered scaled or cropped to the diameter
		        int x = (inputImage.getWidth() - diameter) / 2;
		        int y = (inputImage.getHeight() - diameter) / 2;

		        g2.drawImage(inputImage, -x, -y, null);
		        g2.dispose();
		        
		        return new ImageIcon(output);
		}
		
		public BufferedImage resize(String url) {
			try {
	            File input = new File(url);
	            BufferedImage originalImage = ImageIO.read(input);

	            int newWidth = 132;
	            int newHeight = 170;

	            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

	            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = outputImage.createGraphics();
	            g2d.drawImage(scaledImage, 0, 0, null);
	            g2d.dispose();

	            return outputImage;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
		}
}
