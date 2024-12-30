package com.erp.dao;

import com.erp.vo.ProjectProgressVO;
import java.util.List;

public interface ProjectProgressDAO {
    // 모든 진행 상태 리스트 조회
    List<ProjectProgressVO> getAllProgress();

    // 특정 ID로 진행 상태 조회
    ProjectProgressVO getProgressById(int taskId);

    // 진행 상태 추가
    int addProgress(ProjectProgressVO progress);

    // 진행 상태 업데이트
    int updateProgress(ProjectProgressVO progress);

    // 진행 상태 삭제
    int deleteProgress(int taskId);
}
