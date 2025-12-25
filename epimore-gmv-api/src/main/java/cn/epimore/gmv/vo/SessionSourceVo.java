package cn.epimore.gmv.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionSourceVo {
    //信令服务器访问地址
    private String httpSource;
    //信令服务器状态 0-禁用，1-启用
    private int serverStatus;
    //设备状态 0-禁用，1-启用
    private int deviceStatus;
}
