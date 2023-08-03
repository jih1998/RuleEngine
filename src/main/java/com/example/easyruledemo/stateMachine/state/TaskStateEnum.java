package com.example.easyruledemo.stateMachine.state;

public enum TaskStateEnum {
    UNDO("undo","未开始"),
    DOING("doing","执行中"),
    CONFIRMED("confirmed","已确认"),
    DONE("done","已完成");

    private String code;

    private String desc;

    public String code(){
        return this.code;
    }

    TaskStateEnum(String code , String desc){
        this.code = code;
        this.desc = desc;
    }

    TaskStateEnum(){
    }

    public static TaskStateEnum getTaskStateEnumByCode(String code){
        for (int i = 0; i < TaskStateEnum.values().length; i++) {
            if (TaskStateEnum.values()[i].code.equals(code)){
                return TaskStateEnum.values()[i];
            }
        }
        return null;
    }
}
