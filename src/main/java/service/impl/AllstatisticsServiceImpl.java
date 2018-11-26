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

    public List parseMemberScoreByBQList(List list){
        List result = new ArrayList();

        Integer n1num = 0;
        Integer zn1num = 0;
        Integer n2num = 0;
        Integer zn2num = 0;
        Integer n3num = 0;
        Integer zn3num = 0;
        Integer n4num = 0;
        Integer zn4num = 0;
        Integer kfnum = 0;
        Integer zkfnum = 0;
        Integer tnbnum = 0;
        Integer ztnbnum = 0;
        Integer jmnum = 0;
        Integer zjmnum = 0;
        Integer zlnum = 0;
        Integer zzlnum = 0;
        Integer yynum = 0;
        Integer zyynum = 0;
        Integer wznum = 0;
        Integer zwznum = 0;
        Integer yjnum = 0;
        Integer zyjnum = 0;

        for(int i=0;i<list.size();i++){
            Map map = (Map) list.get(i);
            Map smap = new HashMap();
            smap.put("bingqu",(String)map.get("bingqu"));

            smap.put("n1",(Integer)map.get("n1num")+"/"+(Integer)map.get("zn1num"));
            n1num = n1num + (Integer)map.get("n1num");
            zn1num = zn1num + (Integer)map.get("zn1num");

            smap.put("n2", (Integer) map.get("n2num") + "/" + (Integer) map.get("zn2num"));
            n2num = n2num + (Integer)map.get("n2num");
            zn2num = zn2num + (Integer)map.get("zn2num");

            smap.put("n3", (Integer) map.get("n3num") + "/" + (Integer) map.get("zn3num"));
            n3num = n3num + (Integer)map.get("n3num");
            zn3num = zn3num + (Integer)map.get("zn3num");

            smap.put("n4", (Integer) map.get("n4num") + "/" + (Integer) map.get("zn4num"));
            n4num = n4num + (Integer)map.get("n4num");
            zn4num = zn4num + (Integer)map.get("zn4num");

            int cjp = (Integer)map.get("n1num") + (Integer)map.get("n2num") + (Integer)map.get("n3num") + (Integer)map.get("n4num");
            int cj = (Integer)map.get("zn1num") + (Integer)map.get("zn2num") + (Integer)map.get("zn3num") + (Integer)map.get("zn4num");
            if(cj == 0){
                smap.put("zn", "0%");
            }else{
                smap.put("zn", BigDecimal.valueOf(cjp).divide(BigDecimal.valueOf(cj)).setScale(2) + "");
            }

            smap.put("kf", (Integer) map.get("kfnum") + "/" + (Integer) map.get("zkfnum"));
            kfnum = kfnum + (Integer)map.get("kfnum");
            zkfnum = zkfnum + (Integer)map.get("zkfnum");

            smap.put("tnb", (Integer) map.get("tnbnum") + "/" + (Integer) map.get("ztnbnum"));
            tnbnum = tnbnum + (Integer)map.get("tnbnum");
            ztnbnum = ztnbnum + (Integer)map.get("ztnbnum");

            smap.put("jm", (Integer) map.get("jmnum") + "/" + (Integer) map.get("zjmnum"));
            jmnum = jmnum + (Integer)map.get("jmnum");
            zjmnum = zjmnum + (Integer)map.get("zjmnum");

            smap.put("zl", (Integer) map.get("zlnum") + "/" + (Integer) map.get("zzlnum"));
            zlnum = zlnum + (Integer)map.get("zlnum");
            zzlnum = zzlnum + (Integer)map.get("zzlnum");

            smap.put("yy", (Integer) map.get("yynum") + "/" + (Integer) map.get("zyynum"));
            yynum = yynum + (Integer)map.get("yynum");
            zyynum = zyynum + (Integer)map.get("zyynum");

            smap.put("wz", (Integer) map.get("wznum") + "/" + (Integer) map.get("zwznum"));
            wznum = wznum + (Integer)map.get("wznum");
            zwznum = zwznum + (Integer)map.get("zwznum");

            smap.put("yj", (Integer) map.get("yjnum") + "/" + (Integer) map.get("zyjnum"));
            yjnum = yjnum + (Integer)map.get("yjnum");
            zyjnum = zyjnum + (Integer)map.get("zyjnum");

            int znp = (Integer)map.get("kfnum") + (Integer)map.get("tnbnum") + (Integer)map.get("jmnum") + (Integer)map.get("zlnum") + (Integer)map.get("yynum") + (Integer)map.get("wznum") + (Integer)map.get("yjnum");
            int zn = (Integer)map.get("zkfnum") + (Integer)map.get("ztnbnum") + (Integer)map.get("zjmnum") + (Integer)map.get("zzlnum") + (Integer)map.get("zyynum") + (Integer)map.get("zwznum") + (Integer)map.get("zyjnum");
            if(zn == 0){
                smap.put("zz","0%");
            }else{
                smap.put("zz",BigDecimal.valueOf(znp).divide(BigDecimal.valueOf(zn)).setScale(2) + "%");
            }

            result.add(smap);
        }


        Map smap = new HashMap();
        smap.put("bingqu","合计");

        if(zn1num == 0){
            smap.put("n1", "0%");
        }else{
            smap.put("n1", BigDecimal.valueOf(n1num).divide(BigDecimal.valueOf(zn1num)).setScale(2) + "%");
        }

        if(n2num == 0){
            smap.put("n2", "0%");
        }else{
            smap.put("n2", BigDecimal.valueOf(n2num).divide(BigDecimal.valueOf(zn2num)).setScale(2) + "%");
        }

        if(n3num == 0){
            smap.put("n3", "0%");
        }else{
            smap.put("n3", BigDecimal.valueOf(n3num).divide(BigDecimal.valueOf(zn3num)).setScale(2) + "%");
        }

        if(n4num == 0){
            smap.put("n4", "0%");
        }else{
            smap.put("n4", BigDecimal.valueOf(n4num).divide(BigDecimal.valueOf(zn4num)).setScale(2) + "%");
        }

        int cjp = n1num + n2num + n3num + n4num;
        int cj = zn1num + zn2num + zn3num + zn4num;
        if(cj == 0){
            smap.put("zn", "0%");
        }else{
            smap.put("zn", BigDecimal.valueOf(cjp).divide(BigDecimal.valueOf(cj)).setScale(2) + "%");
        }

        if(kfnum == 0){
            smap.put("kf", "0%");
        }else{
            smap.put("kf", BigDecimal.valueOf(kfnum).divide(BigDecimal.valueOf(zkfnum)).setScale(2) + "%");
        }

        if(tnbnum == 0){
            smap.put("tnb", "0%");
        }else{
            smap.put("tnb", BigDecimal.valueOf(tnbnum).divide(BigDecimal.valueOf(ztnbnum)).setScale(2) + "%");
        }

        if(jmnum == 0){
            smap.put("jm", "0%");
        }else{
            smap.put("jm", BigDecimal.valueOf(jmnum).divide(BigDecimal.valueOf(zjmnum)).setScale(2) + "%");
        }

        if(zlnum == 0){
            smap.put("zl", "0%");
        }else{
            smap.put("zl", BigDecimal.valueOf(zlnum).divide(BigDecimal.valueOf(zzlnum)).setScale(2) + "%");
        }

        if(yynum == 0){
            smap.put("yy", "0%");
        }else{
            smap.put("yy", BigDecimal.valueOf(yynum).divide(BigDecimal.valueOf(zyynum)).setScale(2) + "%");
        }

        if(wznum == 0){
            smap.put("wz", "0%");
        }else{
            smap.put("wz", BigDecimal.valueOf(wznum).divide(BigDecimal.valueOf(zwznum)).setScale(2) + "%");
        }

        if(yjnum == 0){
            smap.put("yj", "0%");
        }else{
            smap.put("yj", BigDecimal.valueOf(yjnum).divide(BigDecimal.valueOf(zyjnum)).setScale(2) + "%");
        }

        int znp = kfnum + tnbnum + jmnum + zlnum + yynum + wznum + yjnum;
        int zn = zkfnum + ztnbnum + zjmnum + zzlnum + zyynum + zwznum + zyjnum;
        if(zn == 0){
            smap.put("zz", "0%");
        }else{
            smap.put("zz", BigDecimal.valueOf(znp).divide(BigDecimal.valueOf(zn)).setScale(2) + "%");
        }

        return result;
    }

    public List parseMemberScoreByBQWithList(Map<String,Object> membersScore){
        Set<String> bingquSet = new HashSet<String>();
        List<Map> list = statisticsService.getDistinctBingqu();
        for(int i=0;i<list.size();i++){
            bingquSet.add(list.get(i).get("bingqu") == null ? "" : (String)list.get(i).get("bingqu"));
        }
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
            String courseType = map.get("courseType");
            String acourseType = map.get("acourseType") == null ? "-1" : map.get("acourseType");
            String scourseId = map.get("scourseId");
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


            String courseName = map.get("courseName");
            BigDecimal scorefen = new BigDecimal(score);

            if(membersScore.keySet().contains(userId)){//已记录用户
                Map user = (Map)membersScore.get(userId);
                String courseIds = (String)user.get("coursIds");
                if(courseId == null){
                    courseId = "";
                }

                if(scourseId.equals("-1")){//excel录入的成绩
                    if(acourseType.equals("0")){
                        int num = (Integer)user.get("cjcoursenum");
                        num = num + 1;
                        BigDecimal zs = new BigDecimal(user.get("cjscore") + "");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("cjscore",zs);
                        user.put("cjcoursenum",num);
                    }else{
                        int num = (Integer)user.get("zycoursenum");
                        num = num + 1;
                        BigDecimal zs = new BigDecimal(user.get("zyscore")+"");
                        zs = zs.add(scorefen).setScale(2);
                        user.put("zyscore",zs);
                        user.put("zycoursenum",num);
                    }
                }else{//在平台考试取得的成绩
                    if(courseIds.contains(courseId)){
                        if(courseType == null){
                            BigDecimal zs = new BigDecimal(user.get("zyscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("zyscore",zs);
                        }else if(Integer.valueOf(cengji) == Integer.valueOf(courseType)){
                            BigDecimal zs = new BigDecimal(user.get("cjscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("cjscore",zs);
                        }else{
                            BigDecimal zs = new BigDecimal(user.get("zyscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("zyscore",zs);
                        }
                        user.put("coursIds",courseIds);
                    }else{
                        courseIds = courseIds + courseId + ",";
                        if(courseType == null){
                            int num = (Integer)user.get("zycoursenum");
                            num = num + 1;
                            BigDecimal zs = new BigDecimal(user.get("zyscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("zyscore",zs);
                            user.put("zycoursenum",num);
                        }else if(Integer.valueOf(cengji) == Integer.valueOf(courseType)){
                            int num = (Integer)user.get("cjcoursenum");
                            num = num + 1;
                            BigDecimal zs = new BigDecimal(user.get("cjscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("cjscore",zs);
                            user.put("cjcoursenum",num);
                        }else{
                            int num = (Integer)user.get("zycoursenum");
                            num = num + 1;
                            BigDecimal zs = new BigDecimal(user.get("zyscore") + "");
                            zs = zs.add(scorefen).setScale(2);
                            user.put("zyscore",zs);
                            user.put("zycoursenum",num);
                        }
                        user.put("coursIds",courseIds);
                    }
                }


            }else{//没用记录用户
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
                user.put("cjscore",new BigDecimal(0));
                user.put("cjcoursenum",0);
                user.put("zyscore",scorefen);
                user.put("zycoursenum",1);



                if(scourseId.equals("-1")){
                    System.out.println("===================================================-1");
                    if(acourseType.equals("0")){
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
                }else{
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
            if(courseId == null) continue;
            String createdTime = map.get("createdTime");
            String courseType = map.get("courseType");
            Map user = (Map)membersScore.get(userId);
            String coursIds = user.get("coursIds") == null ? "---" :  (String)user.get("coursIds");
            if(!coursIds.contains(courseId)){
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
            BigDecimal cjscore = new BigDecimal(user.get("cjscore")+"");
            BigDecimal zyscore =new BigDecimal(user.get("zyscore")+"");
            BigDecimal zf = new BigDecimal(0);
            zf = cjscore.add(zyscore).setScale(2);
            user.put("zongfen",zf);
            if(zf.compareTo(new BigDecimal(13)) >= 0){
                user.put("hasnopasszf",0);
            }else{
                user.put("hasnopasszf",1);
            }
           // System.out.println(user.get("userNo")+"===="+user.get("coursIds"));
        }
        return membersScore;
    }
}
