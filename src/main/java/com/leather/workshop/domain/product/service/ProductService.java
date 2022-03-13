package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.product.domain.*;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import com.leather.workshop.global.common.util.file.FileUtilities;
import com.leather.workshop.global.common.util.web.dto.UploadFile;
import com.leather.workshop.global.config.security.dto.SessionUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Service
public class ProductService {

    private final ProductCategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final FileUtilities fileUtilities;

    @Transactional(readOnly = true)
    public Page<Product> findAllSortByIdDescPaging(String category, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductPagination(category, pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto.Response findById(Long id) {
        Product product = getProduct(id);

        return new ProductDto.Response(product);
    }

    @Transactional
    public Long save(ProductDto.Request form, SessionUser user) throws IOException {

        ProductCategory category = categoryRepository.findById(form.getProductCategory()).get();

        List<ProductUploadFile> productUploadFileList = new ArrayList<>();

        MultipartFile formThumbnailFile = form.getThumbnailFile();
        UploadFile uploadFile = fileUtilities.storeFile(formThumbnailFile, "product");
        ProductUploadFile productThumbnailFile = ProductUploadFile.builder()
                .uploadFileName(uploadFile.getUploadFileName())
                .storeFileName(uploadFile.getStoreFileName())
                .thumbnailYn(BooleanFormatType.Y)
                .build();

        List<MultipartFile> formUploadFiles = form.getProductUploadFiles();
        if (!formUploadFiles.isEmpty()) {
            List<UploadFile> uploadFiles = fileUtilities.storeFiles(formUploadFiles, "product");
            productUploadFileList = uploadFiles.stream()
                    .map(up -> {
                        return ProductUploadFile.builder()
                                .uploadFileName(up.getUploadFileName())
                                .storeFileName(up.getStoreFileName())
                                .thumbnailYn(BooleanFormatType.N)
                                .build();
                    })
                    .collect(Collectors.toList());
        }

        productUploadFileList.add(productThumbnailFile);

        Product product = Product.builder()
                .productCategory(category)
                .name(form.getName())
                .contents(form.getContents())
                .hits(0L)
                .deleteYn(BooleanFormatType.N)
                .userId(user.getId())
                .productUploadFiles(new HashSet<>(productUploadFileList))
                .build();

        return productRepository.save(product).getId();
    }

//    @Transactional
//    public Long update(Long id, ProductDto.SaveRequest productRequest) {
//        Product product = getProduct(id);
//
//        product.update(productRequest.getProductCategory(), productRequest.getName(),
//                productRequest.getContents(), productRequest.getProductUploadFiles());
//
//        return id;
//    }

    @Transactional
    public void delete(Long id) {
        Product product = getProduct(id);

        product.delete();
        productRepository.save(product);
    }

    private Product getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 상품이이 존재하지 않습니다. id=" + id));

        return product;
    }
}
