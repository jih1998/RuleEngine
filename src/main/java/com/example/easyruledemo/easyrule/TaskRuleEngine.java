package com.example.easyruledemo.easyrule;

import com.example.easyruledemo.easyrule.listener.MyRulesEngineListener;
import com.example.easyruledemo.easyrule.listener.StatusRuleListener;
import com.example.easyruledemo.easyrule.rules.TaskStatusRule;
import com.example.easyruledemo.entity.Task;
import com.example.easyruledemo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

@Slf4j
public class TaskRuleEngine {

    // 实例
    private static TaskRuleEngine instance;

    // 规则引擎
    private static DefaultRulesEngine rulesEngine;

    //规则命名空间
    private static Rules rules= new Rules();

    /**
     * 初始化规则引擎
     */
    private TaskRuleEngine(){
        if (rulesEngine == null){

            RulesEngineParameters parameters = new RulesEngineParameters()
                    .skipOnFirstAppliedRule(true)  // 告诉引擎规则被触发时跳过后面的规则。
                    .skipOnFirstFailedRule(true)   // 告诉引擎在规则失败时跳过后面的规则。
                    .skipOnFirstNonTriggeredRule(true);  // 告诉引擎一个规则不会被触发跳过后面的规则。


            //InferenceRulesEngine：持续对已知事实应用规则，直到不再应用规则为止
            // 会通过循环不停的通过事实去匹配规则，直到全部事实都不匹配规则才停止循环
            //rulesEngine = new InferenceRulesEngine();

            //DefaultRulesEngine：根据规则的自然顺序（默认为优先级）应用规则。
            // 按照优先级进行注册规则，从大到小执行
            // 注册 规则监听器
            rulesEngine = new DefaultRulesEngine();
            StatusRuleListener statusRuleListener = new StatusRuleListener();
            rulesEngine.registerRuleListener(statusRuleListener);
            // 注册 规则引擎监听器
            rulesEngine.registerRulesEngineListener(new MyRulesEngineListener());
        }
    }

    /**
     * 获取规则引擎实例
     */
    public static TaskRuleEngine getInstance(){
        if (instance == null){
            instance = new TaskRuleEngine();
        }
        if (rules.isEmpty()){
            rules = new Rules();
            rules.register(new TaskStatusRule.TargetStatusRule());
        }

        return instance;
    }

    /**
     * 获取目标状态
     * @param task
     */
    public void getTargetStatus(Task task){
        // 参数校验
        Boolean handleParams = handleParams(task);
        if (!handleParams){
            throw new BusinessException("参数错误");
        }
        Facts facts = new Facts();
        facts.put("task",task);
        rulesEngine.fire(rules,facts);
    }

    /**
     * 参数校验
     * @param task
     * @return
     */
    private  Boolean handleParams(Task task) {
        // 校验通过
        return true;
    }

}
