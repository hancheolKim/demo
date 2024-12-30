package com.erp.service;

import com.erp.dao.ProjectProgressDAO;
import com.erp.vo.ProjectProgressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectProgressServiceImpl implements ProjectProgressService {

    @Autowired
    ProjectProgressDAO projectProgressDAO;

    @Override
    public List<ProjectProgressVO> getAllProgress() {
        return projectProgressDAO.getAllProgress();
    }

    @Override
    public ProjectProgressVO getProgressById(int taskId) {
        return projectProgressDAO.getProgressById(taskId);
    }

    @Override
    public int addProgress(ProjectProgressVO progress) {
        return projectProgressDAO.addProgress(progress);
    }

    @Override
    public int updateProgress(ProjectProgressVO progress) {
        return projectProgressDAO.updateProgress(progress);
    }

    @Override
    public int deleteProgress(int taskId) {
        return projectProgressDAO.deleteProgress(taskId);
    }
}
