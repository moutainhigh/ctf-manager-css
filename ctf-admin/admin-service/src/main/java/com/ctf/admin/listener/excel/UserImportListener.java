package com.ctf.admin.listener.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ctf.admin.pojo.dto.UserImportDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户导入事件监听
 *
 * @ClassName UserImportListener
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Component
@Scope("prototype")
public class UserImportListener extends AnalysisEventListener<UserImportDTO.UserItem> {

    @Override
    public void invoke(UserImportDTO.UserItem userItem, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
