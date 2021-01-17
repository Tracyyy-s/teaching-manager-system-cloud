package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("SysLogMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface SysLogInvoker {

    @PostMapping("insert")
    int insert(SysLog record);

    @PostMapping("selectByPrimaryKey")
    SysLog selectByPrimaryKey(Integer id);

    @PostMapping("selectAll")
    List<SysLog> selectAll();

    @PostMapping("selectDataExplainAndCount")
    List<Map<String, Object>> selectDataExplainAndCount();

    @PostMapping("selectByType")
    List<SysLog> selectByType(String type);

    @PostMapping("selectByInterval")
    List<SysLog> selectByInterval(Date beginTime, Date endTime, String type);

    @PostMapping("deleteByPrimaryKeys")
    int deleteByPrimaryKeys(List<Integer> ids);

    @PostMapping("selectLogsInfo")
    List<Map<Date, Integer>> selectLogsInfo();

    @PostMapping("selectLogInfo")
    List<Map<String, Integer>> selectLogInfo();
}