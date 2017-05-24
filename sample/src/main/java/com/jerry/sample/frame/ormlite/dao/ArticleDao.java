package com.jerry.sample.frame.ormlite.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.jerry.sample.frame.ormlite.DatabaseHelper;
import com.jerry.sample.frame.ormlite.bean.Article;
import com.jerry.sample.frame.ormlite.bean.User;

public class ArticleDao {

	private Dao<Article, Integer> articleDaoHelper;
	private DatabaseHelper helper;

	@SuppressWarnings("unchecked")
	public ArticleDao(Context context) {
		try {
			helper = DatabaseHelper.getHelper(context);
			articleDaoHelper = helper.getDao(Article.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(Article article) {
		try {
			articleDaoHelper.create(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addAll(final List<Article> articleList){
		try {
			//事务操作
			TransactionManager.callInTransaction(helper.getConnectionSource(),
					new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							for (Article article: articleList){
								add(article);
							}
							return null;
						}
					});
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean update(Article article){
		Boolean flag = false;
		try {
			flag = articleDaoHelper.update(article) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(int id){
		Boolean flag = false;
		try {
			flag = articleDaoHelper.deleteById(id) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Article get(int id) {
		try {
			Article article = articleDaoHelper.queryForId(id);
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Article> getAll() {
		try {
			return articleDaoHelper.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * 通过UserId获取所有的文章
//	 *
//	 * @param userId
//	 * @return
//	 */
//	public List<Article> listByUserId(int userId) {
//		try {
//			/*QueryBuilder<Article, Integer> articleBuilder = articleDaoOpe
//					.queryBuilder();
//			QueryBuilder userBuilder = helper.getDao(User.class).queryBuilder();
//			articleBuilder.join(userBuilder);
//
//
//			Where<Article, Integer> where = queryBuilder.where();
//			where.eq("user_id", 1);
//			where.and();
//			where.eq("name", "xxx");
//
//			// 或者
//			articleDaoOpe.queryBuilder().//
//					where().//
//					eq("user_id", 1).and().//
//					eq("name", "xxx");
//			//
//			articleDaoOpe.updateBuilder().updateColumnValue("name","zzz").where().eq("user_id", 1);
//			where.or(
//					//
//					where.and(//
//							where.eq("user_id", 1), where.eq("name", "xxx")),
//					where.and(//
//							where.eq("user_id", 2), where.eq("name", "yyy")));*/
//
//			return articleDaoHelper.queryBuilder().where().eq("user_id", userId)
//					.query();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
