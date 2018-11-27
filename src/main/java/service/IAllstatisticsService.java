package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqiong on 2018/11/14.
 */
public interface IAllstatisticsService {

    List parseMemberScoreByBQList(List list);

    List parseMemberScoreByBQWithList(Map<String,Object> membersScore);

    List parseMemberScoreWithList(HashMap qryscoreMap, HashMap qrycourseMap);

    Map<String,Object> parseMemberScore(HashMap qryscoreMap, HashMap qrycourseMap);

}
