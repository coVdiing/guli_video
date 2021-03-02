package com.yunwanjia.guli.common.base.result;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel("全局统一返回结果")
public class ResultDTO {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public ResultDTO() {

    }

    public static ResultDTO ok() {
        ResultDTO r = new ResultDTO();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static ResultDTO error() {
        ResultDTO r = new ResultDTO();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static ResultDTO setResult(ResultCodeEnum resultCodeEnum){
        ResultDTO r = new ResultDTO();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public ResultDTO success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultDTO message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultDTO code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResultDTO data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResultDTO data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
