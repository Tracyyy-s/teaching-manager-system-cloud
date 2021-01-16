package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public interface SysLogMapper {

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

    List<Map<String, Object>> selectDataExplainAndCount();

    List<SysLog> selectByType(String type);

    List<SysLog> selectByInterval(@Param("beginTime") Date beginTime,
                                  @Param("endTime") Date endTime,
                                  @Param("type") String type);

    int deleteByPrimaryKeys(@Param("ids") List<Integer> ids);


    List<Map<Date, Integer>> selectLogsInfo();

    List<Map<String, Integer>> selectLogInfo();
}