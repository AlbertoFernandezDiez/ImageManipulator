package com.alberto.exceptions;

public class ImageManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9083170176280905266L;
	
	public static final String IMAGE_NULL = "The image can´t be null";
	public static final String IMAGE_READ_ERROR = "The image can't be read";
	public static final String BORDER_TOO_WIDE = "The border must be less than the half of image height and width";
	public static final String BORDER_NEGATIVE = "The border must be more then 0";

	public static final String MORE_PARTS = "The image must be splited in at least two parts";

	public ImageManagerException(String msg) {
		super(msg);
	}

}
