package com.leather.workshop.domain.notice.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long hits;

    private LocalDateTime modifiedDateTime;

    @Builder
    public Notice(Long userId, String title, String contents, Long hits) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.modifiedDateTime = LocalDateTime.now();
    }

    public void countHits() {
        this.hits += 1;
    }
}
