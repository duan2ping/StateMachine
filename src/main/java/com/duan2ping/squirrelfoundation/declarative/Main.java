package com.duan2ping.squirrelfoundation.declarative;

import com.duan2ping.squirrelfoundation.MyEvent;
import com.duan2ping.squirrelfoundation.MyState;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * 测试声明式注解式状态机
 * Created by duan2ping on 2018/5/17.
 */
public class Main {

    public static void main(String[] args) {
        // 3. 构建状态转换
        // StateMachineBuilder：状态机生成器
        // 使用Factory创建状态机生成器需要：状态机类型，状态类型，事件类型，上下文类型
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(DeclarativeStateMachine.class);

       // ConverterProvider.INSTANCE.register(MyEvent.class, new MyEventConverter());
        //ConverterProvider.INSTANCE.register(MyState.class, new MyStateConverter());

        UntypedStateMachine fsm = builder.newStateMachine(MyState.A);

        fsm.start();

        fsm.fire(MyEvent.ToB);

        fsm.fire(MyEvent.ToC);

        fsm.terminate();
    }

}
