package com.jerry.sample.frame.album;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.album.BitmapCache;
import com.jerry.uilib.frame.album.ImageBucket;

import java.util.List;

public class AlbumBucketAdapter extends BaseAdapter {
	
	private final String TAG = getClass().getSimpleName();
	private List<ImageBucket> imageBucketList;
	private BitmapCache bitmapCache;
	private Context mContext;
	
	public AlbumBucketAdapter(Context context, List<ImageBucket> list) {
		mContext = context;
		imageBucketList = list;
		bitmapCache = new BitmapCache();
	}

	@Override
	public int getCount() {
		int count = 0;
		if (imageBucketList != null) {
			count = imageBucketList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		Holder holder;
		if (view == null) {
			holder = new Holder();
			view = View.inflate(mContext, R.layout.activity_album_bucket_item, null);
			holder.image = (ImageView) view.findViewById(R.id.image);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.count = (TextView) view.findViewById(R.id.count);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		ImageBucket item = imageBucketList.get(position);
		holder.count.setText("" + item.count);
		holder.name.setText(item.bucketName);
		if (item.imageList != null && item.imageList.size() > 0) {
			String thumbPath = item.imageList.get(0).thumbnailPath;
			String sourcePath = item.imageList.get(0).imagePath;
			holder.image.setTag(sourcePath);
			bitmapCache.displayBmp(holder.image, thumbPath, sourcePath, callback);
		} else {
			holder.image.setImageBitmap(null);
			Log.e(TAG, "no images in bucket " + item.bucketName);
		}
		return view;
	}

	class Holder {
		private ImageView image;
		private TextView name;
		private TextView count;
	}
	
	BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
				Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					Log.e(TAG, "callback, bmp not match");
				}
			} else {
				Log.e(TAG, "callback, bmp null");
			}
		}
	};
	
}
