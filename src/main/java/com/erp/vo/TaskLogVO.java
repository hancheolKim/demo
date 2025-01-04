package com.erp.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TaskLogVO {
    private int logId;
    private int taskId;
    private String taskName;
    private String title;
    private String description;
    private Date taskDate;
    private Date uptDate;
}
