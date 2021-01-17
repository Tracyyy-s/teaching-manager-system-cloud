package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Term;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("TermMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface TermInvoker {
    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String termId);

    @PostMapping("insert")
    int insert(Term record);

    @PostMapping("selectByPrimaryKey")
    Term selectByPrimaryKey(String termId);

    @PostMapping("selectAll")
    List<Term> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Term record);

    @PostMapping("insertByBatch")
    int insertByBatch(@Param("terms") List<Term> terms);

    @PostMapping("getCurrentTerm")
    Term getCurrentTerm(Date date);
}