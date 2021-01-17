package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.DeptInvoker;
import com.gwy.manager.service.DeptService;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:09
 */
@CacheConfig(cacheNames = "depts")
@Service
public class DeptServiceImpl implements DeptService {

    @Qualifier("webDeptInvoker")
    @Autowired
    private DeptInvoker deptInvoker;

    @Override
    public int addDept(Dept dept) {
        return deptInvoker.insert(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptInvoker.updateByPrimaryKey(dept);
    }

    @Cacheable(key = "#deptId")
    @Override
    public ResultVO getDeptById(String deptId) {

        ResultVO resultVO;

        Dept dept = deptInvoker.selectByPrimaryKey(deptId);
        if (dept == null) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(dept);
        }
        return resultVO;
    }

    @Override
    public Dept getDeptByName(String name) {
        return deptInvoker.getDeptByName(name);
    }

    @Cacheable(key = "'all'")
    @Override
    public ResultVO getAllDepts() {

        ResultVO resultVO;

        List<Dept> depts = deptInvoker.selectAll();
        if (depts.size() == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(depts);
        }

        return resultVO;
    }
}
