package cn.miao.plat.core.json;


import com.miao.util.http.MObjectMapper;
import com.miao.util.json.JsonUtils;
import com.miao.util.request.RequestUtils;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class MappingJsonView extends MappingJacksonJsonView {

    private Logger logger = LoggerFactory.getLogger(MappingJsonView.class);

    public static String getReqInfo(HttpServletRequest request) {
        String breakFix = "\t";
        String method = request.getMethod();
        String params = RequestUtils.getParams(request);
        String ip = RequestUtils.getRemoteHost(request);
        String content = breakFix + "ip:" + ip
                + breakFix + "url:" + request.getRequestURI()
                + breakFix + "method:" + method
                + breakFix + "user:" + JsonUtils.toJson(request.getAttribute("user"))
                + breakFix + "data:" + params;
        return content;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MObjectMapper mapper = new MObjectMapper();
        setObjectMapper(mapper);
//        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        List<Map> filters = (List<Map>) request.getAttribute("jsonFilters");
        if (filters != null) {
            for (Map m : filters) {
                Class<?> mixin = (Class<?>) m.get("mixin");
                Class<?> target = (Class<?>) m.get("target");
                mapper.getSerializationConfig().addMixInAnnotations(target, mixin);
            }
        }
        super.render(model, request, response);
    }
}
