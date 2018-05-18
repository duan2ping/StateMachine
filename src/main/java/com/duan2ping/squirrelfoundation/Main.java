package com.duan2ping.squirrelfoundation;

import org.squirrelframework.foundation.component.SquirrelProvider;
import org.squirrelframework.foundation.fsm.DotVisitor;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachinePerformanceMonitor;

/**
 * 【状态机】
 * 状态的过渡转换
 *
 * 现态 -----> 次态(通过对事件的触发执行相应的动作实现状态的过渡转换即 A-----Event-----Action----->B)
 *
 *状态转换类型
 *     ① Internal Transitionv  内部转换
 *     ② Local Transition      本地转换
 *     ③ External Transition   外部转换
 * Created by duan2ping on 2018/5/16.
 */
public class Main {

    public static void main(String[] args) {

        // 状态机上下文
        MyContext context = new MyContext();
        // 自定义监听器
        StateListener listener = new StateListener();
        // 自定义条件过滤器
        MyCondition condition = new MyCondition();

        // 3. 构建状态转换
        // StateMachineBuilder：状态机生成器
        // 使用Factory创建状态机生成器需要：状态机类型，状态类型，事件类型，上下文类型
        StateMachineBuilder<MyStateMachine, MyState, MyEvent, MyContext> builder = StateMachineBuilderFactory.create(MyStateMachine.class, MyState.class, MyEvent.class, MyContext.class);

        // 添加结束状态事件
        //builder.defineFinishEvent(MyEvent.ToC);

        // 【配置状态机：state，transition，action，external Transition】
        // 【状态A】
        // 指定状态机触发状态过渡的事件以及动作
        // 使用代码方式实现，也可以直接在状态机实现类使用注解实现
        // 进入A状态时执行的动作(callMethod：动作，onEntry：事件[内置的事件])
        builder.onEntry(MyState.A).callMethod("entryStateA");
        // 退出A状态时执行的动作(callMethod：动作，onExit：事件[内置的事件])
        builder.onExit(MyState.A).callMethod("exitStateA");

        builder.onEntry(MyState.Achild1).callMethod("entryStateAChild1");
        builder.onExit(MyState.Achild1).callMethod("exitStateAChild1");

        builder.onEntry(MyState.Achild2).callMethod("entryStateAChild2");
        builder.onExit(MyState.Achild2).callMethod("exitStateAChild2");

        // 配置父状态下串行子状态，Achild1默认先执行，所以只需配置Achild1到Achild2的状态转移即可(A--->AChild1--->Achild2)
        builder.defineSequentialStatesOn(MyState.A,MyState.Achild1,MyState.Achild2);
        builder.externalTransition().from(MyState.Achild1).to(MyState.Achild2).on(MyEvent.ToAchild2).callMethod("fromAToAChild2");

        // 【状态B,A---->B】
        // 从状态A过渡到状态B执行的动作(externalTransition：配置过渡的方法，callMethod：动作，on：配置事件)
        // MVEL来描述条件，:::分离条件名称和表达式，满足则状态转移，反之状态不转移
        builder.externalTransition().from(MyState.A).to(MyState.B).on(MyEvent.ToB).
                whenMvel("MyCondition:::(1==1 && 2>1)").callMethod("stateAToStateBOnGotoB");
        // 进入B状态时执行的动作
        builder.onEntry(MyState.B).callMethod("entryStateB");
        // 退出B状态时执行的动作
        builder.onExit(MyState.B).callMethod("exitStateB");

        // 【状态C,B---->C---->(C1--->C1Child1--->C1Child2&C2--->C2Child1--->C2Child2】
        // 由状态B转移到状态C
        StateMaker maker = new StateMaker(); // 状态分支决策类，会自动回调execute方法
        builder.externalTransition().from(MyState.B).to(MyState.C).on(MyEvent.ToC).
                when(condition).callMethod("stateBToStateCOnGotoC");
        builder.onEntry(MyState.C).callMethod("entryStateC");

        // 设置状态转移的决策类
        builder.onEntry(MyState.C).perform(maker);
        builder.onExit(MyState.C).callMethod("exitStateC");
        // 由状态C转移到状态C1或C2，实现状态的分叉(toAmong的状态和onEach的事件对应，都为可变长参数即可实现多分支)
        //builder.transitions().from(MyState.C).toAmong(MyState.C1, MyState.C2)
               // .onEach(MyEvent.ToC1, MyEvent.ToC2).callMethod("stateCToStateC1OnGotoC1|stateCToStateC2OnGotoC2");

        // 配置一个父状态C和两个并行的子状态C1&C2
        builder.defineParallelStatesOn(MyState.C,MyState.C1,MyState.C2);

        // 配置父状态下串行子状态(C1--->C1Child1--->C1Child2 && C2--->C2Child1--->C2Child2)
        builder.defineSequentialStatesOn(MyState.C1,MyState.C1Child1,MyState.C1Child2);
        builder.defineSequentialStatesOn(MyState.C2,MyState.C2Child1,MyState.C2Child2);
        builder.externalTransition().from(MyState.C1Child1).to(MyState.C1Child2).on(MyEvent.ToC1Child2).callMethod("fromC1Child1ToC1Child2");
        builder.externalTransition().from(MyState.C2Child1).to(MyState.C2Child2).on(MyEvent.ToC2Child2).callMethod("fromC2Child1ToC2Child2");

        // 【状态C1】
        builder.onEntry(MyState.C1).callMethod("entryStateC1");
        builder.onExit(MyState.C1).callMethod("exitStateC1");

        // 【状态C2】
        builder.onEntry(MyState.C2).callMethod("entryStateC2");
        builder.onExit(MyState.C2).callMethod("exitStateC2");


        // 4. 使用状态机
        // 使用状态机生成器创建状态机并指定初始状态
        MyStateMachine fsm = builder.newStateMachine(MyState.A);

        // 开启日志
        //StateMachineLogger fsmLogger =  new StateMachineLogger(fsm);
        //fsmLogger.startLogging();


        // 状态机配置监听器
        //fsm.addDeclarativeListener(listener);

        // 启动
        fsm.start();

        //fsm.fire(MyEvent.ToAchild2,context);

        // 触发状态机状态转换：状态转换到B[可根据事件转换，或者上下文转换]
        fsm.fire(MyEvent.ToB,context);

        // 触发状态机状态转换：状态转换到C
        fsm.fire(MyEvent.ToC,context);
        fsm.fire(MyEvent.ToC1Child2);
        fsm.fire(MyEvent.ToC2Child2);


        // 【生成状态机状态图】
        // 生成dot，使用GVEdit可查看状态图[可用fsm.exportXMLDefinition(true)生成xml]
        // 也可使用new UntypedStateMachineImporter().importDefinition("")导入xml定义的或SCXMLVisitor画出的状态图生成状态机;
        DotVisitor visitor = SquirrelProvider.getInstance().newInstance(DotVisitor.class);
        fsm.accept(visitor);
        visitor.convertDotFile("MyStateMachine");

        // 结束日志
        //fsmLogger.stopLogging();


        // 状态机监控，统计
        StateMachinePerformanceMonitor performanceMonitor =
                new StateMachinePerformanceMonitor("My State Machine Performance Info");
        fsm.addDeclarativeListener(performanceMonitor);


        //fsm.removeDeclarativeListener(performanceMonitor);

        //System.out.println(performanceMonitor.getPerfModel());

        //
        System.out.println("父状态下的并行子状态："+fsm.getSubStatesOn(MyState.C));
        System.out.println("Current state is ["+fsm.getCurrentState()+"]");

        // 终止
        //fsm.terminate();
    }

}
