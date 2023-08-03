package com.example.easyruledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    private Integer id;

    private String type;

    private String title;

    private String status;

    private String action;

    private String targetStatus;
}
