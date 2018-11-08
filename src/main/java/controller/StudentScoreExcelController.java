package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IStatisticsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqiong on 2018/11/8.
 */

@Controller
@RequestMapping("excel")
public class StudentScoreExcelController {

    @Autowired
    public IStatisticsService statisticsService;

    @RequestMapping("getScoreByGradeExcel")
    public String getScoreByGradeExcel(){

        HashMap qryscoreMap = new HashMap();

        /**
         * 姓名
         * 工号
         * ===============层级培训
         * 层级
         * 培训次数
         * 得分
         * 评定
         * ===============专业培训
         * 专业类型
         * 培训次数
         * 得分评定
         * 得分
         * 评定
         * ===============年度总学分
         * 合格标准
         * 得分
         * 评定
         */

        List<Map<String,String>> menberscorelist = statisticsService.getAllStudentsScore(qryscoreMap);
        for(int i=0;i<menberscorelist.size();i++){
            Map<String,String> map = menberscorelist.get(0);
            String userId = map.get("userid");
            String nickname =  map.get("nickname");
            String truename = map.get("truename");
            String cengji = map.get("cengji");
            String zhuanye = map.get("zhuanye");
            String score = map.get("score");
            String year = map.get("year");
            String courseId = map.get("courseId");
            String courseType = map.get("courseType");
            String courseName = map.get("courseName");

        }
        HashMap qrycourseMap = new HashMap();
        List<Map<String,String>> menbercourselist = statisticsService.getAllStudentsCourse(qrycourseMap);
        for(int i=0;i<menbercourselist.size();i++){
            Map<String,String> map = menbercourselist.get(0);
            String userId = map.get("userId");
            String courseId = map.get("courseId");
            String createdTime = map.get("createdTime");

        }
        return "";
    }
}
