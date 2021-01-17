package com.gwy.manager.service.impl;

import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.MailForm;
import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.domain.entity.User;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.UserOption;
import com.gwy.manager.invokes.StudentInvoker;
import com.gwy.manager.invokes.UserInvoker;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.service.VrCodeService;
import com.gwy.manager.util.MailUtil;
import com.gwy.manager.util.RedisUtil;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Tracy
 * @date 2020/11/26 15:26
 */
@Service
public class VrCodeServiceImpl implements VrCodeService {

    /**
     * 验证码的长度
     */
    private static final int VR_CODE_LENGTH = 6;

    /**
     * 验证码过期时间，默认5min
     */
    private static final int EXPIRATION_TIME = 300;

    @Qualifier("webStudentInvoker")
    @Autowired
    private StudentInvoker studentMapper;

    @Qualifier("webUserInvoker")
    @Autowired
    private UserInvoker userMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RabbitmqProducer producer;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getCode(String userId) {
        return (String) redisUtil.get(userId + ":Code");
    }


    @Override
    public void deleteCode(String userId) {
        redisUtil.del(redisUtil.codeKey(userId));
    }


    @Override
    public ResultVO sendCode(String userId, String userType) {

        //生成六位验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < VR_CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }

        //获得email，并发送邮件
        String email;
        if (userType == null) {
            Student student = studentMapper.selectByPrimaryKey(userId);
            if (student == null) {
                User user = userMapper.selectByPrimaryKey(userId);

                if (user == null) {
                    return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
                }

                email = user.getEmail();
            } else {
                email = student.getEmail();
            }
        } else if (userType.equals(RoleName.USER)) {
            email = userMapper.selectByPrimaryKey(userId).getEmail();
        } else {
            email = studentMapper.selectByPrimaryKey(userId).getEmail();
        }

        MailForm mailForm = new MailForm();
        mailForm.setSubject(mailUtil.getSubject());
        mailForm.setText(mailUtil.getPrefix() + code.toString() + mailUtil.getSuffix());
        mailForm.setHtml(true);
        mailForm.setFrom(mailUtil.getSender());
        mailForm.setTo(email);

        //生产者将邮件体发送至rabbitmq
        producer.sendMailToMq(mailForm);

        //设置redis中的key
        String key = redisUtil.codeKey(userId);
        try {
            redisUtil.set(key, code.toString());
            redisUtil.expire(key, EXPIRATION_TIME);
        } catch (Exception e) {
            return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        if (userType == null) {
            return ResultVOUtil.success(email);
        }

        return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
    }

    public ResultVO updatePasswordByCode(String userType,
                                         String userId,
                                         String password,
                                         String vrCode) {

        String code = this.getCode(userId);
        if (!vrCode.equals(code)) {
            return ResultVOUtil.error("Code Error");
        }

        ResultVO resultVO;

        int result;
        if (userType.equals(UserOption.STUDENT.getUserType())) {
            result = studentMapper.updatePassword(userId, passwordEncoder.encode(password));
        } else {
            result = userMapper.updatePassword(userId, passwordEncoder.encode(password));
        }

        if (result == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        //修改完毕后删除key
        this.deleteCode(userId);

        return resultVO;
    }
}
