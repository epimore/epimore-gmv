package cn.epimore.gmv.gb28181.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.jeecg.common.system.vo.LoginUser;

@Slf4j
public class CurrentUserHelper {
    public static LoginUser getSystemUser() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser.isAuthenticated()) {
                // 获取当前用户的身份信息
                PrincipalCollection principals = currentUser.getPrincipals();
                // 这里可以根据需要进一步处理身份信息
                LoginUser loginUser = principals.oneByType(LoginUser.class);
                return loginUser;
            }
        } catch (Exception e) {
            log.warn("获取当前系统用户信息异常-{}", e.getMessage());
        }
        return null;
    }
}
