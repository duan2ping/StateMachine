package com.duan2ping.squirrelfoundation.declarative;

import com.duan2ping.squirrelfoundation.MyState;
import org.squirrelframework.foundation.fsm.Converter;

/**
 * 状态转换器，因为注解里使用的都是String，所以要自定义状态名转换成String的转换器，否则状态机找不到状态
 * Created by duan2ping on 2018/5/17.
 */
public class MyStateConverter implements Converter<MyState>{


    // 状态转String
    public String convertToString(MyState myState) {
        return myState.name();
    }

    // String转状态
    public MyState convertFromString(String s) {
        return MyState.valueOf(s);
    }
}
