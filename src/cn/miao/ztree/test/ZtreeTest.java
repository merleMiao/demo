package cn.miao.ztree.test;

import cn.miao.plat.config.Config;
import cn.miao.ztree.dao.ZtreeService;
import com.miao.util.http.HttpClientUtils;
import com.miao.util.json.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
@ActiveProfiles("product")
public class ZtreeTest {

    @Autowired
    ZtreeService ztreeService;

    @Test
    public void listByPid(){
        List<Map> list = ztreeService.listByPid(1);
        System.out.println(JsonUtils.toJson(list));
    }

    @Test
    public void listByPidRemote(){
        String url = Config.server + "ztree/list.json?pid=1";
        String result = HttpClientUtils.getString(url);
        System.out.println(result);
    }
}
