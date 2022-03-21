package com.leather.workshop.domain.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductUploadFileRepository extends JpaRepository<ProductUploadFile, Long> {

    @Query(value = "SELECT DISTINCT puf " +
            "FROM ProductUploadFile puf " +
            "WHERE puf.product.id=:prodId " +
            "AND puf.thumbnailYn='Y'")
    Optional<List<ProductUploadFile>> findProductThumbnail(@Param(value = "prodId") Long prodId);
}
