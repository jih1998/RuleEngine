package com.example.easyruledemo.easyrule.constant;

public enum TaskStatusEnum {

    UNDO("undo","未启动"),
    DOING("doing", "执行中"),
    DONE("done","已完成");

    public String code;

    public String desc;

    TaskStatusEnum(String code , String desc){
        this.code = code;
        this.desc = desc;
    }

    TaskStatusEnum(){
    }

    public static TaskStatusEnum getTaskStatusEnumByCode(String code){
        for (int i = 0; i < TaskStatusEnum.values().length; i++) {
            if (TaskStatusEnum.values()[i].code.equals(code)){
                return TaskStatusEnum.values()[i];
            }
        }
        return null;
    }
}
