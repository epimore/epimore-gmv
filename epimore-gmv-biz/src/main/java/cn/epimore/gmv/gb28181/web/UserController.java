package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "用户登录API")
@Validated
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/checkToken")
    @ApiOperation(value = "checkToken", notes = "login")
    public ResponseEntity<Boolean> checkToken() {
        return Result.success(true);
    }
}
