package com.example.easyruledemo.easyrule.listener;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineListener;

/**
 * 规则引擎监听器
 */
public class MyRulesEngineListener implements RulesEngineListener {

    /**
     *  规则集合执行之前
     * @param rules 规则
     * @param facts 事实
     */
    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {
        RulesEngineListener.super.beforeEvaluate(rules, facts);
    }

    /**
     *  规则集合执行之后
     * @param rules 规则
     * @param facts 事实
     */
    @Override
    public void afterExecute(Rules rules, Facts facts) {
        RulesEngineListener.super.afterExecute(rules, facts);
    }
}
