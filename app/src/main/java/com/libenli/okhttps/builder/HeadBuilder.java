package com.libenli.okhttps.builder;


import com.libenli.okhttps.OkHttpUtils;
import com.libenli.okhttps.request.OtherRequest;
import com.libenli.okhttps.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
