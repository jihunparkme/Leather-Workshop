package com.leather.workshop.domain.notice.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Long hits;

    @Builder
    public Notice(Long memberId, String title, String contents, Long hits) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}