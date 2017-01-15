package com.jerry.sample.frame.litepal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.frame.CrashActivity;
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.tab.TabFragmentActivity;
import com.jerry.sample.frame.xutils.XUtilsActivity;
import com.jerry.sample.utils.MyActivityManager;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class LitePalActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = LitePalActivity.this;
        mListData = new ArrayList<ListInfoBean>();

        initView();
        initData();

        //创建数据库
        Connector.getDatabase();

        mListInfoAdapter = new ListInfoAdapter(mContext);
        mListInfoAdapter.setListData(mListData);
        mListView.setAdapter(mListInfoAdapter);
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                ListInfoBean infoBean = mListData.get(position);
                String operate = infoBean.getOperate();
                if("insert".equals(operate)){
                    Book book = new Book();
                    book.setName("假书");
                    book.setAuthor("Jerry");
                    book.setPages(454);
                    book.setPrice(20);
                    book.save();

                } else  if("update".equals(operate)){
                    Book book = new Book();
                    book.setPrice(13);
                    book.updateAll("name = ? and author = ?", "假书", "Jerry");

                } else  if("delete".equals(operate)){
                    DataSupport.deleteAll(Book.class, "price < ?", "15");

                } else  if("search".equals(operate)){
                    List<Book> books = DataSupport.findAll(Book.class);
                    for (Book book: books) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("book name is " + book.getName());
                        sb.append("     ");
                        sb.append("book author is " + book.getAuthor());
                        sb.append("     ");
                        sb.append("book pages is " + book.getPages());
                        sb.append("     ");
                        sb.append("book price is " + book.getPrice());

                        Toast.makeText(mContext,sb.toString(),Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    private void initData(){

        ListInfoBean xutils = new ListInfoBean();
        xutils.setTitle("增加");
        xutils.setOperate("insert");
        mListData.add(xutils);

        ListInfoBean tab = new ListInfoBean();
        tab.setTitle("修改");
        tab.setOperate("update");
        mListData.add(tab);

        ListInfoBean album = new ListInfoBean();
        album.setTitle("删除");
        album.setOperate("delete");
        mListData.add(album);

        ListInfoBean crash = new ListInfoBean();
        crash.setTitle("查找");
        crash.setOperate("search");
        mListData.add(crash);

    }

}
