package com.yunwanjia.guli.service.oss.controller.admin;

import com.yunwanjia.guli.common.base.result.ResultCodeEnum;
import com.yunwanjia.guli.common.base.result.ResultDTO;
import com.yunwanjia.guli.common.base.util.ExceptionUtils;
import com.yunwanjia.guli.service.base.exception.GuliException;
import com.yunwanjia.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResultDTO upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam MultipartFile file,
            @ApiParam(value = "模块", required = true)
            @RequestParam String module) {
        String uploadUrl = null;
        try {
            InputStream inputStream = file.getInputStream();
            uploadUrl = fileService.upload(inputStream, module, file.getOriginalFilename());
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return ResultDTO.ok().data("url", uploadUrl).message("文件上传成功");
    }

    @ApiOperation("测试远程服务调用")
    @PostMapping("/test")
    public ResultDTO test() {
        log.info("oss被调用");
        return ResultDTO.ok();
    }

    @ApiOperation("删除文件")
    @PostMapping("/remove-file")
    public ResultDTO removeFile(
            @ApiParam(value="文件路径",required = true)
            @RequestParam String url) {
        try {
            fileService.removeFile(url);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_DELETE_ERROR);
        }
        return ResultDTO.ok().message("文件删除成功");
    }
}
