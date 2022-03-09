package com.leather.workshop.global.common.util.file;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.InputStream;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/file")
public class FileUtilController {

    private final FileUtils fileUtils;

    @GetMapping(value = "/img/{part}/{fileName}")
    public ResponseEntity<byte[]> displayImgFile(@PathVariable String part,
                                                 @PathVariable String fileName) throws Exception{

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(fileUtils.getFullPath(part, fileName));
            headers.setContentType(FileUtils.getMediaType(fileName));
            headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
            entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }
}
