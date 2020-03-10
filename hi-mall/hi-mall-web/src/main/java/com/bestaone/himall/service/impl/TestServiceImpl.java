package com.bestaone.himall.service.impl;

import com.bestaone.himall.Mapper.TestMapper;
import com.bestaone.himall.service.TestService;
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
