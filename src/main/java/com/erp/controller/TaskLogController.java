package com.erp.controller;

import com.erp.dao.TaskLogDAO;
import com.erp.service.TaskLogService;
import com.erp.vo.TaskLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasklog")
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;

    /**
     * 최근 다섯 개의 Task Logs 가져오기
     */
    @GetMapping("/recent")
    public ResponseEntity<List<TaskLogVO>> getRecentTaskLogs() {
        try {
            List<TaskLogVO> taskLogs = taskLogService.getRecentTaskLogs();
            return new ResponseEntity<>(taskLogs, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching recent task logs", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task Logs 페이징 처리하여 가져오기
     */
    @GetMapping("/list")
    public ResponseEntity<List<TaskLogVO>> getTaskLogsByPage(@RequestParam int page, @RequestParam int size) {
        try {
            int offset = (page - 1) * size;
            List<TaskLogVO> taskLogs = taskLogService.getTaskLogsByPage(offset, size);
            return new ResponseEntity<>(taskLogs, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching paginated task logs", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 특정 Task Log 상세 정보 가져오기
     */
    @GetMapping("/{logId}")
    public ResponseEntity<TaskLogVO> getTaskLogById(@PathVariable int logId) {
        try {
            TaskLogVO taskLog = taskLogService.getTaskLogById(logId);
            if (taskLog != null) {
                return new ResponseEntity<>(taskLog, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error fetching task log by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task Log 추가
     */
    @PostMapping
    public ResponseEntity<String> insertTaskLog(@RequestBody TaskLogVO taskLog) {
        try {
            int rowsInserted = taskLogService.insertTaskLog(taskLog);
            if (rowsInserted > 0) {
                return new ResponseEntity<>("Task log added successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add task log", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error inserting task log", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task Log 수정
     */
    @PutMapping("/{logId}")
    public ResponseEntity<String> updateTaskLog(@PathVariable int logId, @RequestBody TaskLogVO taskLog) {
        try {
            taskLog.setLogId(logId);
            int rowsUpdated = taskLogService.updateTaskLog(taskLog);
            if (rowsUpdated > 0) {
                return new ResponseEntity<>("Task log updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update task log", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error updating task log", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task Log 삭제
     */
    @DeleteMapping("/{logId}")
    public ResponseEntity<String> deleteTaskLog(@PathVariable int logId) {
        try {
            int rowsDeleted = taskLogService.deleteTaskLog(logId);
            if (rowsDeleted > 0) {
                return new ResponseEntity<>("Task log deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to delete task log", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error deleting task log", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
