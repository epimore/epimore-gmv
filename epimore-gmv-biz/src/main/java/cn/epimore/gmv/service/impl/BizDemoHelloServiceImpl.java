package cn.epimore.gmv.service.impl;


import cn.epimore.gmv.mapper.DemoHelloMapper;
import cn.epimore.gmv.service.IBizDemoHelloService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.jeecg.common.system.api.IAuthAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 测试Service
 */
@Service
//@DependsOn("sysAuthAPIFallback")
public class BizDemoHelloServiceImpl implements IBizDemoHelloService {
    @Autowired
    DemoHelloMapper demoHelloMapper;
    @Autowired
    IAuthAPI authAPI;
//    @Autowired
//    ISysBaseAPI sysBaseAPI;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String hello() {

        List<Map<String,Object>> results = demoHelloMapper.hello();
        String rtnStr = "";
        for (Map<String,Object> item : results
             ) {
            rtnStr = rtnStr + item.get("info").toString()+"<br>";
            System.out.println(rtnStr);
        }
        return rtnStr;
    }

    @Override
    public String testLogin(String username, String password) {
        String result = this.authAPI.loginUser(username,password,true);
        return "登录失败:" ;
    }

    @Override
    public String getLoginInfo() {

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            // 获取当前用户的身份信息
            PrincipalCollection principals = currentUser.getPrincipals();
            // 这里可以根据需要进一步处理身份信息
            LoginUser loginUser = principals.oneByType(LoginUser.class);
            return "用户已登录" + loginUser.toString();
        } else {
            return  "用户未登录";
        }
    }
}
