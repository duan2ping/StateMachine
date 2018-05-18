package com.duan2ping.squirrelfoundation.declarative;

import com.duan2ping.squirrelfoundation.MyEvent;
import org.squirrelframework.foundation.fsm.Converter;

/**
 * 事件转换器，因为注解里使用的都是String，所以要自定义事件名转换成String的转换器，否则状态机找不到事件
 * Created by duan2ping on 2018/5/17.
 */
public class MyEventConverter implements Converter<MyEvent>{

    // 事件转String
    public String convertToString(MyEvent myEvent) {
        return myEvent.name();
    }

    // String转事件
    public MyEvent convertFromString(String s) {
        return MyEvent.valueOf(s);
    }
}
