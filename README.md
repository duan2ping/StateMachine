
# 状态机

### 概述

 ##### 状态机三要素
 <ol>状态(state)：根据业务抽象出的阶段状态</ol>
 <ol>事件(event)：触发状态过渡或满足指定要求的条件</ol>
 <ol>动作(action)：符合条件进行状态过渡执行相应的处理</ol>
 
### 框架
 
 <ol>squirrel-foundation</ol>
 
### 项目结构 

 <ol>Main：启动类，实现状态机的流程控制及状态事件动作的定义</ol>
 <ol>MyCondtion：自定义条件过滤器</ol>
 <ol>MyEvent：自定义事件枚举列表</ol>
 <ol>MyState：自定义状态枚举列表</ol>
 <ol>MyStateMachine：状态机，根据需求实现AbstractStateMachine|StateMachine|AbstractUntypedStateMachine</ol>
 <ol>StateListener：自定义监听器，可实现相应方法或使用注解定义监听事件</ol>
 <ol>StateMaker：自定义决策类，用于实现多状态选择</ol>
 <ol>declarative：该包下为声明式注解定义状态机及其实现状态流程控制</ol>
 