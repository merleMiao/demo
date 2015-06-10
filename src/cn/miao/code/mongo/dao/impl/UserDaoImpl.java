package cn.miao.code.mongo.dao.impl;

import cn.miao.code.mongo.dao.UserDao1;

import cn.miao.code.mongo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/8.
 */
@Repository
public class UserDaoImpl implements UserDao1 {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> list() {
        List<User> list = mongoTemplate.findAll(User.class);
        return list;
    }

    @Override
    public User getOne(int userId) {
        User user = mongoTemplate.findOne(new Query(Criteria.where("id").is(userId)),User.class);
        return user;
    }

    @Override
    public void save(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public void removeOne(int userId) {
        Criteria criteria = Criteria.where("id").in(userId);
        if(criteria != null){
            Query query = new Query(criteria);
            if(query != null && mongoTemplate.findOne(query, User.class) != null){
                mongoTemplate.remove(mongoTemplate.find(query, User.class));
            }
        }
    }
}
