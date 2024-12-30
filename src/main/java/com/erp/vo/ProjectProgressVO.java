package com.erp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectProgressVO {

    private int taskId;                    // 고유 ID (task_id)
    private String taskName;               // 기능 이름 (task_name)
    private String taskStatus;             // 상태 (task_status)
    private int completionPercentage;      // 진행도 (completion_percentage)
}
