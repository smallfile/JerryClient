package com.jerry.uilib.frame.album;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 相册帮助类
 */
public class AlbumHelper {
	
	// 缩略图列表
	private HashMap<String, String> mThumbnailList = new HashMap<String, String>();
	// 专辑列表
	private List<HashMap<String, String>> mAlbumList = new ArrayList<HashMap<String, String>>();
	private HashMap<String, ImageBucket> mBucketList = new HashMap<String, ImageBucket>();
	private Context mContext;
	private ContentResolver mContentResolver;
	boolean hasBuildImagesBucketList = false;
		
	private static AlbumHelper instance;

	private AlbumHelper() {
	}

	public static AlbumHelper getInstance() {
		if (instance == null) {
			instance = new AlbumHelper();
		}
		return instance;
	}

	/**
	 * 初始化
	 */
	public void init(Context context) {
		if (this.mContext == null) {
			this.mContext = context;
			mContentResolver = context.getContentResolver();
		}
	}

	/**
	 * 得到缩略图
	 */
	private void getThumbnail() {
		String[] projection = { Thumbnails._ID, Thumbnails.IMAGE_ID, Thumbnails.DATA };
		Cursor cursor = mContentResolver.query(Thumbnails.EXTERNAL_CONTENT_URI, projection,
				null, null, null);
		buildThumbnail(cursor);
	}

	/**
	 * 从数据库中得到缩略图
	 */
	private void buildThumbnail(Cursor cursor) {
		if (cursor.moveToFirst()) {
			do {
				// Get the field values
				int _id = cursor.getInt(cursor.getColumnIndex(Thumbnails._ID));
				int image_id = cursor.getInt(cursor.getColumnIndex(Thumbnails.IMAGE_ID));
				String image_path = cursor.getString(cursor.getColumnIndex(Thumbnails.DATA));
				mThumbnailList.put(String.valueOf(image_id), image_path);
			} while (cursor.moveToNext());
		}
	}

	/**
	 * 得到相册
	 */
	void getAlbum() {
		String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
						Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
						Media.SIZE, Media.BUCKET_DISPLAY_NAME };
		Cursor cursor = mContentResolver.query(Media.EXTERNAL_CONTENT_URI, columns, null, null,
						null);
		buildAlbum(cursor);

	}

	/**
	 * 从本地数据库中得到原图
	 */
	private void buildAlbum(Cursor cursor) {
		if (cursor.moveToFirst()) {
			// 获取图片总数
			int totalNum = cursor.getCount();

			do {
				String _id = cursor.getString(cursor.getColumnIndexOrThrow(Media._ID));
				String name = cursor.getString(cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME));
				String path = cursor.getString(cursor.getColumnIndexOrThrow(Media.DATA));
				String title = cursor.getString(cursor.getColumnIndexOrThrow(Media.TITLE));
				String size = cursor.getString(cursor.getColumnIndexOrThrow(Media.SIZE));
				String bucketName = cursor.getString(cursor.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME));
				String bucketId = cursor.getString(cursor.getColumnIndexOrThrow(Media.BUCKET_ID));
				String picasaId = cursor.getString(cursor.getColumnIndexOrThrow(Media.PICASA_ID));

				ImageBucket bucket = mBucketList.get(bucketId);
				if (bucket == null) {
					bucket = new ImageBucket();
					mBucketList.put(bucketId, bucket);
					bucket.imageList = new ArrayList<ImageItem>();
					bucket.bucketName = bucketName;
				}
				bucket.count++;
				ImageItem imageItem = new ImageItem();
				imageItem.imageId = _id;
				imageItem.imagePath = path;
				imageItem.thumbnailPath = mThumbnailList.get(_id);
				bucket.imageList.add(imageItem);

			} while (cursor.moveToNext());
		}
	}


	/**
	 * 得到图片集
	 */
	public List<ImageBucket> getImagesBucketList(boolean refresh) {
		if (refresh || (!refresh && !hasBuildImagesBucketList)) {
			// 构造缩略图
			getThumbnail();
			// 构造相册
			getAlbum();
			hasBuildImagesBucketList = true;
		}
		List<ImageBucket> imageBucketList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> iterator = mBucketList.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, ImageBucket> entry = (Entry<String, ImageBucket>) iterator.next();
			imageBucketList.add(entry.getValue());
		}
		return imageBucketList;
	}

	/**
	 * 得到原始图像路径
	 */
	String getOriginalImagePath(String image_id) {
		String path = null;
		String[] projection = { Media._ID, Media.DATA };
		Cursor cursor = mContentResolver.query(Media.EXTERNAL_CONTENT_URI, projection,
				Media._ID + "=" + image_id, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			path = cursor.getString(cursor.getColumnIndex(Media.DATA));

		}
		return path;
	}

}
