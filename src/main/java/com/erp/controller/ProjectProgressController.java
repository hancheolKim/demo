package com.erp.controller;

import com.erp.service.ProjectProgressService;
import com.erp.vo.ProjectProgressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ProjectProgress")
public class ProjectProgressController {

    @Autowired
    private ProjectProgressService projectProgressService;

    // 모든 진행 상태 조회
    @GetMapping("/all")
    public List<ProjectProgressVO> getAllProgress() {
        return projectProgressService.getAllProgress();
    }

    // 특정 진행 상태 조회
    @GetMapping("/{taskId}")
    public ProjectProgressVO getProgressById(@PathVariable("taskId") int taskId) {
        log.info("Fetching progress for taskId: " + taskId);
        return projectProgressService.getProgressById(taskId);
    }

    // 진행 상태 추가
    @PostMapping("/add")
    public Map<String, String> addProgress(@RequestBody ProjectProgressVO progress) {
        int result = projectProgressService.addProgress(progress);
        Map<String, String> response = new HashMap<>();
        response.put("status", result > 0 ? "success" : "fail");
        response.put("message", result > 0 ? "Progress added successfully!" : "Failed to add progress!");
        return response;
    }



    // 진행 상태 수정
    @PutMapping("/update")
    public String updateProgress(@RequestBody ProjectProgressVO progress) {
        System.out.println(progress);
        int result = projectProgressService.updateProgress(progress);
        if (result > 0) {
            return "Progress updated successfully!";
        } else {
            return "Failed to update progress!";
        }
    }

    // 진행 상태 삭제
    @DeleteMapping("/delete/{taskId}")
    public String deleteProgress(@PathVariable("taskId") int taskId) {
        int result = projectProgressService.deleteProgress(taskId);
        if (result > 0) {
            return "Progress deleted successfully!";
        } else {
            return "Failed to delete progress!";
        }
    }
}
