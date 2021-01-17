package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.mapper.DeptMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("deptId") String deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }


    @GetMapping("insert")
    int insert(@RequestBody Dept record) {
        return deptMapper.insert(record);
    }

    @GetMapping("selectByPrimaryKey")
    Dept selectByPrimaryKey(@RequestParam("deptId") String deptId) {
        System.out.println(deptId);
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @GetMapping("selectAll")
    List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @GetMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }


    @GetMapping("getDeptByName")
    Dept getDeptByName(@RequestParam("name") String name) {
        return deptMapper.getDeptByName(name);
    }


    @GetMapping("getDeptByIds")
    Map<String, Dept> getDeptByIds(@RequestBody List<String> ids) {
        return deptMapper.getDeptByIds(ids);
    }

}