package com.jerry.sample.frame.litepal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * GitHub地址：https://github.com/LitePalFramework/LitePal
 */
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
                    //查询方法汇总
                    //1. DataSupport.findAll(Book.class);　　　查询所有记录　
                    //2. DataSupport.findFirst(Book.class);    查询第一条记录
                    //3. DataSupport.findLast(Book.class);　　　查询最后一条记录
                    //4. select("name")　　　 指定查询哪几列数据
                    //5. where("pages > ?","23")　   指定查询条件
                    //6. order()　指定结果的排列方式
                    //7. limit()  指定查询结果的数量
                    //8. offest()  指定查询结果的偏移量
                    //9.　以上五项可以做任意组合
                    //10. DataSupport.findBySQL("原生sql语句")　　　原生sql语句查询，返回Cursor

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

        ListInfoBean insert = new ListInfoBean();
        insert.setTitle("增加");
        insert.setOperate("insert");
        mListData.add(insert);

        ListInfoBean update = new ListInfoBean();
        update.setTitle("修改");
        update.setOperate("update");
        mListData.add(update);

        ListInfoBean delete = new ListInfoBean();
        delete.setTitle("删除");
        delete.setOperate("delete");
        mListData.add(delete);

        ListInfoBean search = new ListInfoBean();
        search.setTitle("查找");
        search.setOperate("search");
        mListData.add(search);

    }

}
