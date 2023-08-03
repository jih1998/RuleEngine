package com.example.easyruledemo.easyrule.constant;

public enum TaskActionEnum {
    START("start","启动"),
    COMPLETE("complete","完成"),
    RESTART("reStart","重新启动");

    private String code;

    private String desc;

    public String code(){
        return this.code;
    }

    TaskActionEnum(String code , String desc){
        this.code = code;
        this.desc = desc;
    }

    TaskActionEnum(){
    }

    public static TaskActionEnum getTaskStatusEnumByCode(String code){
        for (int i = 0; i < TaskActionEnum.values().length; i++) {
            if (TaskActionEnum.values()[i].code.equals(code)){
                return TaskActionEnum.values()[i];
            }
        }
        return null;
    }
}
