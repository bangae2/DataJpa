package com.springapp.common.service;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by bangae11 on 2016-06-19.
 */
@Service
public class UtilServiceImpl implements UtilService {
    @Override
    public String resetPassword() {

        int i = 0;
        String pw = "";
        while(i < 10) {
            int is = (int)((Math.random() * 4) + 1);

            switch (is) {
                case 1 : pw += upper(); break;
                case 2 : pw += lower(); break;
                case 3 : pw += integer(); break;
                case 4 : pw += sign(); break;
            }
            i++;
        }

        return shaEncrypt(pw);
    }

    public String upper() {
        int upper = (int) ((Math.random() * 26) + 65);
        return String.valueOf((char)upper);
    }

    public String lower() {
        int lower = (int) ((Math.random() * 26) + 97);
        return String.valueOf((char)lower);
    }

    public String integer() {
        int integer = (int) ((Math.random() * 10) + 48);
        return String.valueOf((char)integer);
    }

    public String sign() {
        int sign = (int) ((Math.random() * 15) + 33);
        return String.valueOf((char)sign);
    }

    public String shaEncrypt(String pw) {
        ShaPasswordEncoder en = new ShaPasswordEncoder();
        return en.encodePassword(pw, null);

    }
}
