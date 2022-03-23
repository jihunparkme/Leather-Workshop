package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.domain.UserRepository;
import com.leather.workshop.global.common.util.file.PathConst;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest(
        properties = {"spring.config.location=classpath:application.yml"}
)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    UserRepository userRepository;

    MockHttpSession session;

    @Value("${file.directory}")
    private String fileDir;

    @BeforeEach
    void setUp() {
        User user = userRepository.findByEmail("ccc@naver.com").get();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));
    }

    @AfterEach
    void clean() {
        session.clearAttributes();
    }

    @Test
    void 상품_리스트_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/product"));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("product/product-list"))
                .andExpect(model().attributeExists("productListPage"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("page"));
    }

    @Test
    void 상품_상세_화면() throws Exception {
        ResultActions perform = mockMvc.perform(get("/product/" + 1));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("product/product-view"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 상품_등록_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/product/add")
                .session(session));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("product/product-add"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 상품_등록() throws Exception {

        String fileName = "portfolio-7";
        String contentType = "jpg";
        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType);

        ResultActions perform = mockMvc.perform(multipart("/product/add")
                .file("thumbnailFile", mockMultipartFile.getBytes())
                .session(session)
                .param("id", "1")
                .param("productCategory", "1")
                .param("name", "상품")
                .param("contents", "내용"));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/{id}"))
                .andExpect(redirectedUrl("/product/19?status=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 상품_수정_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/product/1/edit")
                .session(session));

        perform
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("product/product-edit"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 상품_수정() throws Exception {

        String fileName = "portfolio-7";
        String contentType = "jpg";
        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType);

        ResultActions perform = mockMvc.perform(multipart("/product/1/edit")
                .file("thumbnailFile", mockMultipartFile.getBytes())
                .session(session)
                .param("id", "1")
                .param("isDeleteThumbnail", "false")
                .param("productCategory", "1")
                .param("name", "상품 수정")
                .param("contents", "내용 수정"));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/{id}"))
                .andExpect(redirectedUrl("/product/1?status=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 상품_삭제() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/product/1"));

        perform
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(String.valueOf(Paths.get(fileDir, PathConst.PRODUCT, fileName +  "." + contentType))));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}