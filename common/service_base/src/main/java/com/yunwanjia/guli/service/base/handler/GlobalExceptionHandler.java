package com.yunwanjia.guli.service.base.handler;

import com.yunwanjia.guli.common.base.result.ResultCodeEnum;
import com.yunwanjia.guli.common.base.result.ResultDTO;
import com.yunwanjia.guli.common.base.util.ExceptionUtils;
import com.yunwanjia.guli.service.base.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lijw
 * 统一异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO error(Exception e) {
        log.error(e.getMessage());
        return ResultDTO.error().message(e.getMessage());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResultDTO error(BadSqlGrammarException e) {
        log.error(ExceptionUtils.getMessage(e));
        return ResultDTO.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultDTO error(HttpMessageNotReadableException e) {
        log.error(ExceptionUtils.getMessage(e));
        return ResultDTO.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResultDTO error(GuliException e) {
        log.error(e.getMessage());
        return ResultDTO.error().message(e.getMessage()).code(e.getCode());
    }
}
