package com.yunwanjia.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunwanjia.guli.service.edu.entity.Teacher;
import com.yunwanjia.guli.service.edu.entity.vo.TeacherQueryVo;
import com.yunwanjia.guli.service.edu.mapper.TeacherMapper;
import com.yunwanjia.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author vi
 * @since 2021-03-01
 */
@Service
@Transactional(
        rollbackFor = {Exception.class}
)
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Page<Teacher> selectPage(Page<Teacher> param, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> query = new QueryWrapper<>();
        query.orderByAsc("sort");
        if (teacherQueryVo == null) {
            return baseMapper.selectPage(param,query);
        }
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)) {
            query.likeRight("name", name);
        }
        if (level != null) {
            query.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            query.ge("join_date", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            query.le("join_date", joinDateEnd);
        }
        return baseMapper.selectPage(param,query);
    }
}
