package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/5 13:06
 */
public interface SysLogService {

    /**
     * 使用日志工具记录日志
     * @param request   请求
     * @param args  参数
     * @param resultVO  请求返回结果
     */
    void recordLog(HttpServletRequest request, Object[] args, ResultVO resultVO);

    /**
     * 添加日志
     * @param sysLog    预添加的日志
     */
    void insertLog(SysLog sysLog);

    /**
     * 获得每类日志的数量
     * @return  结果集
     */
    ResultVO getLogTypeAndCount();

    /**
     * 通过日志类型获得日志
     * @param type  关键字
     * @param pageNum   页码
     * @param pageSize  页大小
     * @return  结果集
     */
    ResultVO getLogInfoByType(String type, int pageNum, int pageSize);

    /**
     * 批量删除日志
     * @param ids   id
     * @return  结果集
     */
    ResultVO deleteByBatch(List<Integer> ids);

    /**
     * 获得指定时间内的指定时间内的日志，用于导出
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param type  日志类型
     * @return  结果集
     */
    ResultVO getLogByInterval(Date beginTime, Date endTime, String type);

    /**
     * 分页获得日志
     * @param pageNum   页码
     * @param pageSize  每页个数
     * @return  结果集
     */
    ResultVO getLogs(int pageNum, int pageSize);

    /**
     * 获得日志信息(天数，日志数量)
     * @return  结果集
     */
    ResultVO getLogsInfo();
}
