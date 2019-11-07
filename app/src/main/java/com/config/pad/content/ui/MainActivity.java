package com.config.pad.content.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wuxiaolong.androidmvpsample.R;
import com.config.pad.content.libding.entry.GetWeatherRsp;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.config.pad.content.libding.rerxmvp.presenter.GetWeatherRspPresenter;
import com.config.pad.content.libding.rerxmvp.view.activity.MvpActivity;
import com.config.pad.content.libding.http.manager.ApiCallback;
import com.config.pad.content.libding.http.manager.RetrofitCallback;
import retrofit2.Call;

public class MainActivity extends MvpActivity<GetWeatherRspPresenter> implements interfaceUtilsAll.GetWeatherRspView {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        initToolBarAsHome(getString(R.string.title));
    }

    @Override
    protected GetWeatherRspPresenter createPresenter() {
        return new GetWeatherRspPresenter(this);
    }


    @Override
    public void getDataSuccess(GetWeatherRsp model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(getString(R.string.net_error));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                //请求接口      //MVPRetrofitRxJava请求
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }
//Retrofit请求
    private void loadDataByRetrofit() {
        showProgressDialog();
        Call<GetWeatherRsp> call = apiStores().loadDataByRetrofit("101190201");
        call.enqueue(new RetrofitCallback<GetWeatherRsp>() {
            @Override
            public void onSuccess(GetWeatherRsp model) {
                dataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                toastShow(msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                toastShow(t.getMessage());
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
        addCalls(call);
    }

    //全国+国外主要城市代码http://mobile.weather.com.cn/js/citylist.xml
    //RetrofitRxJava请求
    private void loadDataByRetrofitRxJava() {
        showProgressDialog();
        addSubscription(
                apiStores().loadDataByRetrofitRxJava("101220602"),
                new ApiCallback<GetWeatherRsp>() {

                    @Override
                    public void onSuccess(GetWeatherRsp model) {
                        dataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        toastShow(msg);
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }

    private void dataSuccess(GetWeatherRsp model) {
        GetWeatherRsp.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }
}
