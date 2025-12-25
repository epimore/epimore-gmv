package cn.epimore.gmv.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 枚举编码表（层级ID字符串）
 *
 */
@Getter
@Setter
@TableName(value ="GMV_ENUM_CODE")
public class GmvEnumCodeVo implements Serializable {

    /**
     * 层级编码ID，如 1001, 1001001, 1001001001
     */

    @ApiModelProperty("层级编码ID，如 1001, 1001001, 1001001001")
    @TableId
    private String id;
    /**
     * 父节点ID，NULL 表示顶级根
     */

    @ApiModelProperty("父节点ID，NULL 表示顶级根")
    private String parentId;
    /**
     *
     */


    @ApiModelProperty("")
    private String name;
    /**
     *
     */


    @ApiModelProperty("")
    private String valueStart;
    /**
     *
     */


    @ApiModelProperty("")
    private String valueEnd;
    /**
     *
     */

    @ApiModelProperty("")
    private String remark;
    /**
     * 同级排序
     */
    @ApiModelProperty("同级排序")
    private Integer seq;
    /**
     * 1-启用，0-禁用
     */

    @ApiModelProperty("1-启用，0-禁用")
    private Integer status;
    /**
     *
     */
    @ApiModelProperty("")
    private Date createdAt;


}
