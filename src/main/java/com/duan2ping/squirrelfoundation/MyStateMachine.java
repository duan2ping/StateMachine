package com.duan2ping.squirrelfoundation;

import org.squirrelframework.foundation.fsm.StateMachineStatus;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * 状态机(也可以使用注解声明式状态机)
 * Created by duan2ping on 2018/5/16.
 */
// 使用AbstractStateMachine实现状态机操作需要指定T，S，E，C泛型很麻烦，可以使用
// AbstractUntypedStateMachine无状态机则不需要指定泛型
// 使用@StateMachineParameters(stateType=MyState.class, eventType=MyEvent.class, contextType=MyContext.class)申明
public class MyStateMachine extends AbstractStateMachine<MyStateMachine,MyState,MyEvent,MyContext> {


    // 重新父类状态转移异常处理方法
    @Override
    public void afterTransitionCausedException(MyState fromState, MyState toState, MyEvent event, MyContext context) {

        System.out.println("--------[Exception]：State from {"+fromState+"}---->"+" to "+"{"+ toState +"}");
        if(toState == MyState.B){
            //context.flag = true;
            setStatus(StateMachineStatus.IDLE);
        }else {
            super.afterTransitionCausedException(fromState, toState, event, context);
        }
    }


    // 自己根据业务实现相应的动作

    // 【进入状态】

    // 进入状态AChild1执行的动作
    public void entryStateAChild1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateAChild1");
    }

    // 进入状态AChild2执行的动作
    public void entryStateAChild2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateAChild2");
    }

    // 进入状态C2执行的动作
    public void entryStateC2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateC2");
    }

    // 进入状态C1执行的动作
    public void entryStateC1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateC1");
    }

    // 进入状态C执行的动作
    public void entryStateC(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateC");
    }

    // 进入状态B执行的动作
    public void entryStateB(MyState from, MyState to, MyEvent event, MyContext context){
        if(context.flag){
            throw new RuntimeException("发生了异常...");
        }
        System.out.println("entryStateB");
    }
    // 进入状态A执行的动作
    public void entryStateA(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("entryStateA");
    }


    // 【状态转移】

    // 状态从A转移到B的动作
    public void stateAToStateBOnGotoB(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("A-------->B");
    }
    // 状态从B转移到C的动作
    public void stateBToStateCOnGotoC(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("B-------->C");
    }
    // 状态从B转移到C的动作
    public void stateCToStateC1OnGotoC1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("C-------->C1");
    }
    // 状态从B转移到C的动作
    public void stateCToStateC2OnGotoC2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("C-------->C2");
    }

    // 状态从A转移到AChild1的动作
    public void fromAToAChild1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("A-------->AChild1");
    }

    // 状态从C1Child1转移到C1Child2的动作
    public void fromC1Child1ToC1Child2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("C1Child1-------->C1Child2");
    }

    // 状态从C2Child1转移到C2Child2的动作
    public void fromC2Child1ToC2Child2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("C2Child1-------->C2Child2");
    }

    // 状态从A转移到AChild2的动作
    public void fromAToAChild2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("AChild1-------->AChild2");
    }

    // 状态从任何状态到C状态
    public void fromAnyToC(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("fromAny-------->C");
    }


    // 【退出状态】

    // 进入状态C2的动作
    public void exitStateC2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateC2");
    }

    // 进入状态C的动作
    public void exitStateC1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateC1");
    }

    // 进入状态C的动作
    public void exitStateC(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateC");
    }

    // 进入状态B的动作
    public void exitStateB(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateB");
    }
    // 退出状态A的动作
    public void exitStateA(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateA");
    }
    // 退出状态AChild1的动作
    public void exitStateAChild1(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateAChild1");
    }
    // 退出状态AChild2的动作
    public void exitStateAChild2(MyState from, MyState to, MyEvent event, MyContext context){
        System.out.println("exitStateAChild2");
    }

}
