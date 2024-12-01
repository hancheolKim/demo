package com.example.demo.dao;

import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 예시: 데이터베이스에서 한 명의 사용자 정보를 조회하는 메서드
    public UserVO getUser(int id) {
        String sql = "SELECT id, name, email, created_at FROM users WHERE id = ?";  // 수정된 테이블명 사용

        RowMapper<UserVO> rowMapper = (rs, rowNum) -> {
            UserVO userVO = new UserVO();
            userVO.setId(rs.getInt("id"));
            userVO.setName(rs.getString("name"));
            userVO.setEmail(rs.getString("email"));
            userVO.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());  // Timestamp를 LocalDateTime으로 변환
            return userVO;
        };

        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            return null;  // 사용자 없음
        }
    }

    // 예시: 모든 사용자 정보를 조회하는 메서드
    public List<UserVO> getAllUsers() {
        String sql = "SELECT id, name, email, created_at FROM users";  // 수정된 테이블명 사용

        RowMapper<UserVO> rowMapper = (rs, rowNum) -> {
            UserVO userVO = new UserVO();
            userVO.setId(rs.getInt("id"));
            userVO.setName(rs.getString("name"));
            userVO.setEmail(rs.getString("email"));
            userVO.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());  // Timestamp를 LocalDateTime으로 변환
            return userVO;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }

    // 예시: 사용자 추가
    public int addUser(UserVO userVO) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";  // 수정된 테이블명 사용
        return jdbcTemplate.update(sql, userVO.getName(), userVO.getEmail());
    }

    // 예시: 사용자 수정
    public int updateUser(UserVO userVO) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";  // 수정된 테이블명 사용
        return jdbcTemplate.update(sql, userVO.getName(), userVO.getEmail(), userVO.getId());
    }

    // 예시: 사용자 삭제
    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";  // 수정된 테이블명 사용
        return jdbcTemplate.update(sql, id);
    }
}
