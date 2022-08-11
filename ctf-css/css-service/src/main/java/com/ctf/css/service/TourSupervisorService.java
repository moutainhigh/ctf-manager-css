package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourSupervisor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.UserVO;

/**
* @author zhangyizheng
* @description 针对表【store_tour_supervisor(巡店督导员表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface TourSupervisorService extends IService<TourSupervisor> {

    /**
     * 督导人员分页查询
     * @param queryParams 分页条件
     * @return
     */
    Page<UserVO> pageSupervisor(TourSupervisorPageQuery queryParams);

    /**
     * 通过用户工号获取督导人员信息
     * @param staffCode 用户工号
     * @return 督导人员表单信息
     */
    SuperviseForm getSupervisorFormData(String staffCode);

    /**
     * 通过表单信息新增督导人员
     * @param superviseForm 表单信息
     * @return
     */
    boolean saveSupervisor(SuperviseForm superviseForm);

    /**
     * 通过表单修改督导人员信息
     * @param supervisorId 督导人员ID
     * @param superviseForm 督导人员表单信息
     * @return
     */
    boolean updateSupervisor(Long supervisorId, SuperviseForm superviseForm);

    /**
     * 通过多个id,批量删除督导人员
     * @param ids 多个ID组成的字符串，使用英文(,)分割
     * @return
     */
    boolean deleteSupervisors(String ids);
}
