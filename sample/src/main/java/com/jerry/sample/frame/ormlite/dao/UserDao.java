package com.jerry.sample.frame.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.jerry.sample.frame.ormlite.DatabaseHelper;
import com.jerry.sample.frame.ormlite.bean.Article;
import com.jerry.sample.frame.ormlite.bean.User;

public class UserDao {
	private Context context;
	private Dao<User, Integer> userDaoHelper;
	private DatabaseHelper helper;

	public UserDao(Context context) {
		this.context = context;
		try {
			helper = DatabaseHelper.getHelper(context);
			userDaoHelper = helper.getDao(User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(User user) {
		try {
			userDaoHelper.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addAll(final List<User> userList){
		try {
			//事务操作
			TransactionManager.callInTransaction(helper.getConnectionSource(),
					new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							for (User user: userList){
								add(user);
							}
							return null;
						}
					});
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean update(User user){
		Boolean flag = false;
		try {
			flag = userDaoHelper.update(user) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(int id){
		Boolean flag = false;
		try {
			flag = userDaoHelper.deleteById(id) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User get(int id) {
		try {
			return userDaoHelper.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAll() {
		try {
			return userDaoHelper.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
