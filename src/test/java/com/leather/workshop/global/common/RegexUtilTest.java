package com.leather.workshop.global.common;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegexUtilTest {

    @Test
    public void findAllRegEx(){
        String contents = "<p>ㅇㅇㅇ</p>\n" +
                "\n" +
                "<p>ㅇㅇ</p>\n" +
                "\n" +
                "<p><img alt=\"\" src=\"/file/ckeditor/fileDownload/product?fileName=15573399-24c1-4faa-98d8-8d83b73f7042.png\" style=\"height:128px; width:438px\" /></p>\n" +
                "\n" +
                "<p><img alt=\"\" src=\"/file/ckeditor/fileDownload/product?fileName=e0f460f6-d20f-4678-8e8a-f99155d861f5.png\" style=\"height:128px; width:438px\" /></p>\n" +
                "\n" +
                "<p><img alt=\"\" src=\"/file/ckeditor/fileDownload/product?fileName=eacfe317-6958-457c-b4b9-76f3ab9f8f0c.png\" style=\"height:128px; width:438px\" /></p>\n" +
                "\n" +
                "<p><img alt=\"\" src=\"/file/ckeditor/fileDownload/product?fileName=15573399-24c1-4faa-98d8-8d83b73f7042.png\" style=\"height:128px; width:438px\" /></p>";

        List<String> result = RegexUtil.findAllRegEx(contents, RegexUtil.IMG_SRC_EXPRESSION);
        assertThat(result.size()).isEqualTo(4);
    }
}