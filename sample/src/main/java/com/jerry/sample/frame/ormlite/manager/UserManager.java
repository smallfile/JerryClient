package com.jerry.sample.frame.ormlite.manager;

import com.jerry.sample.MyApplication;
import com.jerry.sample.frame.ormlite.bean.User;
import com.jerry.sample.frame.ormlite.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2017/5/18.
 */

public class UserManager {

    static UserManager instance;
    private UserDao userDao;

    public UserManager() {
        userDao = new UserDao(MyApplication.getApplication());
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                UserManager inst = instance;
                if (inst == null) {
                    synchronized (UserManager.class) {
                        instance = new UserManager();
                    }
                }
            }
        }
        return instance;
    }

    public void insertUser(){
        User user = new User();
        user.setName("张鸿洋");
        user.setDesc("Androd技术大牛");

        System.out.println("UserManager===insertUser==="+user.getName()+"==="+user.getDesc());
        userDao.add(user);
    }

    public void insertUserAll(){
        List<User> userList = new ArrayList<User>();

        User user1 = new User("张鸿洋1", "Androd技术大牛");
        User user2 = new User("张鸿洋2", "Androd技术大牛");
        User user3 = new User("张鸿洋3", "Androd技术大牛");
        User user4 = new User("张鸿洋4", "Androd技术大牛");
        User user5 = new User("张鸿洋5", "Androd技术大牛");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        userDao.addAll(userList);
        System.out.println("UserManager===insertUserAll");
    }

    public void updateUser(){
        User user = getUser();
        user.setName("dfdfdfd");
        Boolean flag = userDao.update(user);
        System.out.println("UserManager===updateUser==="+flag);
    }

    public void deleteUser(){
        Boolean flag = userDao.delete(1);
        System.out.println("UserManager===deleteUser==="+flag);
    }

    public User getUser(){
        User user = userDao.get(1);
        System.out.println("UserManager===getUser==="+user.getId()+"==="+user.getName()+"==="+user.getDesc());

        return user;
    }

    public List<User> getUserList(){
        List<User> userList = userDao.getAll();
        for (User user: userList) {
            System.out.println("UserManager===getUserList==="+user.getId()+"==="+user.getName()+"==="+user.getDesc());
        }

        return null;
    }

}
