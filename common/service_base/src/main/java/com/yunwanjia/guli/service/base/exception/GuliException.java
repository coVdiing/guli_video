package com.yunwanjia.guli.service.base.exception;

import com.yunwanjia.guli.common.base.result.ResultCodeEnum;
import lombok.Data;

@Data
public class GuliException extends RuntimeException {
    private Integer code;

    public GuliException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public GuliException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                "message=" + getMessage() +
                '}';
    }
}
