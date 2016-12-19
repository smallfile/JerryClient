package com.jerry.sample.frame.album;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.frame.album.BitmapCache;
import com.jerry.uilib.frame.album.ImageItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumGridAdapter extends BaseAdapter {

	private final String TAG = getClass().getSimpleName();
	private List<ImageItem> imageItemList;
	private Context mContext;
	public Map<String, ImageItem> selectMap = new HashMap<String, ImageItem>();
	private BitmapCache bitmapCache;
	private int selectCount = 0;
	private int maxCount = 9;
	private TextCallback textCallback = null;
	
	public AlbumGridAdapter(Context context, List<ImageItem> list) {
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
		
		if (item.isSelected) {
			selectedImage(holder);
		} else {
			disSelectImage(holder);
		}
		
		holder.image.setOnClickListener(clickImageListener(item, holder));

		return convertView;
	}
	
	private void selectedImage(Holder holder) {
		holder.selected.setBackgroundResource(R.mipmap.album_grid_selected);
		holder.text.setBackgroundResource(R.drawable.album_grid_selected_background);
	}
	
	private void disSelectImage(Holder holder) {
		holder.selected.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
		holder.text.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
	}

	private OnClickListener clickImageListener(final ImageItem item, final Holder holder) {
		OnClickListener clickImageListener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (item.isSelected) {
					item.isSelected = false;
					disSelectImage(holder);
					selectCount--;
					if (textCallback != null)
						textCallback.onListener(selectCount);
					selectMap.remove(item.imageId);
				} else {
					if (selectCount < maxCount) {
						item.isSelected = true;
						selectedImage(holder);
						selectCount++;
						if (textCallback != null)
							textCallback.onListener(selectCount);
						selectMap.put(item.imageId, item);
					} else {
						Toast.makeText(mContext, "最多选择"+maxCount+"张图片", Toast.LENGTH_LONG).show();
					}
				}
			}
		};
		return clickImageListener;
	}
	
	
	class Holder {
		private ImageView image;
		private ImageView selected;
		private TextView text;
	}
	
	public static interface TextCallback {
		public void onListener(int count);
	}

	public void setTextCallback(TextCallback listener) {
		textCallback = listener;
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
