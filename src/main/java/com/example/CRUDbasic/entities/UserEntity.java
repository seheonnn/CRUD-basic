package com.example.CRUDbasic.entities;

import com.example.CRUDbasic.config.RoleType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
//@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
@AllArgsConstructor // 모든 필드 받는 생성자
@NoArgsConstructor // 필드 아무것도 받지 않는 생성자
@Builder
@Data// @Getter + @Setter 인데 @Setter 안 쓰는 경우는 @Getter 만 해주는 것이 좋다 !
@DynamicUpdate
@DynamicInsert
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY, SEQUENCE, TABLE 가 있는데 IDENTITY 는 자동 증가 옵션
    private Long userId;

    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^.{5,254}$|^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$\n", message = "이메일 형식에 맞지 않습니다.")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    @ColumnDefault("'A'") // A: 활성 유저 D: 탈퇴 유저
    private char status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false) // User, Guest
    private RoleType role;

//    public UserDTO toDTO() {
//        return UserDTO.builder()
//                .email(email)
//                .password(password)
//                .name(name)
//                .status(status)
//                .role(role)
//                .created_at(created_at)
//                .updated_at(updated_at)
//                .build();
//    }

}
