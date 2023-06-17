package com.example.CRUDbasic.repository;

import com.example.CRUDbasic.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JPA 상속
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // JPA 에서 기본적으로 제공해 주는 findBy~ 메소드들은 모두 return 값이 Optional
    // Optional 과 일반 Object 의 차이는 Optional 에는 null 값이 들어갈 수 있고 일반 Object 는 null 값 불가능하므로 오류 발생
    Optional<UserEntity> findByEmail(String email);
}
