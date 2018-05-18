package com.duan2ping.squirrelfoundation.declarative;

import com.duan2ping.squirrelfoundation.MyContext;
import com.duan2ping.squirrelfoundation.MyEvent;
import com.duan2ping.squirrelfoundation.MyState;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**注解声明式状态机
 * Created by duan2ping on 2018/5/17.
 */
// 注解可以扩展状态机的定义,框架会结合注解和代码的配置(配置是累加的。比如注解配置entryCallMethod会执行，代码配置entryCallMethod也会执行)
// 定义状态机的所有状态[name：状态名，entryCallMethod：进入状态执行的方法，exitCallMethod：退出状态执行的方法]
@States({
        @State(name="A", entryCallMethod="entryStateA", exitCallMethod="exitStateA", initialState = true),
        @State(name="B", entryCallMethod="entryStateB", exitCallMethod="exitStateB"),
        @State(name="C", entryCallMethod="entryStateC", exitCallMethod="exitStateC"),
})
// 状态的过渡/切换[from：现态，to：次态，on：事件/条件，callMethod：动作，TransitionType.INTERNAL：内部状态过渡，即进入/退出状态触发的动作]
@Transitions({
        @Transit(from="A", to="B", on="ToB", callMethod="stateAToStateBOnGotoB",whenMvel="MyCondition:::(1==1 && 2>1)"),
        @Transit(from="B", to="C", on="ToC", callMethod="stateBToStateCOnGotoC")
})
// @States和@Transitions,可以理解为将event 或 state 和 action 做了一次映射
@StateMachineParameters(stateType=MyState.class, eventType=MyEvent.class, contextType=MyContext.class)
public class DeclarativeStateMachine extends AbstractUntypedStateMachine{


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
