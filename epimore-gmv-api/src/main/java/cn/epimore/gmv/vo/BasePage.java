package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;

public class BasePage {
    @ApiModelProperty("pageNum")
    private Integer pageNum;
    @ApiModelProperty("pageSize")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null || pageNum == 0 ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize == 0 ? 10 : pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
