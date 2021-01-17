package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.SysLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("SysLogMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "34")
@Qualifier("webSysLogInvoker")
public interface SysLogInvoker {

    @PostMapping("insert")
    int insert(@RequestBody SysLog record);

    @PostMapping("selectByPrimaryKey")
    SysLog selectByPrimaryKey(@RequestParam("id") Integer id);

    @PostMapping("selectAll")
    List<SysLog> selectAll();

    @PostMapping("selectDataExplainAndCount")
    List<Map<String, Object>> selectDataExplainAndCount();

    @PostMapping("selectByType")
    List<SysLog> selectByType(@RequestParam("type") String type);

    @PostMapping("deleteByPrimaryKeys")
    int deleteByPrimaryKeys(@RequestBody List<Integer> ids);

    @PostMapping("selectLogsInfo")
    List<Map<Date, Integer>> selectLogsInfo();

    @PostMapping("selectLogInfo")
    List<Map<String, Integer>> selectLogInfo();

}