package com.netcracker.checkapp.server.service.userinfoservice;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.netcracker.checkapp.server.model.UserInfo;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import com.netcracker.checkapp.server.service.httpservice.HttpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoRepository userInfoRepository;
    private HttpService httpService;

    UserInfoServiceImpl(UserInfoRepository userInfoRepository, HttpService httpService) {
        this.userInfoRepository = userInfoRepository;
        this.httpService = httpService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userInfoRepository.existsByLogin(username);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo delete(String login) {
        return userInfoRepository.deleteUserInfoByLogin(login);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByLogin(username);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo register(UserInfo userInfo) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (existsByUsername(userInfo.getLogin())) {
            throw new Exception("Этот логин уже занят");
        }
        userInfo.setPwd(bCryptPasswordEncoder.encode(userInfo.getPwd()));
        userInfo.setRole("ROLE_USER");
        save(userInfo);

        return userInfo;
    }
}
