package com.leather.workshop.domain.review.domain;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String nickname;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Builder
    public Review(User user, String nickname, String contents) {
        this.user = user;
        this.nickname = nickname;
        this.contents = contents;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
