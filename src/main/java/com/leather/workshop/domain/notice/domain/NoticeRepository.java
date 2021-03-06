package com.leather.workshop.domain.notice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT DISTINCT n FROM Notice n ORDER BY n.id DESC")
    List<Notice> findAllDesc();
}
