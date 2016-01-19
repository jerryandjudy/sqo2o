package com.jspgou.common.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.*;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
/**
* This class should preserve.
* @preserve
*/
public class MagickImageScale{
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
	 * @throws MagickException
	 */
	public static void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight) throws IOException, MagickException {
		ImageInfo info = new ImageInfo(srcFile.getAbsolutePath());
		MagickImage image = new MagickImage(info);
		// 计算缩小宽高
		Dimension dim = image.getDimension();
		int width = (int) dim.getWidth();
		int height = (int) dim.getHeight();
		int zoomWidth;
		int zoomHeight;
		if ((float) width / height > (float) boxWidth / boxHeight) {
			zoomWidth = boxWidth;
			zoomHeight = Math.round((float) boxWidth * height / width);
		} else {
			zoomWidth = Math.round((float) boxHeight * width / height);
			zoomHeight = boxHeight;
		}
		// 缩小
		MagickImage scaled = image.scaleImage(zoomWidth, zoomHeight);
		// 输出
		scaled.setFileName(destFile.getAbsolutePath());
		scaled.writeImage(info);
		scaled.destroyImages();
	}

	/**
	 * 裁剪并缩小
	 * 
	 * @param srcFile
	 *            原文件
	 * @param destFile
	 *            目标文件
	 * @param boxWidth
	 *            缩略图最大宽度
	 * @param boxHeight
	 *            缩略图最大高度
	 * @param cutTop
	 *            裁剪TOP
	 * @param cutLeft
	 *            裁剪LEFT
	 * @param cutWidth
	 *            裁剪宽度
	 * @param catHeight
	 *            裁剪高度
	 * @throws IOException
	 * @throws MagickException
	 */
	public static void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight)
			throws IOException, MagickException {
		ImageInfo info = new ImageInfo(srcFile.getAbsolutePath());
		MagickImage image = new MagickImage(info);
		// 剪切
		Rectangle rect = new Rectangle(cutTop, cutLeft, cutWidth, catHeight);
		// 计算压缩宽高
		MagickImage cropped = image.cropImage(rect);
		Dimension dim = cropped.getDimension();
		int width = (int) dim.getWidth();
		int height = (int) dim.getHeight();
		int zoomWidth;
		int zoomHeight;
		if ((float) width / height > (float) boxWidth / boxHeight) {
			zoomWidth = boxWidth;
			zoomHeight = Math.round((float) boxWidth * height / width);
		} else {
			zoomWidth = Math.round((float) boxHeight * width / height);
			zoomHeight = boxHeight;
		}
		// 缩小
		MagickImage scaled = cropped.scaleImage(zoomWidth, zoomHeight);
		// 输出
		scaled.setFileName(destFile.getAbsolutePath());
		scaled.writeImage(info);
		scaled.destroyImages();
	}

    public static void main(String args[])
        throws Exception{
        long time = System.currentTimeMillis();
        resizeFix(new File("test/com/jeecms/common/util/1.bmp"), new File(
        		"test/com/jeecms/common/util/1-n-3.bmp"), 310, 310, 50, 50,
        		320, 320);
        time = System.currentTimeMillis() - time;
        System.out.println("resize new img in " + time + "ms");
    }
}
