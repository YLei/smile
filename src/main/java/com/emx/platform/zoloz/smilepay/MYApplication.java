package com.emx.platform.zoloz.smilepay;

import android.app.Application;
import com.alipay.zoloz.smile2pay.service.Zoloz;

import static com.emx.platform.zoloz.smilepay.MerchantInfo.mockInfo;
/**
 * Created by bruce on 2018/6/15.
 */
public class MYApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Zoloz zoloz = Zoloz.getInstance(getApplicationContext());

    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }

}
