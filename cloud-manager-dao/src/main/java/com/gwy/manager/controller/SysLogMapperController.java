package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.SysLog;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.SysLogMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("insert")
    int insert(@RequestBody SysLog record) {
        return sysLogMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    SysLog selectByPrimaryKey(@RequestParam("id") Integer id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @RequestMapping("selectAll")
    List<SysLog> selectAll() {
        return sysLogMapper.selectAll();
    }

    @RequestMapping("selectDataExplainAndCount")
    List<Map<String, Object>> selectDataExplainAndCount() {
        return sysLogMapper.selectDataExplainAndCount();
    }

    @RequestMapping("selectByType")
    List<SysLog> selectByType(@RequestParam("type") String type) {
        return sysLogMapper.selectByType(type);
    }

    @RequestMapping("selectByInterval")
    List<SysLog> selectByInterval(@RequestBody Date beginTime, @RequestBody Date endTime,@RequestParam("type") String type) {
        return sysLogMapper.selectByInterval(beginTime, endTime, type);
    }

    @RequestMapping("deleteByPrimaryKeys")
    int deleteByPrimaryKeys(@RequestBody List<Integer> ids) {
        return sysLogMapper.deleteByPrimaryKeys(ids);
    }

    @RequestMapping("selectLogsInfo")
    List<Map<Date, Integer>> selectLogsInfo() {
        return sysLogMapper.selectLogsInfo();
    }

    @RequestMapping("selectLogInfo")
    List<Map<String, Integer>> selectLogInfo() {
        return sysLogMapper.selectLogInfo();
    }
}