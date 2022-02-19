package com.leather.workshop.domain.review.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Builder
    public Review(Long userId, String contents) {
        this.userId = userId;
        this.contents = contents;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
