package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.mapper.DeptMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("DeptMapperController")
public class DeptMapperController {
    @Resource
    private DeptMapper deptMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }


    @PostMapping("insert")
    int insert(@RequestBody Dept record) {
        return deptMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    Dept selectByPrimaryKey(String deptId) {
        System.out.println(deptId);
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @PostMapping("selectAll")
    List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }


    @PostMapping("getDeptByName")
    Dept getDeptByName(String name) {
        return deptMapper.getDeptByName(name);
    }


    @PostMapping("getDeptByIds")
    Map<String, Dept> getDeptByIds(@RequestBody List<String> ids) {
        return deptMapper.getDeptByIds(ids);
    }

}