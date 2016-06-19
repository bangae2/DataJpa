package com.springapp.common.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bangae11 on 2016-06-19.
 */
public interface MailService {
    public void passwordMailSender(String email, String pw);
}
