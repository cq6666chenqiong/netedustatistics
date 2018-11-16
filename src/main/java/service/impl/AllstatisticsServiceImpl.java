package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IAllstatisticsService;
import service.IStatisticsService;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by chenqiong on 2018/11/14.
 */
@Service
public class AllstatisticsServiceImpl implements IAllstatisticsService {

    @Autowired
    public IStatisticsService statisticsService;

    public List parseMemberScoreByBQWithList(Map<String,Object> membersScore){
        Set<String> bingquSet = new HashSet<String>();
        bingquSet.add("80");
        bingquSet.add("160");
        List result = new ArrayList();

        Iterator<String> itbingqu = bingquSet.iterator();
        Iterator<String> it = membersScore.keySet().iterator();
        while(itbingqu.hasNext()) {
            String bingqu = itbingqu.next();
            int n1num = 0;
            int n2num = 0;
            int n3num = 0;
            int n4num = 0;
            int kfnum = 0;  //康复
            int tnbnum = 0; //糖尿病
            int jmnum = 0;  //静脉
            int zlnum = 0;  //肿瘤
            int yynum = 0;  //营养
            int wznum = 0;  //危重
            int yjnum = 0;  //应急

            int zn1num = 0;
            int zn2num = 0;
            int zn3num = 0;
            int zn4num = 0;
            int zkfnum = 0;  //康复
            int ztnbnum = 0; //糖尿病
            int zjmnum = 0;  //静脉
            int zzlnum = 0;  //肿瘤
            int zyynum = 0;  //营养
            int zwznum = 0;  //危重
            int zyjnum = 0;  //应急

            Map bingquMap = new HashMap();
            bingquMap.put("bingqu",bingqu);

            bingquMap.put("n1num",n1num);
            bingquMap.put("n2num",n2num);
            bingquMap.put("n3num",n3num);
            bingquMap.put("n4num",n4num);
            bingquMap.put("kfnum",kfnum);
            bingquMap.put("tnbnum",tnbnum);
            bingquMap.put("jmnum",jmnum);
            bingquMap.put("zlnum",zlnum);
            bingquMap.put("yynum",yynum);
            bingquMap.put("wznum",wznum);
            bingquMap.put("yjnum",yjnum);

            bingquMap.put("zn1num",zn1num);
            bingquMap.put("zn2num",zn2num);
            bingquMap.put("zn3num",zn3num);
            bingquMap.put("zn4num",zn4num);
            bingquMap.put("zkfnum",zkfnum);
            bingquMap.put("ztnbnum",ztnbnum);
            bingquMap.put("zjmnum",zjmnum);
            bingquMap.put("zzlnum",zzlnum);
            bingquMap.put("zyynum",zyynum);
            bingquMap.put("zwznum",zwznum);
            bingquMap.put("zyjnum",zyjnum);


            while (it.hasNext()) {
                String userId = it.next();
                Map user = (Map) membersScore.get(userId);
                String cengji = (String) user.get("cengji");
                String zhuanye = (String) user.get("zhuanye");
                String userbingqu = (String) user.get("bingqu");
                if (!bingqu.equals(userbingqu)) {
                    continue;
                }
                if (cengji.equals("1")) {
                    bingquMap.put("zn1num", (Integer) bingquMap.get("zn1num") + 1);
                    if (user.get("hasnopasscj").equals("0")) {
                        bingquMap.put("n1num", (Integer) bingquMap.get("n1num") + 1);
                    }
                }
                if (cengji.equals("2")) {
                    bingquMap.put("zn2num", (Integer) bingquMap.get("zn2num") + 1);
                    if (user.get("hasnopasscj").equals("0")) {
                        bingquMap.put("n2num", (Integer) bingquMap.get("n2num") + 1);
                    }
                }
                if (cengji.equals("3")) {
                    bingquMap.put("zn3num", (Integer) bingquMap.get("zn3num") + 1);
                    if (user.get("hasnopasscj").equals("0")) {
                        bingquMap.put("n3num", (Integer) bingquMap.get("n3num") + 1);
                    }
                }
                if (cengji.equals("4")) {
                    bingquMap.put("zn4num", (Integer) bingquMap.get("zn4num") + 1);

                    if (user.get("hasnopasscj").equals("0")) {
                        bingquMap.put("n4num", (Integer) bingquMap.get("n4num") + 1);
                    }
                }

                if (zhuanye.equals("康复小组")) {
                    bingquMap.put("zkfnum", (Integer) bingquMap.get("zkfnum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("kfnum", (Integer) bingquMap.get("kfnum") + 1);
                    }
                }
                if (zhuanye.equals("糖尿病课程")) {
                    bingquMap.put("ztnbnum", (Integer) bingquMap.get("ztnbnum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("tnbnum", (Integer) bingquMap.get("tnbnum") + 1);
                    }
                }
                if (zhuanye.equals("静脉课程")) {
                    bingquMap.put("zjmnum", (Integer) bingquMap.get("zjmnum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("jmnum", (Integer) bingquMap.get("jmnum") + 1);
                    }
                }
                if (zhuanye.equals("肿瘤课程")) {
                    bingquMap.put("zzlnum", (Integer) bingquMap.get("zzlnum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("zlnum", (Integer) bingquMap.get("zlnum") + 1);
                    }
                }
                if (zhuanye.equals("营养课程")) {
                    bingquMap.put("zyynum", (Integer) bingquMap.get("zyynum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("yynum", (Integer) bingquMap.get("yynum") + 1);
                    }
                }
                if (zhuanye.equals("危重小组")) {
                    bingquMap.put("zwznum", (Integer) bingquMap.get("zwznum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("wznum", (Integer) bingquMap.get("wznum") + 1);
                    }
                }
                if (zhuanye.equals("应急小组")) {
                    bingquMap.put("zyjnum", (Integer) bingquMap.get("zyjnum") + 1);
                    if (user.get("hasnopasszy").equals("0")) {
                        bingquMap.put("yjnum", (Integer) bingquMap.get("yjnum") + 1);
                    }
                }
            }
            result.add(bingquMap);
        }

        return result;
    }

    public List parseMemberScoreWithList(HashMap qryscoreMap,HashMap qrycourseMap){
        Map<String,Object> map = parseMemberScore(qryscoreMap,qrycourseMap);
        List list = new ArrayList();
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            Object o = map.get(key);
            list.add(o);
        }
        return list;
    }

    public Map<String,Object> parseMemberScore(HashMap qryscoreMap,HashMap qrycourseMap){
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
        Map<String,Object> membersScore = new HashMap<String,Object>();
        List<Map<String,String>> menberscorelist = statisticsService.getAllStudentsScore(qryscoreMap);

        //计算员工所得分数
        for(int i=0;i<menberscorelist.size();i++){

            Map<String,String> map = menberscorelist.get(i);
            String userId = map.get("userid");       //用户id
            String nickname =  map.get("nickname");  //工号
            String truename = map.get("truename");   //姓名
            String cengji = map.get("cengji");
            String zhuanye = map.get("zhuanye");
            String score = map.get("score");
            String bingqu = map.get("bingqu");
            if(cengji == null||cengji.equals("")){
                continue;
            }
            if(score == null && membersScore.keySet().contains(userId)){
                continue;
            }else if(score == null && !membersScore.keySet().contains(userId)){
                Map user = new HashMap();
                user.put("userId",userId);
                user.put("userNo",nickname);
                user.put("name",truename);

                user.put("cengji",cengji);
                user.put("zhuanye",zhuanye);
                //user.put("year",year);
                user.put("coursIds","");
                user.put("bingqu",bingqu);

                user.put("hasnopasscj",0);
                user.put("hasnopasszy",0);

                user.put("cjscore",new BigDecimal(0));
                user.put("cjcoursenum",0);
                user.put("zyscore",new BigDecimal(0));
                user.put("zycoursenum",0);

                membersScore.put(userId,user);

                continue;
            }

            String courseId = map.get("courseId");

            String courseType = map.get("courseType");
            String courseName = map.get("courseName");
            BigDecimal scorefen = new BigDecimal(score);

            if(membersScore.keySet().contains(userId)){//
                Map user = (Map)membersScore.get(userId);
                String courseIds = (String)user.get("coursIds");
                if(courseIds.contains(courseId)){
                    if(courseType == null){
                        BigDecimal zs = (BigDecimal)user.get("zyscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("zyscore",zs);
                    }else if(Integer.valueOf(cengji) == Integer.valueOf(courseType)){
                        BigDecimal zs = (BigDecimal)user.get("cjscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("cjscore",zs);
                    }else{
                        BigDecimal zs = (BigDecimal)user.get("zyscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("zyscore",zs);
                    }
                    user.put("coursIds",courseIds);
                }else{
                    courseIds = courseIds + courseId + ",";
                    if(courseType == null){
                        int num = (Integer)user.get("zycoursenum");
                        num = num + 1;
                        BigDecimal zs = (BigDecimal)user.get("zyscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("zyscore",zs);
                        user.put("zycoursenum",num);
                    }else if(Integer.valueOf(cengji) == Integer.valueOf(courseType)){
                        int num = (Integer)user.get("cjcoursenum");
                        num = num + 1;
                        BigDecimal zs = (BigDecimal)user.get("cjscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("cjscore",zs);
                        user.put("cjcoursenum",num);
                    }else{
                        int num = (Integer)user.get("zycoursenum");
                        num = num + 1;
                        BigDecimal zs = (BigDecimal)user.get("zyscore");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("zyscore",zs);
                        user.put("zycoursenum",num);
                    }
                    user.put("coursIds",courseIds);
                }
            }else{//
                if(cengji == null||cengji.equals("")){
                    continue;
                }
                Map user = new HashMap();
                user.put("userId",userId);
                user.put("userNo",nickname);
                user.put("name",truename);

                user.put("cengji",cengji);

                user.put("zhuanye",zhuanye);

                user.put("coursIds",courseId+",");
                user.put("bingqu",bingqu);
                user.put("hasnopasscj",0);
                user.put("hasnopasszy",0);

                Map<String,Integer> scoreMap = new HashMap<String,Integer>();

                if(courseType == null){
                    user.put("cjscore",new BigDecimal(0));
                    user.put("cjcoursenum",0);
                    user.put("zyscore",scorefen);
                    user.put("zycoursenum",1);
                }else if(Integer.valueOf(cengji) == Integer.valueOf(courseType)){
                    user.put("cjscore",scorefen);
                    user.put("cjcoursenum",1);
                    user.put("zyscore",0);
                    user.put("zycoursenum",0);
                }else{
                    user.put("cjscore",new BigDecimal(0));
                    user.put("cjcoursenum",0);
                    user.put("zyscore",scorefen);
                    user.put("zycoursenum",1);
                }
                membersScore.put(userId,user);
            }


        }
        //检测员工是否有未修的课程
        List<Map<String,String>> menbercourselist = statisticsService.getAllStudentsCourse(qrycourseMap);
        for(int i=0;i<menbercourselist.size();i++){
            Map<String,String> map = menbercourselist.get(0);
            String userId = map.get("userId");
            String courseId = map.get("courseId");
            String createdTime = map.get("createdTime");
            String courseType = map.get("courseType");
            Map user = (Map)membersScore.get(userId);
            if(!((String)user.get("coursIds")).contains(courseId)){
                if(Integer.valueOf(courseType)<=4){
                    user.put("hasnopasscj",1);
                }else{
                    user.put("hasnopasszy",1);
                }
            }
        }
        Iterator<String> it = membersScore.keySet().iterator();
        while(it.hasNext()){
            String userId = it.next();
            Map user = (Map) membersScore.get(userId);
            BigDecimal cjscore = (BigDecimal)membersScore.get("cjscore");
            BigDecimal zyscore = (BigDecimal)membersScore.get("zyscore");
            BigDecimal zf = new BigDecimal(0);
            if(zyscore != null){
                zf = cjscore.add(zyscore).setScale(2);
            }

            if(zf.compareTo(new BigDecimal(13)) >= 0){
                user.put("hasnopasszf",0);
            }else{
                user.put("hasnopasszf",1);
            }
        }
        return membersScore;
    }
}
