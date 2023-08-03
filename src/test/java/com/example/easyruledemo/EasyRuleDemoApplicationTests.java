package com.example.easyruledemo;

import com.example.easyruledemo.easyrule.TaskRuleEngine;
import com.example.easyruledemo.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasyRuleDemoApplicationTests {

    @Test
    void testRuleEngine() {

        Task.TaskBuilder taskBuilder = Task.builder();
        Task task = taskBuilder.status("undo")
                .action("start")
                .title("任务")
                .build();
        TaskRuleEngine.getInstance().getTargetStatus(task);
        System.out.println(task.getTargetStatus());

    }

}
