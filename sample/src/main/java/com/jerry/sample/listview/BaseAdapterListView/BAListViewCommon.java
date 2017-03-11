package com.jerry.sample.listview.BaseAdapterListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BAListViewCommon extends Activity {

    private ListView mListView;
    private List<String> mList = new ArrayList<String>(
            Arrays.asList("aaa", "bbb", "ccc"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_listview);

        mListView = (ListView)findViewById(R.id.list_view);
        mListView.setAdapter(new MyAdapter(this, mList));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(BAListViewCommon.this, "点击事件", Toast.LENGTH_LONG).show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(BAListViewCommon.this, "长按事件", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Context mContext;
        private List<String> mDatas;

        public MyAdapter(Context context, List<String> mDatas) {
            mInflater = LayoutInflater.from(context);
            this.mContext = context;
            this.mDatas = mDatas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.ba_single, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mTextView = (TextView) convertView
                        .findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mDatas.get(position));
            return convertView;
        }

        private final class ViewHolder {
            TextView mTextView;
        }

    }

}
