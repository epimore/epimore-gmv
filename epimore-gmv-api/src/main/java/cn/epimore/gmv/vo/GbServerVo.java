package cn.epimore.gmv.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 国标信令服务
 *
 */
@Getter
@Setter
@TableName(value ="GB_SERVER")
public class GbServerVo implements Serializable {

    @ApiModelProperty("")
    @TableId
    private String domainId;
    /**
     * sip域
     */

    @ApiModelProperty("sip域")

    private String domain;
    /**
     * sip监听IP
     */

    @ApiModelProperty("sip监听IP")

    private String sipIp;
    /**
     * sip监听端口
     */
    @ApiModelProperty("sip监听端口")
    private Integer sipPort;
    /**
     * http接口源路径
     */

    @ApiModelProperty("http接口源路径")

    private String httpSource;
    /**
     * 0-禁用，1-启用
     */

    @ApiModelProperty("0-禁用，1-启用")
    private Integer status;
    /**
     * 心跳时间
     */
    @ApiModelProperty("心跳时间")
    private LocalDateTime heartTime;
    /**
     * 心跳周期秒
     */
    @ApiModelProperty("心跳周期秒")
    private Integer heartCycle;
    @ApiModelProperty("状态：0-离线，1-在线")
    @TableField(exist = false)
    private int online;

}
