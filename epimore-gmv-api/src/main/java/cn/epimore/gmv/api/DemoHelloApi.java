package cn.epimore.gmv.api;
import cn.epimore.gmv.api.fallback.DemoHelloFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "epimore-gmv", fallbackFactory = DemoHelloFallback.class)
public interface DemoHelloApi {

    /**
     * demo hello 微服务接口
     * @param
     * @return
     */
    @GetMapping(value = "/demo/hello1123")
    String callHello();
}
