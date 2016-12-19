package com.jerry.sample.frame.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.album.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlbumGridActivity extends FragmentActivity {
	
	private List<ImageItem> mImageItemList;
	private GridView mGridView;
	private AlbumGridAdapter imageGridAdapter;
	private Button submit;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_grid);

		mImageItemList = (List<ImageItem>) getIntent().getSerializableExtra(AlbumBucketActivity.IMAGE_LIST);

		initView();
		initData();
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridview);
		submit = (Button) findViewById(R.id.submit);
	}

	private void initData() {
		imageGridAdapter = new AlbumGridAdapter(AlbumGridActivity.this, mImageItemList);
		mGridView.setAdapter(imageGridAdapter);
		imageGridAdapter.setTextCallback(new AlbumGridAdapter.TextCallback() {
			public void onListener(int count) {
				submit.setText("完成" + "(" + count + ")");
			}
		});

		submit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				List<ImageItem> selectImageList = new ArrayList<ImageItem>();
				
				Iterator it = imageGridAdapter.selectMap.keySet().iterator();
				while (it.hasNext()){
					Object key = it.next();
					Object value = imageGridAdapter.selectMap.get(key);
					selectImageList.add((ImageItem)value);
				}
				
				startResultActivity(selectImageList);
			}
		});
		
	}
	
	private void startResultActivity(List<ImageItem> imageList) {
		Intent intent = new Intent(this, AlbumResultActivity.class);
		intent.putExtra(AlbumBucketActivity.IMAGE_LIST,(Serializable)imageList);
		startActivity(intent);
	}
	
}
