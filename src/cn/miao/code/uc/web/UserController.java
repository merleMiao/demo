package cn.miao.code.uc.web;

import cn.miao.code.uc.dao.UserService;
import cn.miao.code.uc.pojo.User;
import com.miao.util.request.RequestUtils;
import com.miao.util.scheme.ResultScheme;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Object userDetail(HttpServletRequest request) throws Exception{
        RequestUtils req = new RequestUtils(request);
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)req.create(User.class);
        int i = userService.save(user);
        if(i == 1){
            User u = userService.findByUUID(user.getUuid());
            modelAndView.addObject("user", u);
            modelAndView.addObject("result", ResultScheme.ACT_RESULT_SUCCESS);
            modelAndView.addObject("msg", "成功");
        }else {
            modelAndView.addObject("result", ResultScheme.ACT_RESULT_FAIL);
            modelAndView.addObject("msg", "成功");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object userLogin(HttpServletRequest request) throws Exception{
        RequestUtils req = new RequestUtils(request);
        ModelAndView modelAndView = new ModelAndView();
        int identify = req.getInt("identify");
        User user = userService.findById(identify);
        String password = req.getParameter("password");
        if(StringUtils.equalsIgnoreCase(password, user.getPassword())){
            modelAndView.addObject("user", user);
            modelAndView.addObject("result", ResultScheme.ACT_RESULT_SUCCESS);
            modelAndView.addObject("msg", "成功");
        }else {
            modelAndView.addObject("result", ResultScheme.ACT_RESULT_FAIL);
            modelAndView.addObject("msg", "失败");
        }
        return modelAndView;
    }
}
