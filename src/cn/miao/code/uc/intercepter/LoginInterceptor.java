package cn.miao.code.uc.intercepter;

import cn.miao.code.uc.dao.UserService;
import cn.miao.code.uc.pojo.User;
import com.miao.util.annotation.LoginFilter;
import com.miao.util.base.pojo.LoginEnum;
import com.miao.util.debug.Debug;
import com.miao.util.json.JsonUtils;
import com.miao.util.request.RequestUtils;
import com.miao.util.uuid.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    UserService userService;
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private long start = System.currentTimeMillis();
    private String logData = "";

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-sensky-real-ip");
            if (ip == null || ip.trim().equals("")) {
                ip = request.getHeader("x-real-ip");
                if (ip == null || ip.trim().equals("")) {
                    ip = request.getRemoteHost();
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static void main(String[] args) {
        String str = "{\"id\":28618,\"uuid\":\"6LYe77ma6zf7prWdUMPncL\",\"rel\":{},\"version\":0,\"ico\":\"http://file01.yugusoft.com/M00/01/1A/OkTuVVRrKb6AUF_UAABEWRTFeSs504/headerIconImage.jpg\",\"delete\":0,\"create_date\":null,\"last_up_date\":null,\"nick_name\":\"Susan\",\"clazz\":\"user\"} data:app_key=82f9KD6TAMb2waoMR9en5C&comp_id=DbazTRK72RF1Beiqxx6SSQ&end=20&obj_user_id=6LYe77ma6zf7prWdUMPncL&start=0&token=99975398204c81e07b8719bf94dbc97c resp:uuid=Fu62BjizyhMQoPhoJzYv67        {\"result\":0,\"msg\":\"success\",\"total\":198,\"statistics\":{\"my_un_finish\":7,\"my_fallowed\":0,\"my_joined\":1,\"my_finish\":4},\"task\":[{\"uuid\":\"PVwcZHQ7TLUFjUogGkpPsD\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216618,\"name\":\"Ios对话窗：图片处理继续优化，对话窗查看缩略图（约5K），点击loading后查看原图（实际大小）\",\"encrypted\":0,\"summary\":\"崔兴兰：默认图\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417008260000,\"last_up_date\":1417058136000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"4f6pzsjzJqFYP4AP23NJwv\",\"show_id\":\"B6839\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417003980000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417058136000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417058429000,\"group_type\":3,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"Jgy4Rhopcn8zLhvQErcyeJ\",\"rel\":{\"manager\":[{\"uuid\":\"HiZkkUNjEPQooUF12hTKpW\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216619,\"name\":\"Ios对话窗：图片处理继续优化，对话窗查看缩略图（约5K），点击loading后查看原图（实际大小）\",\"encrypted\":0,\"summary\":\"崔兴兰：默认图\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417008260000,\"last_up_date\":1417058136000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"1\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"PVwcZHQ7TLUFjUogGkpPsD\",\"show_id\":\"B6840\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417003980000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417058136000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417058357000,\"group_type\":4,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"D63uXAUho4srKVaeKCmEA6\",\"rel\":{\"manager\":[{\"uuid\":\"HiZkkUNjEPQooUF12hTKpW\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216623,\"name\":\"Ios任务详情+评论：图片处理继续优化，详情页查看缩略图（约5K），点击loading后查看效果图（约20K），同时提供查看原图（实际大小）按钮\",\"encrypted\":0,\"summary\":\"崔兴兰：效果图\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"WAITING_AGREE\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417003833000,\"last_up_date\":1417058392000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"3\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"2jW3MwcCwLWdFqbimzqcL5\",\"show_id\":\"B6838\",\"child_num\":\"0\",\"progress_percent\":100.0,\"plan_start_date\":1417004025000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417057229000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":1417057558000,\"last_opt_date\":1417057449000,\"group_type\":4,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"2jW3MwcCwLWdFqbimzqcL5\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216623,\"name\":\"Ios任务详情+评论：图片处理继续优化，详情页查看缩略图（约5K），点击loading后查看效果图（约20K），同时提供查看原图（实际大小）按钮\",\"encrypted\":0,\"summary\":\"崔兴兰：效果图\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417003833000,\"last_up_date\":1417058391000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"5\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"4f6pzsjzJqFYP4AP23NJwv\",\"show_id\":\"B6835\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417004025000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417057229000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417057449000,\"group_type\":3,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"3YWRGNZnnHBgYaHRKgxQW2\",\"rel\":{\"manager\":[{\"uuid\":\"3VnEQorNz1L5tqUJivaZa8\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216578,\"name\":\"移动端-任务列表：需要过滤任务List数据\",\"encrypted\":0,\"summary\":\"代德宏：hi\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"WAITING_AGREE\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416916155000,\"last_up_date\":1417055859000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"4f6pzsjzJqFYP4AP23NJwv\",\"show_id\":\"B6784\",\"child_num\":\"0\",\"progress_percent\":100.0,\"plan_start_date\":1416916344000,\"plan_end_date\":1417168800000,\"last_comment_date\":1417055843000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":1417056151000,\"last_opt_date\":1417056136000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"GTtQB5pE5Nvv3oHUMDibjn\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216580,\"name\":\"【鱼骨】技术相关，新版本发布等\",\"encrypted\":0,\"summary\":\"Susan：【Windows版】鱼骨V1.0.0.1949已发布！\\n新特性：\\n1、【新增】IM消息与Ios移动端全程同步；\\n2、【新增】鱼骨采用最新高级别加密方案，为企业信息安全保驾护航；\\n3、【优化】文化墙正式...\",\"tags\":[],\"detail\":\"\",\"priority\":1,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1405419584000,\"last_up_date\":1417055887000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"28\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"7nQG3iQTd4Pxdkdy1NQmpZ\",\"show_id\":\"B3171\",\"child_num\":\"0\",\"progress_percent\":50.0,\"plan_start_date\":1405419584000,\"plan_end_date\":1451556000000,\"last_comment_date\":1417055887000,\"real_start_date\":1405419584000,\"real_end_date\":1405419944000,\"last_sub_date\":null,\"last_opt_date\":1417056107000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"CcAkA851AmbbF6n92ULuMo\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216575,\"name\":\"【鱼骨2014】第48周产品工作计划\",\"encrypted\":0,\"summary\":\"Susan：待讨论：\\n\\n\\n\\t6、Pc端消息的全部已读，如何处理？因为Pc端的未完里边还有未完的抄送任务，和mobile设计不同。服务器要支持的话，需要额外开发。\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416571026000,\"last_up_date\":1417055777000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"5\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"PZLQN128hf8edyN2AWmAHf\",\"show_id\":\"B6723\",\"child_num\":\"0\",\"progress_percent\":70.0,\"plan_start_date\":1416792480000,\"plan_end_date\":1417406400000,\"last_comment_date\":1417055777000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417055998000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"VqRSxRE4L9kcyxqAVGsShG\",\"rel\":{\"manager\":[{\"uuid\":\"BSqrQXSum5C2Wv7z7XgtaW\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216489,\"name\":\"Ios对话窗：图片处理继续优化，对话窗查看缩略图（约5K），点击loading后查看原图（实际大小）\",\"encrypted\":0,\"summary\":\"Susan：各位，需有有变更：IM对话窗的图片显示，流程需要类似于任务图片的处理，缩略图5K--&gt;效果图20K--&gt;原图，逐步处理。\\n\\n\\n\\t原型如下：\\n\\n\\n\\t[图片]\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417008260000,\"last_up_date\":1417051666000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"1\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"PVwcZHQ7TLUFjUogGkpPsD\",\"show_id\":\"B6841\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417003980000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417051666000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417051958000,\"group_type\":4,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"TmmG8RrprG4o3DDHcFJ2NG\",\"rel\":{\"manager\":[{\"uuid\":\"HiZkkUNjEPQooUF12hTKpW\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216461,\"name\":\"Ios和Android移动端“加载中”GIF动图设计，需要3-5套创意方案，备选\",\"encrypted\":0,\"summary\":\"崔兴兰：好的，收到\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416457317000,\"last_up_date\":1417051319000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"7\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"4f6pzsjzJqFYP4AP23NJwv\",\"show_id\":\"B6658\",\"child_num\":\"0\",\"progress_percent\":40.0,\"plan_start_date\":1416457497000,\"plan_end_date\":1418205600000,\"last_comment_date\":1417049472000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417049692000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"K9cTV7Kf3YtxZRR9mJksXo\",\"rel\":{\"manager\":[{\"uuid\":\"SHgoC8H9SH3eKVc2ZwaNNy\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216582,\"name\":\"Win消息同步-未读标识的条件：如果是自己在其他终端发送的消息，在其他终端不应该标记为未读\",\"encrypted\":0,\"summary\":\"Susan：刘涛，一边改消息同步的问题，同时做这个可以吗？这个优先级提高吧。这个目前看是影响最大的了。\\n\\n\\n\\t消息同步的问题可以逐步优化的。\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416973454000,\"last_up_date\":1417055943000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"4qj1uA6J2VPhPiZ7ua2zrT\",\"show_id\":\"B6799\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1416973644000,\"plan_end_date\":1417341600000,\"last_comment_date\":1417049145000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417049438000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"HYJ5uyzpR6dGRDuriM4NB3\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216422,\"name\":\"鱼骨官网建设：需要对鱼骨官方网站www.yugusoft.com进行改版，实现软件下载站+公司官网的功能\",\"encrypted\":0,\"summary\":\"崔建勋：嗯，我把文案再补充一下\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1413018356000,\"last_up_date\":1417048753000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"17\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"CYGNAe2Ejt9cxg728ehcqy\",\"show_id\":\"B5155\",\"child_num\":\"0\",\"progress_percent\":60.0,\"plan_start_date\":1413018465000,\"plan_end_date\":1417341600000,\"last_comment_date\":1417048753000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417048973000,\"group_type\":3,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"TgTYZsoMvLRdvsXfzRayNx\",\"rel\":{\"manager\":[{\"uuid\":\"SHgoC8H9SH3eKVc2ZwaNNy\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216421,\"name\":\"任务-列表：在预览区将任务审批通过后，需要左侧列表区默认将焦点移到下一条（最后一条标记完成后，焦点移到还是最后一条）\",\"encrypted\":0,\"summary\":\"任务-列表：在预览区将任务审批通过后，需要左侧列表区默认将焦点移到下一条（最后一条标记完成后，焦点移到还是最后一条）\\n这个需求可能很难实现，不过还是帮忙想想办法，看看怎么实现。\\n优先级放在这些需求之后\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417048725000,\"last_up_date\":1417048725000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"0\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"77s8RWqY7iSvpi2tpspbrT\",\"show_id\":\"B6848\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417048916000,\"plan_end_date\":1419588000000,\"last_comment_date\":null,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417048725000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"MV9hik2D1Zt7UuC4EiqcoY\",\"rel\":{\"manager\":[{\"uuid\":\"BSqrQXSum5C2Wv7z7XgtaW\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216623,\"name\":\"Ios任务详情+评论：图片处理继续优化，详情页查看缩略图（约5K），点击loading后查看效果图（约20K），同时提供查看原图（实际大小）按钮\",\"encrypted\":0,\"summary\":\"Susan：小孟，不好意思，昨晚马总在另一个任务里再次明确了图片的处理原则，必须是缩略图-效果图-原图全套设计，不能省略步骤二。\\n\\n\\n\\t咱们还是按昨晚提交的第一次需求，来技术支持吧！沟通不当的地方，还请谅解。\\n\\n...\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417003833000,\"last_up_date\":1417058392000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"2jW3MwcCwLWdFqbimzqcL5\",\"show_id\":\"B6837\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417004025000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417047423000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417047715000,\"group_type\":4,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"MB6yc6dZvESZfQVjbqY2DB\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216408,\"name\":\"Ios+Android客户端-Push通知免打扰设置：需要支持按手机所在时区的23：00-次日8：00免打扰\",\"encrypted\":0,\"summary\":\"Susan：V1.0设计是，不提供用户可设置免打扰起止时间点。你的这个需求，建议v2.0再支持。\\n\\n\\n\\t1.0设计，消息免打扰：23：00——8：00；开/关；（待实现）服务器按客户端设置中所在时区进行本地化免打...\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416991025000,\"last_up_date\":1417046786000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"DL2ebqPJZjqY9xr2uYGbjy\",\"show_id\":\"B6822\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1416991216000,\"plan_end_date\":1422698400000,\"last_comment_date\":1417046786000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417047078000,\"group_type\":3,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"EzcVgcDB3puVz2tVC4ApAP\",\"rel\":{\"manager\":[{\"uuid\":\"QxSKnEd7AGjr7j68SGVfbQ\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216410,\"name\":\"Push通知-免打扰设置：服务器需要支持用户设定的免打扰开关、免打扰起止时间点自定义；客户端提供编辑免打扰起止时间修改功能\",\"encrypted\":0,\"summary\":\"Push通知-免打扰设置：服务器需要支持用户设定的免打扰开关、免打扰起止时间点自定义；客户端提供编辑免打扰起止时间修改功能\\n这是v2.0待实现的功能。记录上。\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"POST_PONE\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417046967000,\"last_up_date\":1417046982000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"0\",\"show_times\":\"0\",\"new_status\":1,\"new_comment\":0,\"parent_uuid\":\"V5vJZpRHVAViGjspjWWFdG\",\"show_id\":\"B6846\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417047159000,\"plan_end_date\":1427796000000,\"last_comment_date\":null,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417046967000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"HHFxBpQRfCLpe2hrSpq4qt\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216403,\"name\":\"报告-工作汇报-Ui优化：默认显示自己和所有下属人员的最新一个工作汇报\",\"encrypted\":0,\"summary\":\"Susan：@马总：嗯，是按方案二做的。\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416992619000,\"last_up_date\":1417046119000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"3\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"E85X7DrTRWauUZrNrnKVn8\",\"show_id\":\"B6826\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1416992810000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417046119000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417046412000,\"group_type\":3,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"DXoFghU1AVBgQGWn5J7SDP\",\"rel\":{\"manager\":[{\"uuid\":\"YUseiccsUNQxcQeBPK3Td3\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216402,\"name\":\"报告-工作汇报-Ui优化：默认显示自己和所有下属人员的最新一个工作汇报\",\"encrypted\":0,\"summary\":\"Susan：@马总：嗯，是按方案二做的。\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1416992619000,\"last_up_date\":1417046119000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"2\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"HHFxBpQRfCLpe2hrSpq4qt\",\"show_id\":\"B6827\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1416992810000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417046119000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417046339000,\"group_type\":4,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"KbiNBavtuey1Yc4r5oupR4\",\"rel\":{\"manager\":[{\"uuid\":\"SHgoC8H9SH3eKVc2ZwaNNy\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216404,\"name\":\"抄送任务：作屏蔽\",\"encrypted\":0,\"summary\":\"要屏蔽抄送人是提交人或负责人的任务。\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417016805000,\"last_up_date\":1417046155000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"0\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"77s8RWqY7iSvpi2tpspbrT\",\"show_id\":\"B6843\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1417017024000,\"plan_end_date\":1417773600000,\"last_comment_date\":null,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417016805000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"E3kjYLMxbgt2QHPAJh1v1Z\",\"rel\":{\"manager\":[{\"uuid\":\"3VnEQorNz1L5tqUJivaZa8\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216514,\"name\":\"抄送TAB屏蔽特定任务\",\"encrypted\":0,\"summary\":\"IOS和安卓抄送数据，要屏蔽抄送人是提交人或负责人的任务。\",\"tags\":[],\"detail\":\"\",\"priority\":3,\"grade\":0.0,\"status\":\"WAITING_AGREE\",\"lifecycle\":0,\"delete\":0,\"create_date\":1417016629000,\"last_up_date\":1417052840000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"0\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"4f6pzsjzJqFYP4AP23NJwv\",\"show_id\":\"B6842\",\"child_num\":\"0\",\"progress_percent\":100.0,\"plan_start_date\":1417016848000,\"plan_end_date\":1417341600000,\"last_comment_date\":null,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":1417053060000,\"last_opt_date\":1417016629000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null},{\"uuid\":\"CEt616yGck9mPpTU6r9BD2\",\"rel\":{\"manager\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}],\"creator\":[{\"uuid\":\"6LYe77ma6zf7prWdUMPncL\"}]},\"version\":216204,\"name\":\"鱼骨帮助手册：管理员专用，文档撰写，Pdf格式\",\"encrypted\":0,\"summary\":\"马飞：帮助文档应该在如何用这个工具上面，各个部门如何用，整个公司如何用。\",\"tags\":[],\"detail\":\"\",\"priority\":2,\"grade\":0.0,\"status\":\"RUNNING\",\"lifecycle\":0,\"delete\":0,\"create_date\":1413019475000,\"last_up_date\":1417012919000,\"comp_id\":\"DbazTRK72RF1Beiqxx6SSQ\",\"res_type\":\"TASK\",\"open_type\":0,\"member_size\":\"0\",\"file_num\":\"0\",\"fav_times\":\"0\",\"comment_times\":\"5\",\"show_times\":\"0\",\"new_status\":0,\"new_comment\":0,\"parent_uuid\":\"WwsELqfF8yUKqjCsHEvZJK\",\"show_id\":\"B5161\",\"child_num\":\"0\",\"progress_percent\":0.0,\"plan_start_date\":1413019584000,\"plan_end_date\":1417773600000,\"last_comment_date\":1417012919000,\"real_start_date\":null,\"real_end_date\":null,\"last_sub_date\":null,\"last_opt_date\":1417013211000,\"group_type\":0,\"modify_from\":null,\"modify_to\":null,\"lock_status\":0,\"cron_expression\":null}]}";
        Pattern p = Pattern.compile("\"comp_id.*?\":\".*?\"");
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    public static boolean analyse(String data, String compId) {
        boolean result = true;
        if (StringUtils.isBlank(compId)) return result;
        if (StringUtils.contains(data, "comp_id")) {
            Pattern p = Pattern.compile("\"comp_id.*?\":\".*?\"");
            Matcher m = p.matcher(data);
            ArrayList<String> strs = new ArrayList<String>();
            while (m.find()) {
                String key = "\"comp_id\":\"" + compId + "\"";
                String match = m.group();
                boolean crossComp = !StringUtils.equals(key, match);
                if (crossComp) {
                    crossComp = !StringUtils.contains(match, "null");
                }
                if (crossComp || StringUtils.contains(data, "\"is_delete\"")) {
                    Debug.report("资源异常:" + key + "|||" + m.group() + "|||" + data + "|||" + compId);
//                    return false;
//                    throw new RuntimeException("对不起，参数异常，请联系客户验证");
                }
            }
        }
        return result;
    }

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod _handler = (HandlerMethod) handler;
        LoginFilter annotation = _handler.getMethodAnnotation(LoginFilter.class);
        boolean needLogin = true;
        if (annotation != null) {
            LoginEnum em = annotation.login();
            if (em == LoginEnum.NO) {
                needLogin = false;
            }
        }
        String path = request.getRequestURL().toString() + "?" + StringUtils.defaultString(request.getQueryString());
        logData = "path:" + path + "\t"
                + "getRequestURL:" + request.getRequestURL() + "\t"
                + "getMethod:" + request.getMethod() + "\t"
                + "getQueryString:" + request.getQueryString() + "\t"
                + "getParameterMap:" + JsonUtils.toJson(request.getParameterMap());
        //集中处理IP地址
        String ip = getRemoteHost(request);
        request.setAttribute("req_token", UUIDUtils.base58Uuid());
        request.setAttribute("ip", ip);

        //以下接口都是需要登陆才能使用的啦
//        start = System.currentTimeMillis();
//        RequestUtils req = new RequestUtils(request);
//        String token = req.getParameter("token");
//        User user = userService.getUser(token);
//        if (user == null) {
//            String url = ServerApiUtils.getTaskApi() + "/proxy/user/my.json?token=" + StringUtils.trim(token);
//            String resp = HttpClientUtils.getString(url);
//            logger.info("uc_login:" + url + "\t" + resp);
//            Map respMap = (Map) JsonUtils.toBean(resp, Map.class);
//            if (MapUtils.getIntValue(respMap, "result", -1) == 0) {
//                respMap = MapUtils.getMap(respMap, "user");
//                user = new User();
//                user.setCompId(MapUtils.getString(respMap, "comp_id"));
//                user.setUuid(MapUtils.getString(respMap, "uuid"));
//                user.setId(MapUtils.getIntValue(respMap, "id"));
//                user.setNickName(MapUtils.getString(respMap, "nick_name"));
//                user.setIco(MapUtils.getString(respMap, "ico"));
//                logger.info("uc_login_user:" + JsonUtils.toJson(user));
//                User _user = userService.findByUUID(user.getUuid());
//                if (_user == null) {
//                    userService.save(user);
//                } else {
////                    userService.update(user);
//                }
//                Jedis jedis = RedisCached.getJedis();
//                try {
//                    jedis.hset("task.user", user.getUuid(), token);
//                    RedisCached.returnResource(jedis);
//                } catch (Exception e) {
//                    RedisCached.returnBrokenResource(jedis);
//                }
//            }
//        }
//
//        boolean pass = user != null || !needLogin;
//        if (!pass) {
//            String url = request.getRequestURL().toString();
//            if (StringUtils.endsWith(url, ".html")) {
//                response.sendRedirect(request.getContextPath());
//            } else {
//                Map map = new HashMap();
//                map.put("result", ExpScheme.LOGIN_TIME_OUT);
//                map.put("msg", "对不起，没有登陆或者登陆过期，请重新登陆,code:" + token);
//                response.setHeader("Content-Type", "application/json;charset=utf-8");
//                response.setCharacterEncoding("utf-8");
//                response.getWriter().write(JsonUtils.toJson(map));
//            }
//        } else {
////            if (!StringUtils.containsIgnoreCase("代德宏/张俊杰/苗少康", user.getNickName())) {
////                map.put("result", ExpScheme.LOGIN_TIME_OUT);
////                map.put("msg", "非开发人员当前禁止登陆,code:" + token);
////                response.setHeader("Content-Type", "application/json;charset=utf-8");
////                response.setCharacterEncoding("utf-8");
////                response.getWriter().write(JsonUtils.toJson(map));
////            }
//            if (user != null) {
//                response.setHeader("debug:uid", String.valueOf(user.getUuid()));
//                request.setAttribute("user", user);
//                userService.cache(user);
//                userService.cache(token, user);
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String data = JsonUtils.toJson(modelAndView.getModel());
            String compId = request.getParameter("comp_id");
            analyse(data, compId);
            String uuid = (String) request.getAttribute("req_token");
            logger.info(
                    "req:uuid=" + uuid + "\t"
                            + getReqInfo(request) + "\t"
                            + "resp:uuid=" + uuid + "\t"
                            + JsonUtils.toJson(modelAndView.getModel())
            );
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long end = System.currentTimeMillis();
        System.out.println("control_cost:" + (end - start) + "ms\t" + logData);
    }
}
