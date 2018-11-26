package controller;



import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAllstatisticsService;
import service.IStatisticsService;
import util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenqiong on 2018/11/8.
 */

@Controller
@RequestMapping("/excel")
public class StudentScoreExcelController {

    @Autowired
    public IAllstatisticsService allstatisticsService;

    @RequestMapping("/getScoreByGradeExcel")
    public String getScoreByGradeExcel(HttpServletRequest request,HttpServletResponse response){

        String year = request.getParameter("year");

        String startTime = null;
        String endTime = null;
        if(year == null || year.equals("")){
            Calendar cal = Calendar.getInstance();
            String y = cal.get(Calendar.YEAR) + "";
            System.out.println("year="+y);
            startTime = DateUtil.date2TimeStamp(y + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(y + "-12-31 23:59:59","yyyy-MM-dd HH:mm:ss");
        }else{
            startTime = DateUtil.date2TimeStamp(year + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(year + "-12-31 23:59:59","yyyy-MM-dd HH:mm:ss");
        }


        String name = request.getParameter("name");
        String cengji = request.getParameter("cengji");

        HashMap qryscoreMap = new HashMap();
        qryscoreMap.put("year",year);
        qryscoreMap.put("name",name);
        qryscoreMap.put("cengji",cengji);
        HashMap qrycourseMap = new HashMap();
        qrycourseMap.put("startTime",startTime);
        qrycourseMap.put("endTime",endTime);
        qrycourseMap.put("name",name);
        qrycourseMap.put("cengji",cengji);
        List membersScoreList = allstatisticsService.parseMemberScoreWithList(qryscoreMap,qrycourseMap);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 8);//设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示

        HSSFCellStyle styleOrange = wb.createCellStyle();
        styleOrange.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleOrange.setFillForegroundColor(HSSFColor.ORANGE.index);
        styleOrange.setFont(font);

        HSSFCellStyle styleGreen = wb.createCellStyle();
        styleGreen.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleGreen.setFillForegroundColor(HSSFColor.GREEN.index);
        styleGreen.setFont(font);

        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setFillPattern(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFSheet sheet = wb.createSheet("按人分数统计");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("姓名");
        cell.setCellStyle(styleGreen);
        cell = row.createCell((short) 1);
        cell.setCellValue("工号");
        cell.setCellStyle(styleGreen);
        CellRangeAddress region0 = new CellRangeAddress(0, 0, (short) 2, (short) 5);
        sheet.addMergedRegion(region0);
        cell = row.createCell((short) 2);
        cell.setCellValue("层级培训");
        cell.setCellStyle(styleGreen);
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 6, (short) 9);
        sheet.addMergedRegion(region1);
        cell = row.createCell((short) 6);
        cell.setCellValue("专业培训");
        cell.setCellStyle(styleGreen);
        CellRangeAddress region2 = new CellRangeAddress(0, 0, (short) 10, (short) 12);
        sheet.addMergedRegion(region2);
        cell = row.createCell((short) 10);
        cell.setCellValue("年度总学分");
        cell.setCellStyle(styleGreen);


        row = sheet.createRow((int) 1);
        cell = row.createCell((short) 0);
        cell.setCellValue("");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 1);
        cell.setCellValue("");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 2);
        cell.setCellValue("层级");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 3);
        cell.setCellValue("培训次数");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 4);
        cell.setCellValue("得分");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 5);
        cell.setCellValue("评定");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 6);
        cell.setCellValue("专业类型");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 7);
        cell.setCellValue("培训次数");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 8);
        cell.setCellValue("得分");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 9);
        cell.setCellValue("评定");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 10);
        cell.setCellValue("合格标准");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 11);
        cell.setCellValue("得分");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 12);
        cell.setCellValue("评定");
        cell.setCellStyle(styleOrange);

        for(int i=0;i<membersScoreList.size();i++){
            Map userMap = (Map)membersScoreList.get(i);
            row = sheet.createRow((int) i+2);
            cell = row.createCell((short) 0);
            cell.setCellValue((String)userMap.get("name"));
            cell = row.createCell((short) 1);
            cell.setCellValue((String)userMap.get("userNo"));
            cell = row.createCell((short) 2);
            cell.setCellValue((String)userMap.get("cengji"));
            cell = row.createCell((short) 3);
            cell.setCellValue(userMap.get("cjcoursenum")==null?"0":(userMap.get("cjcoursenum")+""));  //培训次数
            cell = row.createCell((short) 4);
            cell.setCellValue((userMap.get("cjscore")+""));
            cell = row.createCell((short) 5);
            if(((userMap.get("hasnopasscj")+"")).equals("0")){
                cell.setCellValue("通过");  //评定
            }else{
                cell.setCellValue("未通过");  //评定
            }
            cell = row.createCell((short) 6);
            cell.setCellValue((String)userMap.get("zhuanye"));
            cell = row.createCell((short) 7);
            cell.setCellValue(userMap.get("zycoursenum")==null?"0":(userMap.get("zycoursenum")+""));  //培训次数
            cell = row.createCell((short) 8);
            cell.setCellValue((userMap.get("zyscore")+""));
            cell = row.createCell((short) 9);
            if(((userMap.get("hasnopasszy")+"")).equals("0")){
                cell.setCellValue("通过");  //评定
            }else{
                cell.setCellValue("未通过");  //评定
            }
            cell = row.createCell((short) 10);
            cell.setCellValue("大于13分");
            cell = row.createCell((short) 11);
            cell.setCellValue(new BigDecimal(userMap.get("cjscore")+"").add(new BigDecimal(userMap.get("zyscore") + "")).setScale(2) + "");
            cell = row.createCell((short) 12);
            if((userMap.get("hasnopasszf")+"").equals("0")&&(userMap.get("hasnopasszy")+"").equals("0")&&(userMap.get("hasnopasscj")+"").equals("0")){
                cell.setCellValue("通过");  //评定
            }else{
                cell.setCellValue("未通过");  //评定
            }
        }

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String fileName = "按人分数统计.xls";// 文件名
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping("getScoreByBingQuExcel")
    public String getScoreByBingQuExcel(HttpServletRequest request,HttpServletResponse response){
        String year = request.getParameter("year");
        String startTime = null;
        String endTime = null;
        if(year == null || year.equals("")){
            Date date = new Date();
            String y = date.getYear() + "";
            startTime = DateUtil.date2TimeStamp(y + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(y + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        }else{
            startTime = DateUtil.date2TimeStamp(year + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.date2TimeStamp(year + "-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        }


        String name = request.getParameter("name");
        String cengji = request.getParameter("cengji");

        HashMap qryscoreMap = new HashMap();
        qryscoreMap.put("year",year);
        HashMap qrycourseMap = new HashMap();
        qrycourseMap.put("startTime",startTime);
        qrycourseMap.put("endTime",endTime);
        List membersScoreList = allstatisticsService.parseMemberScoreByBQWithList(allstatisticsService.parseMemberScore(qryscoreMap, qrycourseMap));


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 8);//设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示

        HSSFCellStyle styleOrange = wb.createCellStyle();
        styleOrange.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleOrange.setFillForegroundColor(HSSFColor.ORANGE.index);
        styleOrange.setFont(font);

        HSSFCellStyle styleGreen = wb.createCellStyle();
        styleGreen.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleGreen.setFillForegroundColor(HSSFColor.GREEN.index);
        styleGreen.setFont(font);

        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setFillPattern(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        styleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFSheet sheet = wb.createSheet("按病区分数统计");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell = row.createCell((short) 0);
        cell = row.createCell((short) 0);
        cell.setCellValue("病区");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 1);
        cell.setCellValue("N1");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 2);
        cell.setCellValue("N2");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 3);
        cell.setCellValue("N3");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 4);
        cell.setCellValue("N4");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 5);
        cell.setCellValue("总分合格率");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 6);
        cell.setCellValue("康复小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 7);
        cell.setCellValue("糖尿病小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 8);
        cell.setCellValue("静脉小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 9);
        cell.setCellValue("肿瘤小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 10);
        cell.setCellValue("危重小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 11);
        cell.setCellValue("营养小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 12);
        cell.setCellValue("应急小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 13);
        cell.setCellValue("专业组合格率");
        cell.setCellStyle(styleOrange);

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


        for(int i=0;i<membersScoreList.size();i++){
            Map map = (Map) membersScoreList.get(i);
            row = sheet.createRow((int) i + 1);
            cell = row.createCell((short) 0);
            cell.setCellValue((String)map.get("bingqu"));
            cell = row.createCell((short) 1);
            cell.setCellValue((Integer)map.get("n1num")+"/"+(Integer)map.get("zn1num"));
            n1num = n1num + (Integer)map.get("n1num");
            zn1num = zn1num + (Integer)map.get("zn1num");
            cell = row.createCell((short) 2);
            cell.setCellValue((Integer)map.get("n2num")+"/"+(Integer)map.get("zn2num"));
            n2num = n2num + (Integer)map.get("n2num");
            zn2num = zn2num + (Integer)map.get("zn2num");
            cell = row.createCell((short) 3);
            cell.setCellValue((Integer)map.get("n3num")+"/"+(Integer)map.get("zn3num"));
            n3num = n3num + (Integer)map.get("n3num");
            zn3num = zn3num + (Integer)map.get("zn3num");
            cell = row.createCell((short) 4);
            cell.setCellValue((Integer)map.get("n4num")+"/"+(Integer)map.get("zn4num"));
            n4num = n4num + (Integer)map.get("n4num");
            zn4num = zn4num + (Integer)map.get("zn4num");
            cell = row.createCell((short) 5);
            int cjp = (Integer)map.get("n1num") + (Integer)map.get("n2num") + (Integer)map.get("n3num") + (Integer)map.get("n4num");
            int cj = (Integer)map.get("zn1num") + (Integer)map.get("zn2num") + (Integer)map.get("zn3num") + (Integer)map.get("zn4num");
            if(cj == 0){
                cell.setCellValue(0);
            }else{
                cell.setCellValue(BigDecimal.valueOf(cjp).divide(BigDecimal.valueOf(cj)).setScale(2) + "");
            }
            cell = row.createCell((short) 6);
            cell.setCellValue((Integer)map.get("kfnum")+"/"+(Integer)map.get("zkfnum"));
            kfnum = kfnum + (Integer)map.get("kfnum");
            zkfnum = zkfnum + (Integer)map.get("zkfnum");
            cell = row.createCell((short) 7);
            cell.setCellValue((Integer)map.get("tnbnum")+"/"+(Integer)map.get("ztnbnum"));
            tnbnum = tnbnum + (Integer)map.get("tnbnum");
            ztnbnum = ztnbnum + (Integer)map.get("ztnbnum");
            cell = row.createCell((short) 8);
            cell.setCellValue((Integer)map.get("jmnum")+"/"+(Integer)map.get("zjmnum"));
            jmnum = jmnum + (Integer)map.get("jmnum");
            zjmnum = zjmnum + (Integer)map.get("zjmnum");
            cell = row.createCell((short) 9);
            cell.setCellValue((Integer)map.get("zlnum")+"/"+(Integer)map.get("zzlnum"));
            zlnum = zlnum + (Integer)map.get("zlnum");
            zzlnum = zzlnum + (Integer)map.get("zzlnum");
            cell = row.createCell((short) 10);
            cell.setCellValue((Integer)map.get("yynum")+"/"+(Integer)map.get("zyynum"));
            yynum = yynum + (Integer)map.get("yynum");
            zyynum = zyynum + (Integer)map.get("zyynum");
            cell = row.createCell((short) 11);
            cell.setCellValue((Integer)map.get("wznum")+"/"+(Integer)map.get("zwznum"));
            wznum = wznum + (Integer)map.get("wznum");
            zwznum = zwznum + (Integer)map.get("zwznum");
            cell = row.createCell((short) 12);
            cell.setCellValue((Integer)map.get("yjnum")+"/"+(Integer)map.get("zyjnum"));
            yjnum = yjnum + (Integer)map.get("yjnum");
            zyjnum = zyjnum + (Integer)map.get("zyjnum");
            cell = row.createCell((short) 13);
            int znp = (Integer)map.get("kfnum") + (Integer)map.get("tnbnum") + (Integer)map.get("jmnum") + (Integer)map.get("zlnum") + (Integer)map.get("yynum") + (Integer)map.get("wznum") + (Integer)map.get("yjnum");
            int zn = (Integer)map.get("zkfnum") + (Integer)map.get("ztnbnum") + (Integer)map.get("zjmnum") + (Integer)map.get("zzlnum") + (Integer)map.get("zyynum") + (Integer)map.get("zwznum") + (Integer)map.get("zyjnum");
            cell = row.createCell((short) 14);
            if(zn == 0){
                cell.setCellValue(0);
            }else{
                cell.setCellValue(BigDecimal.valueOf(znp).divide(BigDecimal.valueOf(zn)).setScale(2) + "");
            }

        }

        int t = membersScoreList.size();
        row = sheet.createRow((int) t + 1);
        cell = row.createCell((short) 0);
        cell.setCellValue("合计");
        cell = row.createCell((short) 1);
        if(zn1num == 0){
            cell.setCellValue("0%");
        }else{
            cell.setCellValue(BigDecimal.valueOf(n1num).divide(BigDecimal.valueOf(zn1num)).setScale(2) + "%");
        }
        cell = row.createCell((short) 2);
        if(n2num == 0){
            cell.setCellValue("0%");
        }else{
            cell.setCellValue(BigDecimal.valueOf(n2num).divide(BigDecimal.valueOf(zn2num)).setScale(2) + "%");
        }
        cell = row.createCell((short) 3);
        if(n3num == 0){
            cell.setCellValue("0%");
        }else{
            cell.setCellValue(BigDecimal.valueOf(n3num).divide(BigDecimal.valueOf(zn3num)).setScale(2) + "%");
        }
        cell = row.createCell((short) 4);
        if(n4num == 0){
            cell.setCellValue("0%");
        }else{
            cell.setCellValue(BigDecimal.valueOf(n4num).divide(BigDecimal.valueOf(zn4num)).setScale(2) + "%");
        }
        cell = row.createCell((short) 5);
        int cjp = n1num + n2num + n3num + n4num;
        int cj = zn1num + zn2num + zn3num + zn4num;
        if(cj == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(cjp).divide(BigDecimal.valueOf(cj)).setScale(2) + "%");
        }
        cell = row.createCell((short) 6);
        if(kfnum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(kfnum).divide(BigDecimal.valueOf(zkfnum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 7);
        if(tnbnum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(tnbnum).divide(BigDecimal.valueOf(ztnbnum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 8);
        if(jmnum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(jmnum).divide(BigDecimal.valueOf(zjmnum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 9);
        if(zlnum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(zlnum).divide(BigDecimal.valueOf(zzlnum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 10);
        if(yynum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(yynum).divide(BigDecimal.valueOf(zyynum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 11);
        if(wznum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(wznum).divide(BigDecimal.valueOf(zwznum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 12);
        if(yjnum == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(yjnum).divide(BigDecimal.valueOf(zyjnum)).setScale(2) + "%");
        }
        cell = row.createCell((short) 13);
        int znp = kfnum + tnbnum + jmnum + zlnum + yynum + wznum + yjnum;
        int zn = zkfnum + ztnbnum + zjmnum + zzlnum + zyynum + zwznum + zyjnum;
        cell = row.createCell((short) 14);
        if(zn == 0){
            cell.setCellValue(0);
        }else{
            cell.setCellValue(BigDecimal.valueOf(znp).divide(BigDecimal.valueOf(zn)).setScale(2) + "%");
        }

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String fileName = "按病区分数统计.xls";// 文件名
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
