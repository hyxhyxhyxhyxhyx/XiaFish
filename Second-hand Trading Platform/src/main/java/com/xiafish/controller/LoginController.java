package com.xiafish.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiafish.pojo.Result;
import com.xiafish.service.LoginService;
import com.xiafish.service.UserService;
import com.xiafish.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class LoginController implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginBody)
    {
        String username=(String) loginBody.get("username");
        String password=(String) loginBody.get("password");
        Integer userStatus;
        Map<String, Object> claims = new HashMap<>();
        Integer userId;
        try {
            userId = loginService.getIdByUserNameAndPassword(username, password);
        }catch (RuntimeException e)
        {
            log.info(e.getMessage());
            return Result.error("Incorrect username or password");
        }

        userStatus=loginService.getStatusByUserId(userId);

        claims.put("id", userId);
        claims.put("status",userStatus);
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }

}