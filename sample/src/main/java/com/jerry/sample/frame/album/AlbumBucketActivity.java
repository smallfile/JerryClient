package com.jerry.sample.frame.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.album.AlbumHelper;
import com.jerry.uilib.frame.album.ImageBucket;
import com.jerry.uilib.frame.album.ImageItem;

import java.io.Serializable;
import java.util.List;

public class AlbumBucketActivity extends FragmentActivity {
	
	private List<ImageBucket> mImageBucketList;
	private GridView mGridView;
	private AlbumBucketAdapter mImageBucketDapter;
	public static final String IMAGE_LIST = "image_list";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_bucket);

		AlbumHelper.getInstance().init(getApplicationContext());
		initView();
		initData();
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridview);
	}

	private void initData() {
		mImageBucketList = AlbumHelper.getInstance().getImagesBucketList(false);
		mImageBucketDapter = new AlbumBucketAdapter(AlbumBucketActivity.this, mImageBucketList);
		mGridView.setAdapter(mImageBucketDapter);

		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				List<ImageItem> imageItemList = mImageBucketList.get(position).imageList;
				startGridActivity(imageItemList);
			}
		});
	}
	
	private void startGridActivity(List<ImageItem> imageItemList) {
		Intent intent = new Intent(this,AlbumGridActivity.class);
		intent.putExtra(AlbumBucketActivity.IMAGE_LIST,(Serializable)imageItemList);
		startActivity(intent);
	}
}
