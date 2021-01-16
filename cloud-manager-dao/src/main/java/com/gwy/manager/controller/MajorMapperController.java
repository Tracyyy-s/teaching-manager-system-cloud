package com.gwy.manager.controller;


import com.gwy.manager.domain.entity.Major;
import com.gwy.manager.mapper.MajorMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("MajorMapperController")
public class MajorMapperController {
    @Resource
    private MajorMapper majorMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String majorId){
        return majorMapper.deleteByPrimaryKey( majorId) ;
    };
    @RequestMapping("insert")
    int insert(Major record){
        return majorMapper.insert(record) ;
    };
    @RequestMapping("selectByPrimaryKey")
    Major selectByPrimaryKey(String majorId){
        return majorMapper.selectByPrimaryKey( majorId) ;
    };
    @RequestMapping("selectAll")
    List<Major> selectAll(){
        return majorMapper.selectAll() ;
    };
    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Major record){
        return majorMapper.updateByPrimaryKey(record) ;
    };

    /**
     * 获得某学院的所有专业
     * @param deptId    学院id
     * @return  返回结果
     */
    @RequestMapping("selectByDept")
    List<Major> selectByDept(String deptId){
        return majorMapper.selectByDept(deptId) ;
    };
}