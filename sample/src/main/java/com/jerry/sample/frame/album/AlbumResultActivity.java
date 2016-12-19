package com.jerry.sample.frame.album;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.album.ImageItem;

import java.util.List;

public class AlbumResultActivity extends FragmentActivity {

	private List<ImageItem> mImageItemList;
	private GridView mGridview;
	private AlbumResultAdapter mGridAdapter;
	public static String POSITION = "position";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_result);

		mImageItemList = (List<ImageItem>) getIntent().getSerializableExtra(AlbumBucketActivity.IMAGE_LIST);
		
		initWidget();
		initData();
	}

	private void initWidget() {
		mGridview = (GridView) findViewById(R.id.grid_view);
	}

	private void initData() {
		mGridAdapter = new AlbumResultAdapter(this, mImageItemList);
		mGridview.setAdapter(mGridAdapter);
		mGridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long arg3) {
				
			}
		});
	}
	
//	private void startShowActivity(int position) {
//		Intent intent = new Intent(this,
//				ImageShowActivity.class);
//		intent.putExtra(POSITION, position);
//		intent.putExtra(AlbumBucketActivity.IMAGE_LIST,(Serializable)mImageItemList);
//		startActivity(intent);
//	}
}
