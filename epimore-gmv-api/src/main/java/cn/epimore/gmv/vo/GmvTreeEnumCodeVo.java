package cn.epimore.gmv.vo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GmvTreeEnumCodeVo implements Serializable {
    @ApiModelProperty("子编码树")
    private List<GmvTreeEnumCodeVo> subVos = new ArrayList<>();
    @JsonUnwrapped
    private GmvEnumCodeVo vo;

    public GmvTreeEnumCodeVo() {
    }

    public GmvTreeEnumCodeVo(GmvEnumCodeVo vo) {
        this.vo = vo;
    }
}
