package com.ctf.service;

import com.ctf.model.po.Email;

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

    Boolean send(com.ctf.utils.entity.Email email);
}
