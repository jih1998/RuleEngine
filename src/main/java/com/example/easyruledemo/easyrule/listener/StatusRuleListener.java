package com.example.easyruledemo.easyrule.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

@Slf4j
public class StatusRuleListener implements RuleListener {

    /**
     * 该方法在执行@Condition修饰的方法之前执行。该方法返回false则不执行条件的判断，直接跳过该当前rule。
     *
     * @param rule 正在被评估的规则
     * @param facts 评估规则之前的已知事实
     * @return 如果规则应该评估，则返回true，否则返回false
     */
    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
        log.info("在评估规则之前触发。");
        return RuleListener.super.beforeEvaluate(rule, facts);
    }

    /**
     * 该方法在执行@Condition修饰的方法之后执行。
     *
     * @param rule 评估之后的规则
     * @param facts 评估规则之后的已知事实
     * @param evaluationResult 评估结果
     */
    @Override
    public void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {
        log.info("在评估规则之后触发。");
        RuleListener.super.afterEvaluate(rule, facts, evaluationResult);
    }

    /**
     * 在执行@Action修饰的方法出现异常时，该方法执行。
     *
     * @param rule 评估之后的规则
     * @param facts 评估时的已知事实
     * @param exception 条件评估时发生的异常
     */
    @SneakyThrows
    @Override
    public void onEvaluationError(Rule rule, Facts facts, Exception exception) {
        log.info("运行时异常导致条件评估错误时触发。");
        throw exception;
    }

    /**
     * 该方法在执行@Action修饰的方法之前执行。
     *
     * @param rule 当前的规则
     * @param facts 执行规则操作时的已知事实
     */
    @Override
    public void beforeExecute(Rule rule, Facts facts) {
        log.info("在规则操作执行之前触发。");
        RuleListener.super.beforeExecute(rule, facts);
    }

    /**
     * 该方法在执行@Action修饰的方法之后执行。
     *
     * @param rule t当前的规则
     * @param facts 执行规则操作时的已知事实
     */
    @Override
    public void onSuccess(Rule rule, Facts facts) {
        log.info("在规则操作成功执行之后触发。");
        RuleListener.super.onSuccess(rule, facts);
    }

    /**
     * 在规则操作执行失败时触发
     *
     * @param rule 当前的规则
     * @param facts 执行规则操作时的已知事实
     * @param exception 执行规则操作时发生的异常
     */
    @Override
    public void onFailure(Rule rule, Facts facts, Exception exception) {
        log.info("在规则操作执行失败时触发。");
        RuleListener.super.onFailure(rule, facts, exception);
    }
}
