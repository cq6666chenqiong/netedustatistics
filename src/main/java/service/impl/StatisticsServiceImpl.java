package service.impl;

import dao.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IStatisticsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqiong on 2018/11/8.
 */

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    public StatisticsMapper statisticsMapper;


    @Override
    public List<Map<String, String>> getAllStudentsScore(HashMap qryMap) {
        return statisticsMapper.getAllStudentsScore(qryMap);
    }

    @Override
    public List<Map<String, String>> getAllStudentsCourse(HashMap qryMap) {
        return statisticsMapper.getAllStudentsCourse(qryMap);
    }

    @Override
    public List<Map<String, String>> getUsers(HashMap qryMap) {
        return statisticsMapper.getUsers(qryMap);
    }
/*
@Override
@DataSourceChange(slave = false)
 */
}
