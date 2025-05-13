package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;


public class Result<T> {
    @ApiModel
    static class ResultBody<T> implements Serializable {
        @ApiModelProperty("响应编码，200为成功，其他为失败")
        private int code;

        @ApiModelProperty("响应信息描述")
        private String msg = "操作失败";

        @ApiModelProperty("响应数据")
        private T data;

        public ResultBody(int code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    // 静态方法，方便构造通用的Result对象
    public static <T> ResponseEntity success(T data) {
        ResultBody<T> body = new ResultBody<>(200, "操作成功", data);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity failure(String msg) {
        ResultBody<T> body = new ResultBody<>(500, msg, null);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity failure() {
        ResultBody<T> body = new ResultBody<>(500, "操作失败", null);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity build(boolean state) {
        return state ? success(null) : failure();
    }

    public static <T> ResponseEntity common(int code, String msg, T data) {
        ResultBody<T> body = new ResultBody<>(code, msg, data);
        return new ResponseEntity<>(body, HttpStatus.valueOf(code));
    }

    public static <T> ResponseEntity custom(int code, String msg, T data, HttpStatus status) {
        ResultBody<T> body = new ResultBody<>(code, msg, data);
        return new ResponseEntity<>(body, status);
    }
}
