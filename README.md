# ImageManipulator
A java library to easily manipulate images

##Adds easily to your projects the posiblity to
  * Open a image file
  * Save a image file
  * Quit the borders
  * Convert the image to GrayScale
  * Split the images in as many parts as you want
  
##Use
1. Firstly open the image giving the path in a File object
```java
ImageManipulator.getMyImageManipulator().openImageFromFile(fileOpen);
```
2. Make any change you want to the returned BufferedImage with the funcions

FunctionName  | Second Header
------------- | -------------
rotateImage  | This method rotates the given <strong>image</strong>
toGrayScale  | Converts the given image to grayscale
splitImage  | This method splits the given image in parts and return the parts of the image in a <code>Array<code>.
quitBorder  | This methods quit the given border length to the image

3. Save the Buffered image to a File

```java
ImageManipulator.getMyImageManipulator().saveImage(image, filesave);
```
