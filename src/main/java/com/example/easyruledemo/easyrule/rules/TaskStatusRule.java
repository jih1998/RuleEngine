package com.example.easyruledemo.easyrule.rules;

import cn.hutool.core.util.ObjectUtil;
import com.example.easyruledemo.easyrule.constant.TaskActionEnum;
import com.example.easyruledemo.easyrule.constant.TaskStatusEnum;
import com.example.easyruledemo.entity.Task;
import com.example.easyruledemo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Slf4j
public class TaskStatusRule {

    @Rule(name = "StatusRule",description = "Return target Status By current status",priority = 1)
    public static class TargetStatusRule{


        /**
         * 规则条件
         * @param task 任务信息
         * @return
         */
        @Condition
        public boolean condition(@Fact("task")Task task){
            TaskStatusEnum taskStatusEnumByCode = TaskStatusEnum.getTaskStatusEnumByCode(task.getStatus());
            if (ObjectUtil.isEmpty(taskStatusEnumByCode)){
                return false;
            }
            return true;
        }

        @Action(order = 1)
        public void actionFirst(Facts facts){
            Task task = facts.get("task");
            if (ObjectUtil.equal(task.getStatus(),TaskStatusEnum.UNDO.code)){
                if (ObjectUtil.equal(task.getAction(), TaskActionEnum.START.code())){
                    task.setTargetStatus(TaskStatusEnum.DOING.code);
                }else {
                    log.info("未启动任务传入动作数据错误");
                    throw new BusinessException("未启动任务传入动作数据错误");
                }
            }else if (ObjectUtil.equal(task.getStatus(),TaskStatusEnum.DOING.code)){
                if (ObjectUtil.equal(task.getAction(), TaskActionEnum.COMPLETE.code())){
                    task.setTargetStatus(TaskStatusEnum.DONE.code);
                }else {
                    log.info("执行中任务传入动作数据错误");
                    throw new BusinessException("执行中任务传入动作数据错误");
                }
            }else if (ObjectUtil.equal(task.getStatus(),TaskStatusEnum.DONE.code)){
                if (ObjectUtil.equal(task.getAction(), TaskActionEnum.RESTART.code())){
                    task.setTargetStatus(TaskStatusEnum.DOING.code);
                }else {
                    log.info("已完成任务传入动作数据错误");
                    throw new BusinessException("已完成任务传入动作数据错误");
                }
            }else {
                throw new BusinessException("数据错误");
            }
        }

        @Action(order = 2)
        public void actionSecond(Facts facts){
            Task task = facts.get("task");
            System.out.println("目标状态为："+task.getTargetStatus());
        }
    }
}
