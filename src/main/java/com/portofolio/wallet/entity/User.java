package com.portofolio.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDateTime;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;
}
