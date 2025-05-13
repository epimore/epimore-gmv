package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ImageInfo implements Serializable {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("图片业务ID")
    private String bizId;
    @ApiModelProperty("图片说明")
    private String note;
    @ApiModelProperty("图片地址")
    private String picUrl;
    @ApiModelProperty("图片大小byte")
    private Integer picSize;
    @ApiModelProperty("创建时间")
    private LocalDateTime bizTime;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getBizTime() {
        return bizTime;
    }

    public void setBizTime(LocalDateTime bizTime) {
        this.bizTime = bizTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Integer getPicSize() {
        return picSize;
    }

    public void setPicSize(Integer picSize) {
        this.picSize = picSize;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        if (StringUtils.isNotEmpty(picUrl) && picUrl.startsWith("./")) {
            return picUrl.substring(1);
        }
        return picUrl;
    }
}
