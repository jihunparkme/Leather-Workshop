package com.leather.workshop.domain.review.domain;

import com.leather.workshop.domain.login.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Builder
    public Review(User user, String contents) {
        this.user = user;
        this.contents = contents;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
