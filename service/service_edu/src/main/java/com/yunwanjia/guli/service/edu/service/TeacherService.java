package com.yunwanjia.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunwanjia.guli.service.edu.entity.Teacher;
import com.yunwanjia.guli.service.edu.entity.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author vi
 * @since 2021-03-01
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> selectPage(Page<Teacher> param, TeacherQueryVo teacherQueryVo);
}
