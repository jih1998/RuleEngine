package com.example.easyruledemo.stateMachine.event;

public enum TaskEventEnum {
    CREATE("create","创建"),
    START("start","启动"),
    COMPLETE("complete","完成"),
    CONFIRM("confirm","确认"),
    RESTART("reStart","重新启动");

    private String code;

    private String desc;

    public String code(){
        return this.code;
    }

    TaskEventEnum(String code , String desc){
        this.code = code;
        this.desc = desc;
    }

    TaskEventEnum(){
    }

    public static TaskEventEnum getTaskEventEnumByCode(String code){
        for (int i = 0; i < TaskEventEnum.values().length; i++) {
            if (TaskEventEnum.values()[i].code.equals(code)){
                return TaskEventEnum.values()[i];
            }
        }
        return null;
    }
}
