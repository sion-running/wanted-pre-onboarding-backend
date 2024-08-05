package com.wanted.recruitment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at is NULL")
@SQLDelete(sql = "UPDATE #{#entityName} SET deleted_at = NOW() WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;

    @Column(columnDefinition = "bigint NOT NULL COMMENT '회사 ID'")
    private Long companyId;

    @Column(columnDefinition = "varchar NOT NULL COMMENT '포지션'")
    private String position;

    @Column(columnDefinition = "int NOT NULL COMMENT '채용 보상금'")
    private Integer reward;

    @Column(columnDefinition = "varchar NOT NULL COMMENT '채용 내용'")
    private String description;

    @Column(columnDefinition = "varchar NOT NULL COMMENT '채용 내용'")
    private String skills;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP NOT NULL COMMENT '생성일자'")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'")
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "datetime default NULL COMMENT '삭제일자'")
    private LocalDateTime deletedAt;
}
