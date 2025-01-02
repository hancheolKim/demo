package com.erp.service;

import com.erp.dao.TaskLogDAO;
import com.erp.vo.TaskLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogServiceImpl implements TaskLogService{

    @Autowired
    TaskLogDAO taskLogDAO;

    @Override
    public List<TaskLogVO> getRecentTaskLogs() {
        return taskLogDAO.getRecentTaskLogs();
    }

    @Override
    public List<TaskLogVO> getTaskLogsByPage(int offset, int limit) {
        return taskLogDAO.getTaskLogsByPage(offset, limit);
    }

    @Override
    public TaskLogVO getTaskLogById(int logId) {
        return taskLogDAO.getTaskLogById(logId);
    }

    @Override
    public int insertTaskLog(TaskLogVO taskLog) {
        return taskLogDAO.insertTaskLog(taskLog);
    }

    @Override
    public int updateTaskLog(TaskLogVO taskLog) {
        return taskLogDAO.updateTaskLog(taskLog);
    }

    @Override
    public int deleteTaskLog(int logId) {
        return taskLogDAO.deleteTaskLog(logId);
    }
}
