package cn.miao.code.uc.test;

import cn.miao.code.uc.dao.UserService;
import cn.miao.code.uc.pojo.User;
import com.miao.util.http.HttpClientUtils;
import com.miao.util.http.MObjectMapper;
import com.miao.util.json.JsonUtils;
import com.miao.util.uuid.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
@ActiveProfiles("product")
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void saveRemote(){
        Map map = new HashMap();
        map.put("name", "苗少康");
        map.put("password", "123456");
        map.put("sex", 0);
        String url = "http://localhost:8080/demo/api/user.json";
        String result = HttpClientUtils.postString(url, map);
        System.out.println("result:" + result);
    }

    @Test
    public void findByUUID(){
        String uuid = "HY2MMPcx9WahwNLnaJmJL9";
        User user = userService.findByUUID(uuid);
        System.out.println("user:" + JsonUtils.toJson(user, new MObjectMapper()));
    }

    @Test
    public void login(){
        Map map = new HashMap();
        String url = "http://localhost:8088/api/user/login.json";
        map.put("identify",1000);
        map.put("password","123456");
        String result = HttpClientUtils.postString(url, map);
        System.out.println(result);
    }

    @Test
    public void uuid(){
        for(int i = 0; i < 20; i ++){
            System.out.println("第" + i + "个:"+ UUIDUtils.base58Uuid());

        }
    }


}
