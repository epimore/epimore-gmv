package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PairParamModel")
@Getter
@Setter
public class PairParamModel<T,U> {
    private T param1;
    private U param2;
}
