package com.bestaone.hiauth.service;

import com.bestaone.hiauth.domain.User;

import java.util.List;

public interface TestService {

    List<String> UserIdByClientId(String clientId);

    List<User> findByUserIds(List<String> userId);
}
