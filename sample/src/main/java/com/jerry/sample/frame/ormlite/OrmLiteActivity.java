package com.jerry.sample.frame.ormlite;

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
import com.jerry.sample.frame.litepal.Book;
import com.jerry.sample.frame.ormlite.manager.UserManager;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class OrmLiteActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = OrmLiteActivity.this;
        mListData = new ArrayList<ListInfoBean>();

        initView();
        initData();

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
                if("userInsert".equals(operate)){
                    UserManager.getInstance().insertUser();

                } else if("userInsertAll".equals(operate)){
                    UserManager.getInstance().insertUserAll();

                } else if("userUpdate".equals(operate)){
                    UserManager.getInstance().updateUser();

                } else  if("userDelete".equals(operate)){
                    UserManager.getInstance().deleteUser();

                } else  if("userGet".equals(operate)){
                    UserManager.getInstance().getUser();

                } else  if("userGetAll".equals(operate)){
                    UserManager.getInstance().getUserList();

                }
            }
        });
    }

    private void initData(){

        ListInfoBean userInsert = new ListInfoBean();
        userInsert.setTitle("用户增加");
        userInsert.setOperate("userInsert");
        mListData.add(userInsert);

        ListInfoBean userInsertAll = new ListInfoBean();
        userInsertAll.setTitle("增加多个用户");
        userInsertAll.setOperate("userInsertAll");
        mListData.add(userInsertAll);

        ListInfoBean userUpdate = new ListInfoBean();
        userUpdate.setTitle("用户修改");
        userUpdate.setOperate("userUpdate");
        mListData.add(userUpdate);

        ListInfoBean userDelete = new ListInfoBean();
        userDelete.setTitle("用户删除");
        userDelete.setOperate("userDelete");
        mListData.add(userDelete);

        ListInfoBean userGet = new ListInfoBean();
        userGet.setTitle("用户查找");
        userGet.setOperate("userGet");
        mListData.add(userGet);

        ListInfoBean userGetAll = new ListInfoBean();
        userGetAll.setTitle("查找所有用户");
        userGetAll.setOperate("userGetAll");
        mListData.add(userGetAll);

    }

}
