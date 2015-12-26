package com.alberto.manipulator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alberto.exceptions.ImageManagerException;
import com.alberto.interfaces.ImageManipulable;

public class ImageManipulator implements ImageManipulable {

	private static ImageManipulator myJPGmanipulator = new ImageManipulator();

	private ImageManipulator() {

	}

	public static ImageManipulator getMyJPGmanipulator() {
		return myJPGmanipulator;

	}

	@Override
	public BufferedImage rotateImage(BufferedImage image, short rotation)
			throws ImageManagerException {
		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		int x = 0;
		int y = 0;
		int newWidth;
		int newHeight;
		if (rotation == 0 || rotation == 1) {
			newWidth = image.getHeight();
			newHeight = image.getWidth();
		} else {
			newWidth = image.getWidth();
			newHeight = image.getHeight();
		}
		double degree = ImageManipulable.ANGLES[rotation];
		BufferedImage auxImage = new BufferedImage(newWidth, newHeight,
				image.getType());

		// Giro a la izquierda
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				x = calculateX(i, j, degree, newWidth, newHeight);
				y = calculateY(i, j, degree, newWidth, newHeight);

				auxImage.setRGB(x, y, image.getRGB(i, j));

			}
		}

		/*
		 * for (int i = image.getWidth() - 1 ; i > 0; i--){ for (int j =
		 * image.getHeight() - 1; j > 0; j--){ auxImage.setRGB(j, i,
		 * image.getRGB(i, j)); } }
		 */

		return auxImage;
	}

	private int calculateY(int i, int j, double degree, int xWidth, int yHeight) {

		float xPart = (float) (i * Math.sin(degree));
		float yPart = (float) (j * Math.cos(degree));
		int suma = (int) (xPart + yPart);
		return suma > 0 ? suma : yHeight + suma - 1;

		// return (int) Math.abs(xPart + yPart);
		// return (int) (yHeight + (int) (xPart + yPart) -1);

		// return (int) (/*yHeight - */(i * (Math.cos(degree)) - j *
		// (Math.sin(degree))));

	}

	private int calculateX(int i, int j, double degree, int xWidth, int yHeight) {
		float xPart = (float) (i * Math.cos(degree));
		float yPart = (float) (j * Math.sin(degree));

		int suma = (int) (xPart - yPart);

		return suma > 0 ? suma : xWidth + suma - 1;
		// return (int) Math.abs(xPart - yPart);
		// return ( (int) (/* xWidth - */(i * Math.sin(degree) + j *
		// Math.cos(degree))));
		// return (int) (xWidth + (int) (xPart - yPart) -1);
	}

	@Override
	public BufferedImage openImageFromFile(File imageFile)
			throws ImageManagerException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			throw new ImageManagerException(
					ImageManagerException.IMAGE_READ_ERROR);
		}
		return image;
	}

	@Override
	public boolean saveImage(BufferedImage image, File saveDestiny)
			throws ImageManagerException {

		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}
		boolean result = false;
		try {
			ImageIO.write(image, "JPG", saveDestiny);
			result = true;
		} catch (IOException e) {

		}

		return result;
	}

	@Override
	public boolean toGrayScale(BufferedImage image)
			throws ImageManagerException {
		boolean converted = false;

		Color color = null;
		int colorMed;
		int colorSRGB;

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				color = new Color(image.getRGB(i, j));

				colorMed = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
				colorSRGB = (colorMed << 16) | (colorMed << 8) | colorMed;
				image.setRGB(i, j, colorSRGB);
			}
		}

		return converted;
	}

	@Override
	public BufferedImage[] splitImage(BufferedImage image, boolean horizontal)
			throws ImageManagerException {

		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		BufferedImage[] result = new BufferedImage[2];

		if (horizontal) {
			int verticalPoint = image.getHeight() / 2;
			result[0] = image
					.getSubimage(0, 0, image.getWidth(), verticalPoint);
			result[1] = image.getSubimage(0, verticalPoint, image.getWidth(),
					verticalPoint);
		} else {
			int horizontaPoint = image.getWidth() / 2;
			result[0] = image.getSubimage(0, 0, horizontaPoint,
					image.getHeight());
			result[1] = image.getSubimage(horizontaPoint, 0, horizontaPoint,
					image.getHeight());
		}

		return result;
	}

	@Override
	public BufferedImage[] splitImage(BufferedImage image, boolean horizontal,
			int partsNumber) throws ImageManagerException {

		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		if (partsNumber < 2) {
			throw new ImageManagerException(ImageManagerException.MORE_PARTS);
		}

		BufferedImage[] result = new BufferedImage[partsNumber];

		if (horizontal) {
			int verticalPoint = image.getHeight() / partsNumber;
			int verticalPointAux = 0;

			for (int i = 0; i < partsNumber; i++) {
				result[i] = image.getSubimage(0, verticalPointAux,
						image.getWidth(), verticalPoint);
				verticalPointAux += verticalPoint;
			}

		} else {
			int horizontaPoint = image.getWidth() / partsNumber;
			int horizontaPointAux = 0;

			for (int i = 0; i < partsNumber; i++) {
				result[i] = image.getSubimage(horizontaPointAux, 0,
						horizontaPoint, image.getHeight());
				horizontaPointAux += horizontaPoint;
			}

		}

		return result;

	}

	@Override
	public BufferedImage quitBorder(BufferedImage image, int border)
			throws ImageManagerException {
		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		if (border < 1) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_NEGATIVE);
		}

		if (border >= image.getHeight() / 2 || border >= image.getWidth() / 2) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_TOO_WIDE);
		}

		return image.getSubimage(border, border, image.getWidth()
				- (2 * border), image.getHeight() - (2 * border));
	}

	@Override
	public BufferedImage quitBorder(BufferedImage image, int verticalBorder,
			int horizontalBorder) throws ImageManagerException {
		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		if (verticalBorder < 1 || horizontalBorder < 1) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_NEGATIVE);
		}

		if (verticalBorder >= image.getHeight() / 2
				|| horizontalBorder >= image.getWidth() / 2) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_TOO_WIDE);
		}

		return image.getSubimage(horizontalBorder, verticalBorder,
				image.getWidth() - (2 * horizontalBorder), image.getHeight()
						- (2 * verticalBorder));
	}

	@Override
	public BufferedImage quitBorder(BufferedImage image, int topBorder,
			int bottomBorder, int leftBorder, int rightBorder)
			throws ImageManagerException {
		if (image == null) {
			throw new ImageManagerException(ImageManagerException.IMAGE_NULL);
		}

		if (topBorder < 1 || bottomBorder < 1 || leftBorder < 1
				|| rightBorder < 1) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_NEGATIVE);
		}

		if (topBorder >= image.getHeight() / 2
				|| bottomBorder >= image.getHeight() / 2
				|| leftBorder >= image.getWidth() / 2
				|| rightBorder >= image.getWidth() / 2) {
			throw new ImageManagerException(
					ImageManagerException.BORDER_TOO_WIDE);
		}
		
		return image.getSubimage(leftBorder, topBorder,
				image.getWidth() - (leftBorder + rightBorder), image.getHeight()
				- (topBorder + bottomBorder));
	}

}
