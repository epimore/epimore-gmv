package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 国标设备ID生成器：编码由中心编码(8位)、行业编码(2位)、类型编码(3位)、网络标识(1位)和序号(6位)5个码段共20位十进制数字字符
* 
*/
@Getter
@Setter
public class GbIdSeqVo implements Serializable {
    @ApiModelProperty("")
    private Long id;
    /**
    * SIP域ID
    */
    
    @ApiModelProperty("SIP域ID")
    
    private String domainId;
    /**
    * 中心编码:取domain_id前8位
    */
    
    @ApiModelProperty("中心编码:取domain_id前8位")
    
    private String centerCode;
    /**
    * 行业编码:取domain_id的9-10位
    */
    
    @ApiModelProperty("行业编码:取domain_id的9-10位")
    
    private String industryCode;
    /**
    * 类型编码,3位
    */
    
    @ApiModelProperty("类型编码,3位")
    
    private String typeCode;
    /**
    * 网络编码，1位
    */
    @ApiModelProperty("网络编码，1位")
    private String networkCode;
    /**
    * 序号，6位
    */
    @ApiModelProperty("序号，6位")
    private Integer seq;
    /**
    * 状态：0-禁用，1-启用中
    */
    
    @ApiModelProperty("状态：0-禁用，1-启用中")
    private Integer status;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;


}
