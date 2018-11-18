package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import commons.annotation.DataSourceChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAllstatisticsService;
import service.IStatisticsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "getScoreByGrade",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getScoreByGrade(HttpServletRequest request,HttpServletResponse response){
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
        response.setHeader("Transfer-Encoding","chunked");
        response.setCharacterEncoding("utf-8");
        response.setHeader("contentType", "text/html; charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        return JSON.toJSONString(result);
    }

    @RequestMapping("getScoreByGradeCount")
    @ResponseBody
    public String getScoreByGradeCount(HttpServletRequest request,HttpServletResponse response){
        HashMap qryMapUser = new HashMap();
        List userList = statisticsService.getUsers(qryMapUser);
        HashMap qryMapUserCourse = new HashMap();
        qryMapUserCourse.put("userlist",userList);
        HashMap qryMapUserScore = new HashMap();
        qryMapUserScore.put("userlist",userList);
        List result = allStatisticsService.parseMemberScoreWithList(qryMapUserScore, qryMapUserCourse);
        JSONObject json = new JSONObject();
        json.put("sum",result.size());
        response.setHeader("Transfer-Encoding","chunked");
        response.setCharacterEncoding("utf-8");
        response.setHeader("contentType", "text/html; charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        return JSON.toJSONString(json);
    }

    @RequestMapping(value = "getScoreByBingQu",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getScoreByBingQu(HttpServletRequest request,HttpServletResponse response){
       /* String startStr = request.getParameter("start");
        String plimitStr = request.getParameter("plimit");
        Integer start = Integer.valueOf(startStr);
        Integer plimit = Integer.valueOf(plimitStr);*/
        HashMap qryMapUser = new HashMap();
        /*qryMapUser.put("start",start);
        qryMapUser.put("plimit",plimit);*/
        /*qryMapUser.put("islimit",0);
        List userList = statisticsService.getUsers(qryMapUser);*/
        HashMap qryMapUserCourse = new HashMap();
        /*qryMapUserCourse.put("userlist",userList);*/
        HashMap qryMapUserScore = new HashMap();
        /*qryMapUserScore.put("userlist",userList);*/
        List result = allStatisticsService.parseMemberScoreByBQWithList(allStatisticsService.parseMemberScore(qryMapUserScore, qryMapUserCourse));
        response.setHeader("Transfer-Encoding","chunked");
        response.setCharacterEncoding("utf-8");
        response.setHeader("contentType", "text/html; charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        return JSON.toJSONString(result);
    }
}
