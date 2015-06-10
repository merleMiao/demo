package cn.miao.code.mongo.dao;

import cn.miao.code.mongo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/8.
 */
@Repository
public interface UserDao1 {

    public List<User> list();

    public User getOne(int userId);

    public void save(User user);

    public void removeOne(int userId);
}
