package com.bestaone.hiauth.service.impl;


import com.bestaone.hiauth.domain.User;
import com.bestaone.hiauth.mapper.TestMapper;
import com.bestaone.hiauth.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<String> UserIdByClientId(String clientId) {
        return testMapper.UserIdByClientId(clientId);
    }

    @Override
    public List<User> findByUserIds(List<String> userId) {
        ArrayList<User> users = new ArrayList<>();
        for (String id:userId){
            User user = testMapper.findByUserId(id);
            users.add(user);
        }
        return users;
    }
}
