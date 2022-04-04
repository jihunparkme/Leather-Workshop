package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.product.domain.*;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import com.leather.workshop.global.common.util.file.FileUtilities;
import com.leather.workshop.global.common.util.file.PathConst;
import com.leather.workshop.global.common.util.web.dto.UploadFile;
import com.leather.workshop.global.config.security.dto.SessionUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class ProductService {

    private final ProductCategoryRepository categoryRepository;

    private final ProductUploadFileRepository uploadFileRepository;

    private final ProductRepository productRepository;

    private final FileUtilities fileUtilities;

    @Transactional(readOnly = true)
    public Page<Product> findAllSortByIdDescPaging(String category, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductPagination(category, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findTop12OrderByIdDesc() {
        PageRequest pageable = PageRequest.of(0, 12, Sort.by("id").descending());
        return productRepository.findAllProductPagination("ALL", pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto.Response findById(Long id) {
        Product product = getProduct(id);

        return new ProductDto.Response(product);
    }

    @Transactional
    public Long save(ProductDto.SaveRequest form, SessionUser user) throws IOException {

        ProductCategory category = categoryRepository.findById(form.getProductCategory()).get();

        Product product = Product.builder()
                .productCategory(category)
                .name(form.getName())
                .contents(form.getContents())
                .hits(0L)
                .deleteYn(BooleanFormatType.N)
                .userId(user.getId())
                .build();

        MultipartFile formThumbnailFile = form.getThumbnailFile();
        UploadFile uploadFile = fileUtilities.storeFile(formThumbnailFile, PathConst.PRODUCT);
        ProductUploadFile productThumbnailFile = ProductUploadFile.builder()
                .product(product)
                .uploadFileName(uploadFile.getUploadFileName())
                .storeFileName(uploadFile.getStoreFileName())
                .thumbnailYn(BooleanFormatType.Y)
                .build();
        product.addProductUploadFiles(productThumbnailFile);

        List<MultipartFile> formUploadFiles = form.getProductUploadFiles();
        if (formUploadFiles != null && !formUploadFiles.isEmpty()) {
            List<UploadFile> uploadFiles = fileUtilities.storeFiles(formUploadFiles, PathConst.PRODUCT);
            uploadFiles.stream()
                    .map(up -> {
                        ProductUploadFile puf = ProductUploadFile.builder()
                                .product(product)
                                .uploadFileName(up.getUploadFileName())
                                .storeFileName(up.getStoreFileName())
                                .thumbnailYn(BooleanFormatType.N)
                                .build();

                        product.addProductUploadFiles(productThumbnailFile);
                        return null;
                    });
        }

        return productRepository.save(product).getId();
    }

    @Transactional
    public void edit(Long id, ProductDto.UpdateRequest form, SessionUser user) throws IOException {

        Product product = productRepository.findById(id).get();

        checkNewThumbnail(id, form, product);

        List<ProductUploadFile> productUploadFiles = new ArrayList<>();
        product.update(form.getProductCategory(), form.getName(), form.getContents(), new HashSet<>(productUploadFiles));

        productRepository.save(product).getId();
    }

    @Transactional
    public void delete(Long id) {
        Product product = getProduct(id);
        product.delete();

        productRepository.save(product);
    }

    private void checkNewThumbnail(Long id, ProductDto.UpdateRequest form, Product product) throws IOException {
        if (!form.getThumbnailFile().getOriginalFilename().isEmpty()) {
            ProductUploadFile productUploadFile = uploadFileRepository.findProductThumbnail(id).get().get(0);

            if (form.getThumbnailFile().getOriginalFilename() != productUploadFile.getUploadFileName()) {
                uploadFileRepository.delete(productUploadFile);

                UploadFile uploadFile = fileUtilities.storeFile(form.getThumbnailFile(), PathConst.PRODUCT);
                ProductUploadFile productThumbnailFile = ProductUploadFile.builder()
                        .product(product)
                        .uploadFileName(uploadFile.getUploadFileName())
                        .storeFileName(uploadFile.getStoreFileName())
                        .thumbnailYn(BooleanFormatType.Y)
                        .build();
                uploadFileRepository.save(productThumbnailFile);
            }
        }
    }

    private Product getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 상품이이 존재하지 않습니다. id=" + id));

        return product;
    }
}
