package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAllstatisticsService;
import service.IStatisticsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by chenqiong on 2018/11/8.
 */

@Controller
@RequestMapping("excel")
public class StudentScoreExcelController {

    @Autowired
    public IAllstatisticsService allstatisticsService;

    @RequestMapping("getScoreByGradeExcel")
    public String getScoreByGradeExcel(HttpServletRequest reques,HttpServletResponse response){
        HashMap qryscoreMap = new HashMap();
        HashMap qrycourseMap = new HashMap();
        List membersScoreList = allstatisticsService.parseMemberScoreWithList(qryscoreMap,qrycourseMap);
        return "";
    }

    @RequestMapping("getScoreByBingQuExcel")
    public String getScoreByBingQuExcel(HttpServletRequest reques,HttpServletResponse response){
        HashMap qryscoreMap = new HashMap();
        HashMap qrycourseMap = new HashMap();
        List membersScoreList = allstatisticsService.parseMemberScoreByBQWithList(allstatisticsService.parseMemberScore(qryscoreMap, qrycourseMap));
        return "";
    }


}
