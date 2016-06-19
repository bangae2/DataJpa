package com.springapp.common.service;

import com.springapp.users.dao.UsersDao;
import com.springapp.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by bangae11 on 2016-06-19.
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void passwordMailSender(String email, String pw) {
        MimeMessage message = javaMailSender.createMimeMessage();
        usersDao.resetPassword(email, pw);
        try {

            message.setSubject("[안내] 임시 비밀번호 발신 <관리자가 보낸메일>", "euc-kr");
            String htmlContent = "당신의 임시 비밀번호는 <Strong>" + pw
                    + "</Strong> 입니다. 사이트에 접속해서 로그인 후 비밀번호를 변경하세요.<br>";
            htmlContent += "<a href='http://localhost:9999/index.choon' target='_blank'>홈으로 이동</a>";
            message.setText(htmlContent, "euc-kr", "html");
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            javaMailSender.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
