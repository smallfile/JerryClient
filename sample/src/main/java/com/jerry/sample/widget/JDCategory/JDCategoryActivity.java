package com.jerry.sample.widget.JDCategory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jerry.sample.R;

/**
 * 
 * @author qdwang
 *
 */
public class JDCategoryActivity extends FragmentActivity implements OnItemClickListener {

	private String[] strs = { "常用分类", "服饰内衣", "鞋靴", "手机", "家用电器", "数码", "电脑办公", "个护化妆", "图书" };
	private ListView listView;
	private JDCategoryAdapter adapter;
	private JDCategoryFragment myFragment;
	public static int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jd_category);

		initView();
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		listView = (ListView) findViewById(R.id.listview);

		adapter = new JDCategoryAdapter(this, strs);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		loadFragment(mPosition);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//拿到当前位置
		mPosition = position;
		//即使刷新adapter
		adapter.notifyDataSetChanged();
		for (int i = 0; i < strs.length; i++) {
			loadFragment(mPosition);
		}
		
	}
	
	private void loadFragment(int position){
		//创建MyFragment对象
		myFragment = new JDCategoryFragment();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, myFragment);
		Bundle bundle = new Bundle();
		bundle.putString(JDCategoryFragment.TAG, strs[position]);
		myFragment.setArguments(bundle);
		fragmentTransaction.commit();
	}
}
