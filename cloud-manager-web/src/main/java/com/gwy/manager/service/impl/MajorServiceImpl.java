package com.gwy.manager.service.impl;

import com.gwy.manager.domain.entity.Major;
import com.gwy.manager.invokes.MajorInvoker;
import com.gwy.manager.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:10
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Qualifier("webMajorInvoker")
    @Autowired
    private MajorInvoker majorInvoker;

    @Override
    public int addMajor(Major major) {
        return majorInvoker.insert(major);
    }

    @Override
    public int updateMajor(Major major) {
        return majorInvoker.updateByPrimaryKey(major);
    }

    @Override
    public Major getMajorById(String majorId) {
        return majorInvoker.selectByPrimaryKey(majorId);
    }

    @Override
    public List<Major> getMajorsByDept(String deptId) {
        return majorInvoker.selectByDept(deptId);
    }
}
