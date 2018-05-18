package com.duan2ping.squirrelfoundation;

import org.squirrelframework.foundation.fsm.AnonymousAction;

/**
 * 状态决策：判断状态的转移
 * Created by duan2ping on 2018/5/16.
 */
public class StateMaker extends AnonymousAction<MyStateMachine,MyState,MyEvent,MyContext> {

    public void execute(MyState from, MyState to, MyEvent event, MyContext context, MyStateMachine machine) {

        // 状态转移到C
        if(to == MyState.C){
            // 根据上下文数据分析状态流转
            if(context.flag){
                machine.fire(MyEvent.ToC1);
            }else{
                machine.fire(MyEvent.ToC2);
            }
        }

    }
}
