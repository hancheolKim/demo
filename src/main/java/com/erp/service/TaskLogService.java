package com.erp.service;

import com.erp.vo.TaskLogVO;

import java.util.List;

public interface TaskLogService {
    // Task log 리스트 가져오기 (메인 페이지에서는 제일 최근 다섯 개)
    List<TaskLogVO> getRecentTaskLogs();

    // Task log 리스트 가져오기 (프로그램 버전 관리 페이지에서는 스무 개씩 페이징)
    List<TaskLogVO> getTaskLogsByPage(int offset, int limit);

    // Task log 상세
    TaskLogVO getTaskLogById(int logId);

    // Task log 추가
    int insertTaskLog(TaskLogVO taskLog);

    // Task log 수정
    int updateTaskLog(TaskLogVO taskLog);

    // Task log 삭제
    int deleteTaskLog(int logId);
}
