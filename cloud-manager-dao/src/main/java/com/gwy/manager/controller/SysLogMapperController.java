package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.SysLog;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.SysLogMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("SysLogMapperController")
public class SysLogMapperController {
    @Resource
    SysLogMapper sysLogMapper;

    @PostMapping("insert")
    int insert(SysLog record){
        return sysLogMapper.insert(record);
    }
    @PostMapping("selectByPrimaryKey")
    SysLog selectByPrimaryKey(Integer id){
        return sysLogMapper.selectByPrimaryKey(id);
    }
    @PostMapping("selectAll")
    List<SysLog> selectAll(){
        return sysLogMapper.selectAll();
    }
    @PostMapping("selectDataExplainAndCount")
    List<Map<String, Object>> selectDataExplainAndCount(){
        return sysLogMapper.selectDataExplainAndCount();
    }
    @PostMapping("selectByType")
    List<SysLog> selectByType(String type){
        return sysLogMapper.selectByType(type);
    }
    @PostMapping("selectByInterval")
    List<SysLog> selectByInterval(Date beginTime, Date endTime, String type){
        return sysLogMapper.selectByInterval(beginTime,endTime,type);
    }
    @PostMapping("deleteByPrimaryKeys")
    int deleteByPrimaryKeys( List<Integer> ids){
        return sysLogMapper.deleteByPrimaryKeys(ids);
    }

    @PostMapping("selectLogsInfo")
    List<Map<Date, Integer>> selectLogsInfo(){
        return sysLogMapper.selectLogsInfo();
    }
    @PostMapping("selectLogInfo")
    List<Map<String, Integer>> selectLogInfo(){
        return sysLogMapper.selectLogInfo();
    }
}