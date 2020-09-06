package hackerrank;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class MyImagewriter {
	public MyImagewriter(int width, int height, Point queen, List<Point> red, List<Point> green) {
		super();
		this.width = width;
		this.height = height;
		this.queen = queen;
		this.red = red;
		this.green = green;
	}

	public MyImagewriter() {
		// TODO Auto-generated constructor stub
	}

	int width;
	int height;
	Point queen;
	List<Point> red = new ArrayList<Point>();
	List<Point> green = new ArrayList<Point>();

	public static void main(String[] args) {
		MyImagewriter iw = new MyImagewriter();
		iw.width = 1000;
		iw.height = 1000;
		iw.queen = new Point(500, 600);
		for (int i = 1; i < 1000; i = i + 10) {
			iw.red.add(new Point(i, i));
		}
		iw.write();
	}

	public void write() {
		BufferedImage img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_BINARY);
		// file object
		File f = null;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				int a = 255; // alpha
				int r = 255; // red
				int g = 255; // green
				int b = 255; // blue
				int p = (a << 24) | (r << 16) | (g << 8) | b;
				img.setRGB(x, y, p);
			}
		}

		for (Point pnt : this.red) {
			int a = 255; // alpha
			int r = 255; // red
			int g = 0; // green
			int b = 0; // blue
			int p = (a << 24) | (r << 16) | (g << 8) | b;
			img.setRGB((int) pnt.getX(), (int) pnt.getY(), p);
		}

		for (Point pnt : this.green) {
			int a = 255; // alpha
			int r = 0; // red
			int g = 255; // green
			int b = 0; // blue
			int p = (a << 24) | (r << 16) | (g << 8) | b;
			img.setRGB((int) pnt.getX(), (int) pnt.getY(), p);
		}

		img.setRGB((int) queen.getX(), (int) queen.getY(), (int) (255 << 24) | (255 << 16) | (255 << 8) | 0);

		try {
			f = new File("Output.jpg");
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
