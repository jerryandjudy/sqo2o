package com.jspgou.common.image;

import java.io.File;
import magick.Magick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* This class should preserve.
* @preserve
*/
public class ImageScaleImpl implements ImageScale{
	
	private static final Logger log = LoggerFactory
	         .getLogger(ImageScaleImpl.class);
	private boolean isMagick = false;

	public void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight) throws Exception {
		if (isMagick) {
			MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
		} else {
			AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
		}
	}

	public void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight)
			throws Exception {
		if (isMagick) {
			MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,
					cutTop, cutLeft, cutWidth, catHeight);
		} else {
			AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,
					cutTop, cutLeft, cutWidth, catHeight);
		}
	}

    public void init() {
        try{
            System.setProperty("jmagick.systemclassloader", "no");
            new Magick();
            log.info("use jmagick");
            isMagick = true;
        }catch(Throwable throwable) {
            log.warn("load magick fail, use java image scale. message:{}", throwable.getMessage());
            isMagick = false;
        }
    }
}
