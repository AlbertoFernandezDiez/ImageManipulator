package com.alberto.interfaces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import com.alberto.exceptions.ImageManagerException;

/**
 * This interface enables the main image manipulation capabilities
 * 
 * @author Alberto
 * 
 */
public interface ImageManipulable {

	public static final short ROTATE_LEFT = 0;
	public static final short ROTATE_RIGHT = 1;
	public static final short INVERSE = 2;

	public static final double[] ANGLES = { 3 * (Math.PI / 2), (Math.PI / 2),
			Math.PI };

	/**
	 * This method rotates the given <strong>image</strong>
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to rotate
	 * @param degree
	 *            <code>int</code> the rotation movement the image will do, use
	 *            the constants <code>ROTATE_LEFT</code>,
	 *            <code>ROTATE_RIGHT</code> and <code>INVERSE</code>
	 * @return <code>BufferedImage</code> the rotated image
	 */
	public BufferedImage rotateImage(BufferedImage image, short rotation)
			throws ImageManagerException;

	/**
	 * This methods gets the image from the given <code>File</code>
	 * 
	 * @param imageFile
	 *            <code>File</code> the image file
	 * @return <code>BufferedImage</code> the opened image
	 */
	public BufferedImage openImageFromFile(File imageFile)
			throws ImageManagerException;

	/**
	 * This method saves from memory to disk the modified image
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to save
	 * @param saveDestiny
	 *            <code>File</code> the save destiny
	 * @return <ul>
	 *         <li><code>true</code> the image was saved</li>
	 *         <li><code>false</code> the image <strong>was</strong> not saved</li>
	 *         </ul>
	 */
	public boolean saveImage(BufferedImage image, File saveDestiny)
			throws ImageManagerException;

	/**
	 * Converts the given image to grayscale
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to convert to grayscale
	 * @return <ul>
	 *         <li><code>true</code> the image was converted</li>
	 *         <li><code>false</code> the image <strong>was</strong> not
	 *         converted</li>
	 *         </ul>
	 */
	public boolean toGrayScale(BufferedImage image)
			throws ImageManagerException;

	/**
	 * This method splits the given image in parts and return the parts of the
	 * image in a
	 * <code>Array<code>. The halfs will be made <strong>left to right</strong> and <strong>up to down</strong>
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to split
	 * @param horizontal
	 *            <code>boolean</code>
	 *            <ul>
	 *            <li><code>true</code> the image will be splitted horizontally</li>
	 *            <li><code>false</code> the image will be splitted vertically</li>
	 *            </ul>
	 * @param partsNumber
	 *            <code>int</code> the number of parts the image will be
	 *            splitted, it must be <strong>more than 1</strong>
	 * @return <code>BufferedImage[]</code> the first position contains the
	 *         first half of the image, the second position the last half
	 */
	public BufferedImage[] splitImage(BufferedImage image, boolean horizontal,
			int partsNumber) throws ImageManagerException;

	/**
	 * This method splits by the half the given image and return the two halfs
	 * of the image in a
	 * <code>Array<code>. The halfs will be made <strong>left to right</strong> and <strong>up to down</strong>
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to split
	 * @param horizontal
	 *            <code>boolean</code>
	 *            <ul>
	 *            <li><code>true</code> the image will be splitted horizontally</li>
	 *            <li><code>false</code> the image will be splitted vertically</li>
	 *            </ul>
	 * @return <code>BufferedImage[]</code> the first position contains the
	 *         first half of the image, the second position the last half
	 */
	public BufferedImage[] splitImage(BufferedImage image, boolean horizontal)
			throws ImageManagerException;

	/**
	 * This methods quit the given border length to the image
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to quit border
	 * @param border
	 *            <code>int</code> the border to quit in pixels, must
	 *            <strong>more than 0</strong> and <strong>less than the
	 *            half</strong> of the picture in width or height
	 * @return
	 */
	public BufferedImage quitBorder(BufferedImage image, int border)
			throws ImageManagerException;

	/**
	 * This methods quit the given border length to the image top and bottom and
	 * to the left and right
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to quit border
	 * @param verticalBorder
	 *            <code>int</code> the border to quit from top and bottom in
	 *            pixels, must <strong>more than 0</strong> and <strong>less
	 *            than the half</strong> of the picture in height
	 * @param horizontalBorder
	 *            <code>int</code> the border to quit from left and right in
	 *            pixels, must <strong>more than 0</strong> and <strong>less
	 *            than the half</strong> of the picture in width
	 * @return
	 * @throws ImageManagerException
	 */
	public BufferedImage quitBorder(BufferedImage image, int verticalBorder,
			int horizontalBorder) throws ImageManagerException;

	/**
	 * This methods quit the given border length to the image top and bottom and
	 * to the left and right
	 * 
	 * @param image
	 *            <code>BufferedImage</code> the image to quit border
	 * @param topBorder
	 *            <code>int</code> the border to quit from top in pixels, must
	 *            <strong>more than 0</strong> and <strong>less than the
	 *            half</strong> of the picture in height
	 * @param bottomBorder
	 *            <code>int</code> the border to quit from bottom in pixels,
	 *            must <strong>more than 0</strong> and <strong>less than the
	 *            half</strong> of the picture in height
	 * @param leftBorder
	 *            <code>int</code> the border to quit from left in pixels, must
	 *            <strong>more than 0</strong> and <strong>less than the
	 *            half</strong> of the picture in width
	 * @param rightBorder
	 *            <code>int</code> the border to quit from right in pixels, must
	 *            <strong>more than 0</strong> and <strong>less than the
	 *            half</strong> of the picture in width
	 * @return
	 * @throws ImageManagerException
	 */
	public BufferedImage quitBorder(BufferedImage image, int topBorder,
			int bottomBorder, int leftBorder, int rightBorder)
			throws ImageManagerException;
}
