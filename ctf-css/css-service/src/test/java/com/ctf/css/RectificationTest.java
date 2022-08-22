package com.ctf.css;

import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.service.RectificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 14:21
 * @Describe RectificationTest
 */
@SpringBootTest
public class RectificationTest {
    @Autowired
    private RectificationService rectificationService;

    @Test
    public void testPage() throws Exception{
        RestultPageQuery queryParams = new RestultPageQuery();
//        queryParams.setStatus("1");
//        queryParams.setArea("闽深区");
        rectificationService.pageRectification(queryParams);
    }
}
