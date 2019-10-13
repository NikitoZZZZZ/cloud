package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.service.httpservice.HttpService;

import java.util.List;
import java.util.Map;

public interface CheckService {
    Check getCheck(Check check);

    List<Check> getNearPlacesAndChecks(String  longitude, String latitude, String radius);

    Check save(Check check);

    boolean existsByIdAndUsername(String id, String username);

    Check findById(String id);

    List<Check> findByUsername(String username);

    Map<String, String> findCategoriesByUserName(String username);
}