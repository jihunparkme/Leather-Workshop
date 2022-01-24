package com.leather.workshop.notice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Notice {

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

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    @Builder
    public Notice(Long memberId, String title, String contents, Long hits, LocalDateTime createDateTime) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
        this.createDateTime = createDateTime;
    }

    public void update(String title, String contents, LocalDateTime updateDateTime) {
        this.title = title;
        this.contents = contents;
        this.updateDateTime = updateDateTime;
    }
}
