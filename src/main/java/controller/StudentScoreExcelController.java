package controller;



import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAllstatisticsService;
import service.IStatisticsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
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
    public String getScoreByGradeExcel(HttpServletRequest reques,HttpServletResponse response){
       /* HashMap qryscoreMap = new HashMap();
        HashMap qrycourseMap = new HashMap();
        List membersScoreList = allstatisticsService.parseMemberScoreWithList(qryscoreMap,qrycourseMap);*/

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


        row = sheet.createRow((int) 2);
        cell = row.createCell((short) 0);
        cell.setCellValue("");
        cell = row.createCell((short) 1);
        cell.setCellValue("");
        cell = row.createCell((short) 2);
        cell.setCellValue("层级");
        cell = row.createCell((short) 3);
        cell.setCellValue("培训次数");
        cell = row.createCell((short) 4);
        cell.setCellValue("得分");
        cell = row.createCell((short) 5);
        cell.setCellValue("评定");
        cell = row.createCell((short) 6);
        cell.setCellValue("专业类型");
        cell = row.createCell((short) 7);
        cell.setCellValue("培训次数");
        cell = row.createCell((short) 8);
        cell.setCellValue("得分");
        cell = row.createCell((short) 9);
        cell.setCellValue("评定");
        cell = row.createCell((short) 10);
        cell.setCellValue("合格标准");
        cell = row.createCell((short) 11);
        cell.setCellValue("得分");
        cell = row.createCell((short) 12);
        cell.setCellValue("评定");

/*
        row = sheet.createRow((int) 3);
        cell = row.createCell((short) 0);
        cell.setCellValue("");
        cell = row.createCell((short) 1);
        cell.setCellValue("");
        cell = row.createCell((short) 2);
        cell.setCellValue("层级");
        cell = row.createCell((short) 3);
        cell.setCellValue("培训次数");
        cell = row.createCell((short) 4);
        cell.setCellValue("得分");
        cell = row.createCell((short) 5);
        cell.setCellValue("评定");
        cell = row.createCell((short) 6);
        cell.setCellValue("专业类型");
        cell = row.createCell((short) 7);
        cell.setCellValue("培训次数");
        cell = row.createCell((short) 8);
        cell.setCellValue("得分");
        cell = row.createCell((short) 9);
        cell.setCellValue("评定");
        cell = row.createCell((short) 10);
        cell.setCellValue("合格标准");
        cell = row.createCell((short) 11);
        cell.setCellValue("得分");
        cell = row.createCell((short) 12);
        cell.setCellValue("评定");
*/
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
    public String getScoreByBingQuExcel(HttpServletRequest reques,HttpServletResponse response){
        HashMap qryscoreMap = new HashMap();
        HashMap qrycourseMap = new HashMap();
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
        row = sheet.createRow((int) 1);
        cell = row.createCell((short) 0);
        cell.setCellValue("N1");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 1);
        cell.setCellValue("N2");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 2);
        cell.setCellValue("N3");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 3);
        cell.setCellValue("N4");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 4);
        cell.setCellValue("总分合格率");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 5);
        cell.setCellValue("康复小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 6);
        cell.setCellValue("糖尿病小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 7);
        cell.setCellValue("静脉小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 8);
        cell.setCellValue("肿瘤小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 9);
        cell.setCellValue("危重小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 10);
        cell.setCellValue("营养小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 11);
        cell.setCellValue("应急小组");
        cell.setCellStyle(styleOrange);
        cell = row.createCell((short) 12);
        cell.setCellValue("专业组合格率");
        cell.setCellStyle(styleOrange);

        for(int i=0;i<membersScoreList.size();i++){
            Map map = (Map) membersScoreList.get(i);
            row = sheet.createRow((int) i + 1);
            cell = row.createCell((short) 0);
            cell.setCellValue((String)map.get("bingqu"));
            cell = row.createCell((short) 1);
            cell.setCellValue((String)map.get("bingqu"));
            cell = row.createCell((short) 2);
            cell.setCellValue((Integer)map.get("n1num")+"/"+(Integer)map.get("zn1num"));
            cell = row.createCell((short) 3);
            cell.setCellValue((Integer)map.get("n2num")+"/"+(Integer)map.get("zn2num"));
            cell = row.createCell((short) 4);
            cell.setCellValue((Integer)map.get("n3num")+"/"+(Integer)map.get("zn3num"));
            cell = row.createCell((short) 5);
            cell.setCellValue((Integer)map.get("n4num")+"/"+(Integer)map.get("zn4num"));
            cell = row.createCell((short) 6);
            cell.setCellValue("总分合格率");
            cell = row.createCell((short) 7);
            cell.setCellValue((Integer)map.get("kfnum")+"/"+(Integer)map.get("zkfnum"));
            cell = row.createCell((short) 8);
            cell.setCellValue((Integer)map.get("tnbnum")+"/"+(Integer)map.get("ztnbnum"));
            cell = row.createCell((short) 9);
            cell.setCellValue((Integer)map.get("jmnum")+"/"+(Integer)map.get("zjmnum"));
            cell = row.createCell((short) 10);
            cell.setCellValue((Integer)map.get("zlnum")+"/"+(Integer)map.get("zzlnum"));
            cell = row.createCell((short) 11);
            cell.setCellValue((Integer)map.get("yynum")+"/"+(Integer)map.get("zyynum"));
            cell = row.createCell((short) 12);
            cell.setCellValue((Integer)map.get("wznum")+"/"+(Integer)map.get("zwznum"));
            cell = row.createCell((short) 13);
            cell.setCellValue((Integer)map.get("yjnum")+"/"+(Integer)map.get("yjnum"));
            cell = row.createCell((short) 14);
            cell.setCellValue("专业组合格率");

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
