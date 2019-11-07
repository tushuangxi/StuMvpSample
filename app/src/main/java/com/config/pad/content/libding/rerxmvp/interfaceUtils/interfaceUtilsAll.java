package com.config.pad.content.libding.rerxmvp.interfaceUtils;

import com.config.pad.content.libding.entry.GetWeatherRsp;

public class interfaceUtilsAll {
    public interface BaseView {
        void showLoading();

        void hideLoading();
    }
    public interface GetWeatherRspView extends BaseView {

        void getDataSuccess(GetWeatherRsp model);

        void getDataFail(String msg);

    }

}
