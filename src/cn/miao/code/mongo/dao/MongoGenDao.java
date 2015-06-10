package cn.miao.code.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/8.
 */
public abstract class MongoGenDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;




    /**
     * 保存一个对象
     * @param t
     */
    public void save(T t){
        this.mongoTemplate.save(t);
    }

    /**
     * 为属性自动注入bean
     * @param mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
}
