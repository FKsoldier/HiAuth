package com.lmq.hidemo.service.impl;

import com.lmq.hidemo.Mapper.TestMapper;
import com.lmq.hidemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String test() {

        return testMapper.test();
    }

    @Override
    public String checkRoleByUserId(String userId, String clientId) {
        return testMapper.checkRoleByUserId(userId,clientId);
    }


}
