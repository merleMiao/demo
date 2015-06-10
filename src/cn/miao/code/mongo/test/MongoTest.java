package cn.miao.code.mongo.test;

import cn.miao.code.mongo.dao.UserDao1;
import cn.miao.code.mongo.pojo.User;
import com.miao.util.http.MObjectMapper;
import com.miao.util.json.JsonUtils;
import com.miao.util.uuid.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
@ActiveProfiles("product")
public class MongoTest {

    @Autowired
    private UserDao1 userDao1;

    @Test
    public void save(){
        User user = new User();
        user.setId(1003);
        user.setName("张明");
        user.setPassword("123456");
        user.setSex(0);
        userDao1.save(user);
        User result = userDao1.getOne(1000);
        System.out.println(JsonUtils.toJson(result, new MObjectMapper()));

    }
}
