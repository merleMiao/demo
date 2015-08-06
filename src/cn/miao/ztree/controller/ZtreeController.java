package cn.miao.ztree.controller;

import cn.miao.ztree.dao.ZtreeService;
import com.miao.util.json.JsonUtils;
import com.miao.util.request.RequestUtils;
import com.miao.util.scheme.ResultScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/8/5.
 */
@Controller
public class ZtreeController {

    @Autowired
    ZtreeService ztreeService;

    @RequestMapping(value = "/ztree/list", method = RequestMethod.GET)
    public void userDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        RequestUtils req = new RequestUtils(request);
        int pid = req.getInt("pid", -1);
        List<Map> list = ztreeService.listByPid(pid);
        for(Map map : list){
            int id = Integer.valueOf(String.valueOf(map.get("id")));
            List<Map> children = ztreeService.listByPid(id);
            map.put("children", children);
        }
//        modelAndView.addObject("list", list);
//        modelAndView.addObject("msg", "success");
//        modelAndView.addObject("result", ResultScheme.ACT_RESULT_SUCCESS);
        response.getWriter().print(JsonUtils.toJson(list));
    }

}
