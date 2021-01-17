package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Term;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.TermMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("TermMapperController")
public class TermMapperController {
    @Resource
    TermMapper termMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("termId") String termId) {
        return termMapper.deleteByPrimaryKey(termId);
    }

    @RequestMapping("insert")
    int insert(@RequestBody Term record) {
        return termMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    Term selectByPrimaryKey(@RequestParam("termId") String termId) {
        return termMapper.selectByPrimaryKey(termId);
    }

    @RequestMapping("selectAll")
    List<Term> selectAll() {
        return termMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Term record) {
        return termMapper.updateByPrimaryKey(record);
    }

    @RequestMapping("insertByBatch")
    int insertByBatch(@RequestBody List<Term> terms) {
        return termMapper.insertByBatch(terms);
    }

    @RequestMapping("getCurrentTerm")
    Term getCurrentTerm(@RequestBody Date date) {
        System.out.println(date);
        return termMapper.getCurrentTerm(date);
    }
}