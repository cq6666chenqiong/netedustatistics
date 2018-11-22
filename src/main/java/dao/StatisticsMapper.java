package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqiong on 2018/11/8.
 */
public interface StatisticsMapper {


    List<Map<String,String>> getAllStudentsScore(HashMap qryMap);

    List<Map<String,String>> getAllStudentsCourse(HashMap qryMap);

    List<Map<String,String>> getUsers(HashMap qryMap);

    List<Map> getDistinctBingqu();
}
