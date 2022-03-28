package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.product.domain.*;
import com.leather.workshop.domain.product.web.dto.ProductCategoryDto;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.domain.product.web.dto.ProductUploadFileDto;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import com.leather.workshop.global.common.util.file.FileUtilities;
import com.leather.workshop.global.common.util.web.dto.UploadFile;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductCategoryRepository categoryRepository;

    @Mock
    private ProductUploadFileRepository uploadFileRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FileUtilities fileUtilities;

    @InjectMocks
    private ProductService productService;

    private Long id = 10L;
    private BooleanFormatType deleteYn = BooleanFormatType.N;
    private String name = "상품명";
    private String contents = "상품 내용";
    private Long hits = 10L;
    private Long userId = 1L;

    private Long ctgId = 3L;
    private String ctgTitle = "카테고리명";
    private Integer ctgOrderNo = 2;
    private BooleanFormatType categoryUseYn = BooleanFormatType.Y;
    private ProductCategory productCategory = ProductCategory.builder()
                                                    .id(ctgId)
                                                    .title(ctgTitle)
                                                    .orderNo(ctgOrderNo)
                                                    .categoryUseYn(categoryUseYn)
                                                    .build();

    private Long ufId = 5L;
    private String uploadFileName = "uploadFileName";
    private String storeFileName = "storeFileName";
    private BooleanFormatType thumbnailYn = BooleanFormatType.Y;
    private ProductUploadFile productUploadFiles = ProductUploadFile.builder()
                            .id(ufId)
                            .uploadFileName(uploadFileName)
                            .storeFileName(storeFileName)
                            .thumbnailYn(thumbnailYn)
                            .build();

    private Product product = Product.builder()
                                .id(id)
                                .productCategory(productCategory)
                                .name(name)
                                .contents(contents)
                                .hits(hits)
                                .deleteYn(deleteYn)
                                .userId(userId)
                                .productUploadFiles(Set.of(productUploadFiles))
                                .build();

    private User admin = User.builder()
            .id(userId)
            .name("Aaron")
            .email("jihunpark.tech@gmail.com")
            .picture("")
            .role(Role.ADMIN)
            .build();

    @Test
    @DisplayName("상품 조회 성공")
    void findById() {

        lenient().when(productRepository.findById(any())).thenReturn(Optional.of(product));

        ProductDto.Response response = productService.findById(id);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getProductCategory().getTitle()).isEqualTo(new ProductCategoryDto.Response(productCategory).getTitle());
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getContents()).isEqualTo(contents);
        assertThat(response.getHits()).isEqualTo(hits);
        assertThat(response.getDeleteYn()).isEqualTo(deleteYn);
        assertThat(response.getUserId()).isEqualTo(userId);
        assertThat(response.getProductUploadFiles().get(0).getStoreFileName())
                .isEqualTo(new ProductUploadFileDto.Response(productUploadFiles).getStoreFileName());

        verify(productRepository).findById(any());
    }

    @Test
    @DisplayName("상품 저장 성공")
    void save() throws IOException {

        ProductDto.SaveRequest saveRequest = ProductDto.SaveRequest.builder()
                .productCategory(1L)
                .name(name)
                .contents(contents)
                .thumbnailFile(new MockMultipartFile("portfolio-9.jpg", new FileInputStream(new File("/Users/aaron/uploadFiles/product/portfolio-9.jpg"))))
                .build();

        lenient().when(productRepository.save(any())).thenReturn(Optional.of(product).get());
        lenient().when(categoryRepository.findById(any())).thenReturn(Optional.of(productCategory));
        lenient().when(fileUtilities.storeFile(any(), any())).thenReturn(new UploadFile("portfolio-9.jpg", "portfolio-9.jpg"));

        productService.save(saveRequest, new SessionUser(admin));

        verify(productRepository).save(any());
        verify(categoryRepository).findById(any());
        verify(fileUtilities).storeFile(any(), any());
    }

    @Test
    @DisplayName("상품 수정 성공")
    void edit() throws IOException {

        ProductDto.UpdateRequest updateRequest = ProductDto.UpdateRequest.builder()
                .id(1L)
                .productCategory(productCategory)
                .name(name)
                .contents(contents)
                .thumbnailFile(new MockMultipartFile("portfolio-9.jpg", new FileInputStream(new File("/Users/aaron/uploadFiles/product/portfolio-9.jpg"))))
                .build();

        lenient().when(productRepository.findById(any())).thenReturn(Optional.of(product));
        lenient().when(productRepository.save(any())).thenReturn(Optional.of(product).get());

        productService.edit(id, updateRequest, new SessionUser(admin));

        verify(productRepository).findById(any());
        verify(productRepository).save(any());
    }

    @Test
    @DisplayName("상품 삭제 성공")
    void delete() {

        lenient().when(productRepository.findById(any())).thenReturn(Optional.of(product));
        lenient().when(productRepository.save(any())).thenReturn(Optional.of(product).get());

        productService.delete(id);

        verify(productRepository).findById(any());
        verify(productRepository).save(any());
    }
}