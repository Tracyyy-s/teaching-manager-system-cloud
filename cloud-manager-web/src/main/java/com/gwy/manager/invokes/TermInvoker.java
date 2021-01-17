package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Term;
import org.apache.ibatis.annotations.Param;
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

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("TermMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "90")
@Qualifier("webTermInvoker")
public interface TermInvoker {
    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("termId") String termId);

    @PostMapping("insert")
    int insert(@RequestBody Term record);

    @PostMapping("selectByPrimaryKey")
    Term selectByPrimaryKey(@RequestParam("termId") String termId);

    @PostMapping("selectAll")
    List<Term> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Term record);

    @PostMapping("insertByBatch")
    int insertByBatch(@RequestBody List<Term> terms);

    @PostMapping("getCurrentTerm")
    Term getCurrentTerm(@RequestBody Date date);
}