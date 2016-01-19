package com.jspgou.common.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
 * 图片缩小类。使用方型区域颜色平均算法
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
public class AverageImageScale {
	/**
	 * 缩小图片
	 * 
	 * @param srcFile
	 *            原图片
	 * @param destFile
	 *            目标图片
	 * @param boxWidth
	 *            缩略图最大宽度
	 * @param boxHeight
	 *            缩略图最大高度
	 * @throws IOException
	 */
	public static void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight) throws IOException {
		BufferedImage srcImgBuff = ImageIO.read(srcFile);
		int width = srcImgBuff.getWidth();
		int height = srcImgBuff.getHeight();
		if (width <= boxWidth && height <= boxHeight) {
			FileUtils.copyFile(srcFile, destFile);
			return;
		}
		int zoomWidth;
		int zoomHeight;
		if ((float) width / height > (float) boxWidth / boxHeight) {
			zoomWidth = boxWidth;
			zoomHeight = Math.round((float) boxWidth * height / width);
		} else {
			zoomWidth = Math.round((float) boxHeight * width / height);
			zoomHeight = boxHeight;
		}
		BufferedImage imgBuff = scaleImage(srcImgBuff, width, height,
				zoomWidth, zoomHeight);
		writeFile(imgBuff, destFile);
	}

    public static void resizeFix(File file, File file1, int i, int j, int k, int l, int i1, int j1)
        throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        bufferedimage = bufferedimage.getSubimage(k, l, i1, j1);
        int k1 = bufferedimage.getWidth();
        int l1 = bufferedimage.getHeight();
        if(k1 <= i && l1 <= j)
        {
            writeFile(bufferedimage, file1);
            return;
        }
        int i2;
        int j2;
        if((float)k1 / (float)l1 > (float)i / (float)j)
        {
            i2 = i;
            j2 = Math.round(((float)i * (float)l1) / (float)k1);
        } else
        {
            i2 = Math.round(((float)j * (float)k1) / (float)l1);
            j2 = j;
        }
        BufferedImage bufferedimage1 = scaleImage(bufferedimage, k1, l1, i2, j2);
        writeFile(bufferedimage1, file1);
    }
    
	public static void writeFile(BufferedImage imgBuf, File destFile)
	        throws IOException {
           File parent = destFile.getParentFile();
           if (!parent.exists()) {
	              parent.mkdirs();
           }
         ImageIO.write(imgBuf, "jpeg", destFile);
       }

	private static BufferedImage scaleImage(BufferedImage srcImgBuff,
			int width, int height, int zoomWidth, int zoomHeight) {
		int[] colorArray = srcImgBuff.getRGB(0, 0, width, height, null, 0,
				width);
		BufferedImage outBuff = new BufferedImage(zoomWidth, zoomHeight,
				BufferedImage.TYPE_INT_RGB);
		// 宽缩小的倍数
		float wScale = (float) width / zoomWidth;
		int wScaleInt = (int) (wScale + 0.5);
		// 高缩小的倍数
		float hScale = (float) height / zoomHeight;
		int hScaleInt = (int) (hScale + 0.5);
		int area = wScaleInt * hScaleInt;
		int x0, x1, y0, y1;
		int color;
		long red, green, blue;
		int x, y, i, j;
		for (y = 0; y < zoomHeight; y++) {
			// 得到原图高的Y坐标
			y0 = (int) (y * hScale);
			y1 = y0 + hScaleInt;
			for (x = 0; x < zoomWidth; x++) {
				x0 = (int) (x * wScale);
				x1 = x0 + wScaleInt;
				red = green = blue = 0;
				for (i = x0; i < x1; i++) {
					for (j = y0; j < y1; j++) {
						color = colorArray[width * j + i];
						red += getRedValue(color);
						green += getGreenValue(color);
						blue += getBlueValue(color);
					}
				}
				outBuff.setRGB(x, y, comRGB((int) (red / area),
						(int) (green / area), (int) (blue / area)));
			}
		}
		return outBuff;
	}

	private static int getRedValue(int rgbValue) {
		return (rgbValue & 0x00ff0000) >> 16;
	}

	private static int getGreenValue(int rgbValue) {
		return (rgbValue & 0x0000ff00) >> 8;
	}

	private static int getBlueValue(int rgbValue) {
		return rgbValue & 0x000000ff;
	}

	private static int comRGB(int redValue, int greenValue, int blueValue) {
		return (redValue << 16) + (greenValue << 8) + blueValue;
	}

    public static void main(String args[])
        throws Exception{
        long time = System.currentTimeMillis();
        resizeFix(new File("test/com/jeecms/common/util/1.bmp"), new File(
        		"test/com/jeecms/common/util/1-n-2.bmp"), 310, 310, 50, 50,
        		320, 320);
        time = System.currentTimeMillis() - time;
        System.out.println("resize2 img in " + time + "ms");
    }
}
