package com.ctf.css;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 10:50
 * @Describe SuperviseTest
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.UserVO;
import com.ctf.css.service.SuperviseDomainService;
import com.ctf.css.service.TourPlanService;
import com.ctf.css.service.TourSupervisorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SuperviseTest {
    @Autowired
    private TourSupervisorService tourSupervisorService;
    @Autowired
    private SuperviseDomainService superviseDomainService;
    @Autowired
    private TourPlanService tourPlanService;

    @Test
    public void testSupervise() throws Exception {
        TourSupervisorPageQuery queryParams = new TourSupervisorPageQuery();
        queryParams.setPageNum(1);
        queryParams.setPageSize(10);
        queryParams.setStaffCode("11111");
        queryParams.setStaffName("a");
        //获取分页信息
        Page<UserVO> userVOPage = new Page<>();
        userVOPage.setCurrent(queryParams.getPageNum());
        userVOPage.setSize(queryParams.getPageSize());
        //获取数据
        tourSupervisorService.pageSupervisor(queryParams);
    }

    @Test
    public void testSuperviseDomain() throws Exception{
        SupervisorDomainPageQuery queryParams = new SupervisorDomainPageQuery();
        queryParams.setPageNum(1);
        queryParams.setPageSize(2);
        queryParams.setSuperviseDomainName("a");
        superviseDomainService.pageSupervise(queryParams);
    }

    @Test
    public void testTourPage() throws Exception{
        TourPlanPageQuery queryParams = new TourPlanPageQuery();
//        queryParams.setStatus("1");
        queryParams.setArea("闽深区");
        tourPlanService.pagePlan(queryParams);
    }
}
