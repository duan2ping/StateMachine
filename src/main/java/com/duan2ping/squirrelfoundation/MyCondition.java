package com.duan2ping.squirrelfoundation;

import org.squirrelframework.foundation.fsm.AnonymousCondition;

/**
 * 自定义过滤条件类
 * Created by duan2ping on 2018/5/17.
 */
public class MyCondition extends AnonymousCondition<MyContext> {

    // 框架回调判断
    public boolean isSatisfied(MyContext context) {
        return 2>1;
    }
}
