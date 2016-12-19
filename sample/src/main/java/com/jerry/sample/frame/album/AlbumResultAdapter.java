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
import com.jerry.uilib.frame.album.ImageItem;

import java.util.List;

public class AlbumResultAdapter extends BaseAdapter {
	
	private final String TAG = getClass().getSimpleName();
	private List<ImageItem> imageItemList;
	private Context mContext;
	private BitmapCache bitmapCache;

	public AlbumResultAdapter(Context context, List<ImageItem> list) {
		mContext = context;
		imageItemList = list;
		bitmapCache = new BitmapCache();
	}

	@Override
	public int getCount() {
		int count = 0;
		if (imageItemList != null) {
			count = imageItemList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = View.inflate(mContext, R.layout.activity_album_grid_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.selected = (ImageView) convertView.findViewById(R.id.isselected);
			holder.text = (TextView) convertView.findViewById(R.id.item_image_grid_text);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		final ImageItem item = imageItemList.get(position);
		holder.image.setTag(item.imagePath);
		bitmapCache.displayBmp(holder.image, item.thumbnailPath, item.imagePath, callback);
		
		return convertView;
	}
	
	class Holder {
		private ImageView image;
		private ImageView selected;
		private TextView text;
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
