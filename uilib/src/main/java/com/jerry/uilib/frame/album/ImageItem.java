package com.jerry.uilib.frame.album;

import java.io.Serializable;

/**
 * 图片对象
 */
public class ImageItem implements Serializable {
	public String imageId;
	public String thumbnailPath;
	public String imagePath;
	public boolean isSelected = false;
}
