package com.yunwanjia.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunwanjia.guli.common.base.result.ResultDTO;
import com.yunwanjia.guli.service.edu.entity.Teacher;
import com.yunwanjia.guli.service.edu.entity.vo.TeacherQueryVo;
import com.yunwanjia.guli.service.edu.service.TeacherService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author vi
 * @since 2021-03-01
 */
@ApiModel("讲师操作接口")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("讲师列表")
    @GetMapping("/list")
    public ResultDTO listAll() {
        return ResultDTO.ok().data("items", teacherService.list());
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("/remove/{id}")
    public ResultDTO remove(@PathVariable Integer id) {
        boolean result = teacherService.removeById(id);
        if (result) {
            return ResultDTO.ok().message("删除成功");
        } else {
            return ResultDTO.error().message("数据不存在");
        }
    }

    @ApiOperation("讲师分页列表")
    @GetMapping("/list/{page}/{limit}")
    public ResultDTO listPage(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable("page") Long page,
                              @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                              @ApiParam("讲师列表查询条件") TeacherQueryVo teacherQueryVo) {
        Page<Teacher> param = new Page<>(page, limit);
        param = teacherService.selectPage(param, teacherQueryVo);
        List<Teacher> rows = param.getRecords();
        long total = param.getTotal();
        return ResultDTO.ok().data("rows", rows).data("total", total);
    }
}

