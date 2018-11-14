package controller;

import com.alibaba.fastjson.JSON;
import commons.annotation.DataSourceChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAllstatisticsService;
import service.IStatisticsService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqiong on 2018/11/8.
 */

@Controller
@RequestMapping("statistics")
public class StudentScoreStatisticsController {

    @Autowired
    public IStatisticsService statisticsService;

    @Autowired
    public IAllstatisticsService allStatisticsService;

    @RequestMapping("getScoreByGrade")
    @ResponseBody
    public String getScoreByGrade(HttpServletRequest request){
        String startStr = request.getParameter("start");
        String plimitStr = request.getParameter("plimit");
        Integer start = Integer.valueOf(startStr);
        Integer plimit = Integer.valueOf(plimitStr);
        HashMap qryMapUser = new HashMap();
        qryMapUser.put("start",start);
        qryMapUser.put("plimit",plimit);
        qryMapUser.put("islimit",1);
        List userList = statisticsService.getUsers(qryMapUser);
        HashMap qryMapUserCourse = new HashMap();
        qryMapUserCourse.put("userlist",userList);
        HashMap qryMapUserScore = new HashMap();
        qryMapUserScore.put("userlist",userList);
        List result = allStatisticsService.parseMemberScoreWithList(qryMapUserScore, qryMapUserCourse);
        return JSON.toJSONString(result);
    }

    @RequestMapping("getScoreByBingQu")
    @ResponseBody
    public String getScoreByBingQu(HttpServletRequest request){
        String startStr = request.getParameter("start");
        String plimitStr = request.getParameter("plimit");
        Integer start = Integer.valueOf(startStr);
        Integer plimit = Integer.valueOf(plimitStr);
        HashMap qryMapUser = new HashMap();
        qryMapUser.put("start",start);
        qryMapUser.put("plimit",plimit);
        qryMapUser.put("islimit",1);
        List userList = statisticsService.getUsers(qryMapUser);
        HashMap qryMapUserCourse = new HashMap();
        qryMapUserCourse.put("userlist",userList);
        HashMap qryMapUserScore = new HashMap();
        qryMapUserScore.put("userlist",userList);
        List result = allStatisticsService.parseMemberScoreByBQWithList(allStatisticsService.parseMemberScore(qryMapUserScore, qryMapUserCourse));
        return JSON.toJSONString(result);
    }
}
