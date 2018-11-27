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
import util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
        String year = request.getParameter("year");
        String name = request.getParameter("name");
        String cengji = request.getParameter("cengji");


        String startTime = null;
        String endTime = null;
        if(year == null || year.equals("")){
            Calendar cal = Calendar.getInstance();
            String y = cal.get(Calendar.YEAR) + "";
            System.out.println("year="+y);
            startTime = DateUtil.date2TimeStamp(y + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(y + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        }else{
            startTime = DateUtil.date2TimeStamp(year + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(year + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        }

        String startStr = request.getParameter("start");
        String plimitStr = request.getParameter("plimit");
        Integer start = Integer.valueOf(startStr);
        Integer plimit = Integer.valueOf(plimitStr);
        HashMap qryMapUser = new HashMap();
        qryMapUser.put("start",(start-1)*plimit);
        qryMapUser.put("plimit",plimit);
        qryMapUser.put("islimit",1);
        qryMapUser.put("name",name);
        qryMapUser.put("cengji",cengji);
        List userList = statisticsService.getUsers(qryMapUser);
        HashMap qryMapUserCourse = new HashMap();
        qryMapUserCourse.put("isMember",1);
        qryMapUserCourse.put("userlist",userList);
        qryMapUserCourse.put("name",name);
        qryMapUserCourse.put("startTime",startTime);
        qryMapUserCourse.put("endTime",endTime);
        qryMapUserCourse.put("cengji",cengji);
        HashMap qryMapUserScore = new HashMap();
        qryMapUserScore.put("isMember",1);
        qryMapUserScore.put("userlist",userList);
        qryMapUserCourse.put("year",year);
        qryMapUserCourse.put("name",name);
        qryMapUserCourse.put("cengji",cengji);
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
        HashMap qryMapUserCourse = new HashMap();
        HashMap qryMapUserScore = new HashMap();
        List list = allStatisticsService.parseMemberScoreByBQWithList(allStatisticsService.parseMemberScore(qryMapUserScore, qryMapUserCourse));
        List result = allStatisticsService.parseMemberScoreByBQList(list);
        //response.setHeader("Transfer-Encoding","chunked");
        response.setCharacterEncoding("utf-8");
        response.setHeader("contentType", "text/html; charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        return JSON.toJSONString(result);
    }
}
