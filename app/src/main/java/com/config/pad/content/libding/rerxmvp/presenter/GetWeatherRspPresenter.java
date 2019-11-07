package com.config.pad.content.libding.rerxmvp.presenter;

import com.config.pad.content.libding.entry.GetWeatherRsp;
import com.config.pad.content.libding.rerxmvp.base.BasePresenter;
import com.config.pad.content.libding.http.manager.ApiCallback;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;

public class GetWeatherRspPresenter extends BasePresenter<interfaceUtilsAll.GetWeatherRspView> {

    public GetWeatherRspPresenter(interfaceUtilsAll.GetWeatherRspView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading();
        addSubscription(apiStores.loadDataByRetrofitRxJava(cityId),
                new ApiCallback<GetWeatherRsp>() {
                    @Override
                    public void onSuccess(GetWeatherRsp model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

}
