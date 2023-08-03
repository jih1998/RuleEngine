package com.example.easyruledemo.stateMachine;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.example.easyruledemo.entity.Task;
import com.example.easyruledemo.stateMachine.event.TaskEventEnum;
import com.example.easyruledemo.stateMachine.state.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateMachineRegister {

    public static final String Task_STATE_MACHINE_ID = "Task_STATE_MACHINE_ID";

    public StateMachine<TaskStateEnum, TaskEventEnum, Task> stateMachine() {

        // 1.生成状态机
        StateMachineBuilder<TaskStateEnum, TaskEventEnum, Task> stateMachineBuilder = StateMachineBuilderFactory.create();
        // 2.制定规则
        // 2.1.内部流转状态规则
        stateMachineBuilder.internalTransition().within(TaskStateEnum.UNDO).on(TaskEventEnum.CREATE).when(checkParamPass()).perform(doAction());
        // 2.2.外部流转任务状态规则
        stateMachineBuilder.externalTransition().from(TaskStateEnum.UNDO).to(TaskStateEnum.DOING).on(TaskEventEnum.START).when(checkParamPass()).perform(doAction());

        stateMachineBuilder.externalTransition().from(TaskStateEnum.DOING).to(TaskStateEnum.DONE).on(TaskEventEnum.COMPLETE).when(checkParamPass()).perform(doAction());

        stateMachineBuilder.externalTransition().from(TaskStateEnum.DONE).to(TaskStateEnum.DOING).on(TaskEventEnum.RESTART).when(checkParamPass()).perform(doAction());
        // 确认
        stateMachineBuilder.externalTransition().from(TaskStateEnum.DONE).to(TaskStateEnum.CONFIRMED).on(TaskEventEnum.CONFIRM).when(checkTaskType()).perform(doAction());

        return stateMachineBuilder.build(Task_STATE_MACHINE_ID);
    }

    /**
     * 判断特殊任务类型
     * @return
     */
    private Condition<Task> checkTaskType() {
        return new Condition<Task>() {
            @Override
            public boolean isSatisfied(Task task) {
                if (ObjectUtil.equal(task.getType(),"特殊任务类型")){
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 事件触发后的统一执行
     *
     * @return
     */
    private Action<TaskStateEnum, TaskEventEnum, Task> doAction() {
        log.info("状态机触发成功");
        return null;
    }

    /**
     * 判断是否符合执行条件 默认为通过
     *
     * @return
     */
    private Condition<Task> checkParamPass() {
        return task -> true;
    }
}
