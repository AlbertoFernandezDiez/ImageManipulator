package com.alberto.manipulator;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.alberto.exceptions.ImageManagerException;
import com.alberto.interfaces.ImageManipulable;

public class ImageManiulatorTest {

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();
	
	private static ImageManipulable manipulator;
	private static File baseImage;
	private static final String BASE_IMAGE_NAME = "triumph.jpg";
	
	private static BufferedImage openedImage = null;
	private static BufferedImage modifiedImage = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		manipulator = ImageManipulator.getMyManipulator();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		manipulator = ImageManipulator.getMyManipulator();
	}

	@Before
	public void setUp() throws Exception {		
		baseImage = new File(getClass().getClassLoader().getResource(BASE_IMAGE_NAME).getFile());
		Assert.assertTrue(baseImage.exists());
		try {
			openedImage = manipulator.openImageFromFile(baseImage);
		} catch (ImageManagerException e) {
			fail("Exception should not be lanunched");
		}
		modifiedImage = null;
	}

	@After
	public void tearDown() throws Exception {
		
		try {
			manipulator.saveImage(modifiedImage,new File(baseImage.getParentFile(), Long.toString(System.currentTimeMillis()) + ".jpg"));
		} catch (ImageManagerException e) {
			fail("Exception should not be lanunched");
		} 
		baseImage = null;
		openedImage = null;
		modifiedImage = null;
	}

	@Test
	public void testRotateImage() {
			
		int oldHeight = openedImage.getHeight();
		int oldWidth = openedImage.getWidth();
		
		try {
			modifiedImage = manipulator.rotateImage(openedImage, ImageManipulator.ROTATE_RIGHT);
		} catch (ImageManagerException e) {
			fail("Exception should not be lanunched");
		}
		
		Assert.assertEquals(oldHeight, modifiedImage.getWidth());
		Assert.assertEquals(oldWidth, modifiedImage.getHeight());
	}

	@Test
	public void testSplitImageBufferedImageBoolean() {
		int oldHeight = openedImage.getHeight();
		int oldWidth = openedImage.getWidth();
		BufferedImage[] modifiedImages = null;
		try {
			modifiedImages = manipulator.splitImage(openedImage, false);
		} catch (ImageManagerException e) {
			fail("Exception should not be lanunched");
		}
		
		Assert.assertEquals("There should be 2 splitted images" , 2, modifiedImages.length);
		
		modifiedImage = modifiedImages[0];
		
		Assert.assertEquals(oldHeight, modifiedImage.getHeight());
		Assert.assertTrue("The old image should be wider than the modified one",oldWidth > modifiedImage.getWidth());
	}

	@Test
	public void testQuitBorderBufferedImageInt() {
		int oldHeight = openedImage.getHeight();
		int oldWidth = openedImage.getWidth();
		
		try {
			modifiedImage = manipulator.quitBorder(openedImage, 100);
		} catch (ImageManagerException e) {
			fail("Exception should not be lanunched");
		}
		
		Assert.assertTrue(oldHeight > modifiedImage.getHeight());
		Assert.assertTrue(oldWidth > modifiedImage.getWidth());
	}

}
