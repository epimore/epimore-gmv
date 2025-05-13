package cn.epimore.gmv.api.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import cn.epimore.gmv.api.DemoHelloApi;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeecgBoot
 */
@Slf4j
@Component
public class DemoHelloFallback implements FallbackFactory<DemoHelloApi> {
    @Setter
    private Throwable cause;

    @Override
    public DemoHelloApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }

}
