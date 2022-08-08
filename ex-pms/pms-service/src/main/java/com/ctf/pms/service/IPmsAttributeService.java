package com.ctf.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.pms.pojo.entity.PmsCategoryAttribute;
import com.ctf.pms.pojo.form.PmsCategoryAttributeForm;

public interface IPmsAttributeService extends IService<PmsCategoryAttribute> {

    /**
     * 批量保存商品属性
     *
     * @param formData 属性表单数据
     * @return
     */
    boolean saveBatch(PmsCategoryAttributeForm formData);
}
