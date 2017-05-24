package com.jerry.sample.frame.ormlite.manager;

import com.jerry.sample.MyApplication;
import com.jerry.sample.frame.ormlite.bean.Article;
import com.jerry.sample.frame.ormlite.bean.User;
import com.jerry.sample.frame.ormlite.dao.ArticleDao;
import com.jerry.sample.frame.ormlite.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2017/5/18.
 */

public class ArticleManager {

    static ArticleManager instance;
    private ArticleDao articleDao;
    private UserDao userDao;

    public ArticleManager() {
        articleDao = new ArticleDao(MyApplication.getApplication());
        userDao = new UserDao(MyApplication.getApplication());
    }

    public static ArticleManager getInstance() {
        if (instance == null) {
            synchronized (ArticleManager.class) {
                ArticleManager inst = instance;
                if (inst == null) {
                    synchronized (ArticleManager.class) {
                        instance = new ArticleManager();
                    }
                }
            }
        }
        return instance;
    }

    public void insertArticle(){
        User user = new User();
        user.setName("张鸿洋");
        user.setDesc("Androd技术大牛");
        userDao.add(user);

        Article article = new Article();
        article.setTitle("Android技术");
        article.setUser(user);
        articleDao.add(article);

    }

    public void insertArticleAll(){
        List<Article> articleList = new ArrayList<Article>();

        User user1 = new User("张鸿洋1", "Androd技术大牛");
        userDao.add(user1);
        User user2 = new User("张鸿洋2", "Androd技术大牛");
        userDao.add(user2);
        Article article1 = new Article("技术1", user1);
        articleList.add(article1);
        Article article2 = new Article("技术2", user2);
        articleList.add(article2);

        articleDao.addAll(articleList);
        System.out.println("ArticleManager===insertArticleAll");
    }

    public void updateArticle(){
        Article article = getArticle();
        article.setTitle("3434");
        Boolean flag = articleDao.update(article);
        System.out.println("ArticleManager===updateArticle==="+flag);
    }

    public void deleteArticle(){
        Boolean flag = articleDao.delete(1);
        System.out.println("ArticleManager===deleteArticle==="+flag);
    }

    public Article getArticle(){
        Article article = articleDao.get(1);
        System.out.println("ArticleManager===getArticle==="+article.getTitle()
                +"==="+article.getUser().getName()+"==="+article.getUser().getDesc());

        return article;
    }

    public List<Article> getArticleList(){
        List<Article> articleList = articleDao.getAll();
        for (Article article: articleList) {
            System.out.println("ArticleManager===getArticleList==="+article.getTitle()
                    +"==="+article.getUser().getId()
                    +"==="+article.getUser().getName()
                    +"==="+article.getUser().getDesc());
        }

        return null;
    }

    public List<Article> getArticleListByUserId(){
        List<Article> articleList = new ArrayList<Article>();
        User user = new User("张鸿洋1", "Androd技术大牛");
        userDao.add(user);
        Article article1 = new Article("技术1", user);
        articleList.add(article1);
        Article article2 = new Article("技术2", user);
        articleList.add(article2);
        articleDao.addAll(articleList);

        List<Article> articleList2 = articleDao.getArticlesByUserId(user.getId());
        for (Article article: articleList2) {
            System.out.println("ArticleManager===getArticleListByUserId==="+article.getTitle()
                    +"==="+article.getUser().getId()
                    +"==="+article.getUser().getName()
                    +"==="+article.getUser().getDesc());
        }

        return null;
    }

}
