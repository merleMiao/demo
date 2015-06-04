package cn.miao.code.uc.web;

import org.apache.commons.lang.ArrayUtils;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Shaokang on 2015/6/4.
 */
@Controller
public class TemperatureController {
    private static Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    @RequestMapping(value = "/temp/list", method = RequestMethod.GET)
    public Object getTempList(HttpServletRequest httprequest){
        ModelAndView modelAndView = new ModelAndView();
        Map map = new HashMap();
        map.put("title","月度温度");
        map.put("subtitle","Month");
        String[] month1 = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        List<String> month = new ArrayList<String>();
        month.addAll(Arrays.asList(month1));
        map.put("xAxis", month);
        List<Map> list = new ArrayList<Map>();
        Map temp = new HashMap();
        temp.put("name", "Tokyo");
        Double[] tempe = new Double[]{7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6};
        temp.put("data", tempe);
        list.add(temp);
        map.put("series", list);
        modelAndView.addObject("result", 0);
        modelAndView.addObject("msg", "成功");
        modelAndView.addObject("map", map);
        return modelAndView;
    }
}
