package com.bwie.zhoukao02;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;


import com.bwie.zhoukao02.adapter.NineAdapter;
import com.bwie.zhoukao02.adapter.ShowAdapter;
import com.bwie.zhoukao02.bean.ADEnity;
import com.bwie.zhoukao02.bean.NineBean;
import com.bwie.zhoukao02.bean.ShowBean;
import com.bwie.zhoukao02.inter.ICallBack;
import com.bwie.zhoukao02.utils.HttpUtils;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgSao;
    private int REQUEST_CODE=1000;
    private List<ShowBean.DataBean> showlist;
    private List<NineBean.DataBean> ninelist;
    private ShowAdapter showAdapter;
    private NineAdapter nineAdapter;
    private Type type;
    private TextViewAd textViewAd;
    private List<ADEnity> mList;
    private RecyclerView rvNine;
    private RecyclerView rvShow;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ADEnity adEnity = new ADEnity("键盘敲烂" + i, "月薪过万" +i, "http://www.baidu.com"+i);
            mList.add(adEnity);
        }
        textViewAd.setmTexts(mList);
        textViewAd.setFrontColor(Color.RED);
        textViewAd.setBackColor(Color.BLUE);
        textViewAd.setmDuration(1000);
        textViewAd.setmInterval(1000);
        textViewAd.setOnClickLitener(new TextViewAd.onClickLitener() {
            @Override
            public void onClick(String mUrl) {
                Toast.makeText(HomeActivity.this,"点击了"+mUrl,Toast.LENGTH_LONG).show();
            }
        });


        ninelist = new ArrayList<>();

         nineAdapter=new NineAdapter(this,ninelist);

        rvNine.setAdapter(nineAdapter);

        rvNine.setLayoutManager(new GridLayoutManager(this,4));


        Type type=new TypeToken<NineBean>(){}.getType();
        HttpUtils.getInstant().get("http://www.zhaoapi.cn/product/getCatagory", new ICallBack() {


            @Override
            public void onSuccess(Object obj) {
                NineBean nineBean= (NineBean) obj;
                if (nineBean!=null){
                    List<NineBean.DataBean> data = nineBean.getData();
                    if (data!=null){
                        ninelist.clear();
                        ninelist.addAll(data);
                        nineAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        },type);

        showlist = new ArrayList<>();
        showAdapter = new ShowAdapter(this, showlist);
        rvShow.setAdapter(showAdapter);

        rvShow.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        Type type1= new TypeToken<ShowBean>(){}.getType();
        HttpUtils.getInstant().get("http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4", new ICallBack() {



            @Override
            public void onSuccess(Object obj) {
               ShowBean showBean = (ShowBean) obj;
                if (showBean !=null){
                    List<ShowBean.DataBean> data = showBean.getData();
                    if (data!=null){
                        showlist.clear();
                        showlist.addAll(data);
                        showAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        },type1);







        imgSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                }
        });
    }

    private void initView() {
        imgSao = findViewById(R.id.img_sao);
        rvNine = findViewById(R.id.rv_nine);
        rvShow = findViewById(R.id.rv_show);
        textViewAd = findViewById(R.id.textad);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE==requestCode){
            if (data!=null){
                Bundle bundle = data.getExtras();
                if (bundle!=null){
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){

                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果"+result, Toast.LENGTH_SHORT).show();


                }else if(bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_FAILED){
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
