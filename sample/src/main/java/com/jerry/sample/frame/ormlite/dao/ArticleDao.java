package com.jerry.sample.frame.ormlite.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.jerry.sample.frame.ormlite.DatabaseHelper;
import com.jerry.sample.frame.ormlite.bean.Article;
import com.jerry.sample.frame.ormlite.bean.User;


/**
 * 1. updateBuilder  deleteBuilder  queryBuilder的使用
 * 　　1). updateBuilder执行更新
 *         articleDaoHelper.updateBuilder().updateColumnValue("name","zzz").where().eq("user_id", 1);
 *         articleDaoHelper.updateRaw(statement, arguments);   //传入sql和参数
 *    2). deleteBuilder执行删除　
 *        类似于updateBuilder,建议直接拼写sql
 *    3). queryBuilder的查询
 *        使用queryBuilder是因为我们希望执行完成查询直接返回List<Bean>集合；
 *    　　1>. 简单的where语句
 *            articleDaoHelper.queryBuilder().where().eq("user_id", userId).query();
 *       2>. where 和 and　结合　相当于　select * from tb_article where user_id = 1 and name = 'xxx' ;
 *           //第一种写法
 *           QueryBuilder<Article, Integer> queryBuilder = articleDaoHelper.queryBuilder();
 *           Where<Article, Integer> where = queryBuilder.where();
 *           where.eq("user_id", 1);
 *           where.and();
 *           where.eq("name", "xxx");
 *
 *           //第二种写法
 *           queryBuilder.where()
 *                       .eq("user_id", 1)
 *                       .and()
 *                       .eq("name", "xxx");
 *
 *          //比较复杂的查询，相当于select * from tb_article where ( user_id = 1 and name = 'xxx' )  or ( user_id = 2 and name = 'yyy' ) ;
 *          where.or(
 *                   where.and(where.eq("user_id", 1), where.eq("name", "xxx")),
 *                   where.and(where.eq("user_id", 2), where.eq("name", "yyy"))
 *          );
 *
 *
 *   2. 事务操作
 *   　　Dao中直接写如下代码
 *   　　TransactionManager.callInTransaction(
 *   　　　　　helper.getConnectionSource(),
 *   　　　　　new Callable<Void>(){
 *   　　　　　　　　@Override
 *   　　　　　　　　public Void call() throws Exception　{
 *   　　　　　　　　　　　return null;
 *   　　　　　　　　}
 *   　　　　　}
 *   　　);
 */





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

	/**
	 * 通过UserId获取所有的文章
	 */
	public List<Article> getArticlesByUserId(int userId) {
		try {
			return articleDaoHelper.queryBuilder().where().eq("user_id", userId).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
