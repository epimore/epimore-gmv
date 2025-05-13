package cn.epimore.gmv.mapper;

import java.util.List;
import java.util.Map;


public interface DemoHelloMapper  {

    public default List<Map<String, Object>> hello() {
        return null;
    }
}
