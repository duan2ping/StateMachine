package com.duan2ping.squirrelfoundation;

import org.squirrelframework.foundation.exception.TransitionException;
import org.squirrelframework.foundation.fsm.Action;
import org.squirrelframework.foundation.fsm.annotation.*;

/**
 * 事件监听器
 * Created by duan2ping on 2018/5/16.
 */
public class StateListener{

    // 状态转换结束时被调用
    @OnTransitionEnd
    @ListenerOrder(10) // Since 0.3.1 ListenerOrder can be used to insure listener invoked orderly
    public void transitionEnd() {
        // method annotated with TransitionEnd will be invoked when transition end...
        // the method must be public and return nothing
        System.out.println("[StateListener]------transitionEnd");
    }

    // 状态转换开始时被调用
    @OnTransitionBegin
    public void transitionBegin(MyEvent event) {
        // method annotated with TransitionBegin will be invoked when transition begin...
        System.out.println("[StateListener]------transitionBegin");
    }


    // 触发事件ToB时执行
    // 'event'(E), 'from'(S), 'to'(S), 'context'(C) and 'stateMachine'(T) can be used in MVEL scripts
    @OnTransitionBegin(when="event.name().equals(\"ToB\")")
    public void transitionBeginConditional() {
        // method will be invoked when transition begin while transition caused by event "toB"
        System.out.println("[StateListener]------transitionBeginConditional");
    }

    // 状态转换结束时执行
    @OnTransitionComplete
    public void transitionComplete(MyState from, MyState to, MyEvent event, MyContext context) {
        // method annotated with TransitionComplete will be invoked when transition complete...
        System.out.println("[StateListener]------transitionComplete");
    }


    @OnTransitionDecline
    public void transitionDeclined(MyState from, MyEvent event, MyContext context) {
        // method annotated with TransitionDecline will be invoked when transition declined...
        System.out.println("[StateListener]------transitionDeclined");
    }

    @OnBeforeActionExecuted
    public void onBeforeActionExecuted(MyState sourceState, MyState targetState,
                                       MyEvent event, MyContext context, int[] mOfN, Action<?, ?, ?,?> action) {
        // method annotated with OnAfterActionExecuted will be invoked before action invoked
        System.out.println("[StateListener]------onBeforeActionExecuted");
    }

    @OnAfterActionExecuted
    public void onAfterActionExecuted(Object sourceState, Object targetState,
                                      Object event, Object context, int[] mOfN, Action<?, ?, ?,?> action) {
        // method annotated with OnAfterActionExecuted will be invoked after action invoked
        System.out.println("[StateListener]------onAfterActionExecuted");
    }

    @OnActionExecException
    public void onActionExecException(Action<?, ?, ?,?> action, TransitionException e) {
        // method annotated with OnActionExecException will be invoked when action thrown exception
        System.out.println("[StateListener]------onActionExecException");
    }
}
