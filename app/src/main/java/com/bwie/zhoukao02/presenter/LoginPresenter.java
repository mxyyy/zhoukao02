package com.bwie.zhoukao02.presenter;

import android.text.TextUtils;

import com.bwie.zhoukao02.bean.User;
import com.bwie.zhoukao02.utils.NetUtils;


public class LoginPresenter {


    public LoginPresenter(UserLoginPresenter muserLoginPresenter) {
        this.muserLoginPresenter = muserLoginPresenter;
    }

    public  boolean  submit(User user){
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }


    public void  Login(final User user) {
        new Thread() {
            @Override
            public void run() {
                NetUtils netUtils = new NetUtils();
                if (netUtils.sendNetUtils(user)) {
                    muserLoginPresenter.success();
                } else {

                    muserLoginPresenter.failed();
                }
            }


        }.start();
    }

    public interface  UserLoginPresenter{
        void success();
        void failed();
    }

    private UserLoginPresenter muserLoginPresenter;

}
