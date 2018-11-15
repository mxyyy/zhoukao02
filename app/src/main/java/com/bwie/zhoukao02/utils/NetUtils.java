package com.bwie.zhoukao02.utils;

import com.bwie.zhoukao02.bean.User;


public class NetUtils {
    public boolean  sendNetUtils(User user){
        if ("Baway".equals(user.username) && "123".equals(user.password)){
            return true;

        }else{
            return false;
        }


    }
}
