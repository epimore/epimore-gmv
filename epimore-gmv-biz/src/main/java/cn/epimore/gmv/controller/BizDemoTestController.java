package cn.epimore.gmv.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.epimore.gmv.service.IBizDemoHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "demo示例")
@RestController
@RequestMapping("/bizdemo11")
@Slf4j
public class BizDemoTestController {

	@Autowired
	private IBizDemoHelloService jeecgHelloService;

	@ApiOperation(value = "hello", notes = "对外服务接口")
	@GetMapping(value = "/hello11")
	public String sayHello() {
		log.info(" ---我被调用了--- ");
		String str = jeecgHelloService.hello();
		return  str;
	}


	@ApiOperation(value = "testLogin", notes = "testLogin")
	@GetMapping(value = "/testLogin11")
	public String testLogin() {
		String str = jeecgHelloService.testLogin("test","Star*2024");
		return  str;
	}

	@ApiOperation(value = "getLogin", notes = "getLogin")
	@GetMapping(value = "/getLogin11")
	public String getLogin() {
		return  jeecgHelloService.getLoginInfo();
	}

}
