package com.zdf.service;

import com.zdf.model.po.Email;

/**
 * @author Daneil
 */
public interface MailService {
    /**
     * 发送邮件带附件
     * @param emailForm
     * @return
     */
    Boolean sendMailWithAttachments(Email emailForm);

    Boolean send(com.zdf.utils.entity.Email email);
}
