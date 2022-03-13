package com.leather.workshop.global.common.util.file;

import com.leather.workshop.global.common.util.web.dto.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/file")
public class FileUtilController {

    private final FileUtilities fileUtilities;

    @GetMapping(value = "/img/{part}/{fileName}")
    public ResponseEntity<byte[]> displayImgFile(@PathVariable String part,
                                                 @PathVariable String fileName) throws Exception{

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(fileUtilities.getFullPath(part, fileName));
            headers.setContentType(FileUtilities.getMediaType(fileName));
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

    @PostMapping("/ckeditor/fileUpload")
    public String fileUploadFromCKEditor(HttpServletResponse response,
                                         MultipartHttpServletRequest multipartRequest) throws Exception {
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            UploadFile uploadFile = fileUtilities.storeFile(multipartRequest.getFile("upload"));
            String fileUrl = "/file/ckeditor/fileDownload?fileName=" + uploadFile.getStoreFileName();
            printWriter = response.getWriter();
            printWriter.println("{\"filename\" : \"" + uploadFile.getStoreFileName() + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
            printWriter.flush();
        } catch (IOException e) {
            log.info(e.toString());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        } return null;
    }

    @RequestMapping("/ckeditor/fileDownload")
    public void filePrintFromCKEditor(@RequestParam(value = "fileName") String fileName,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        File file = fileUtilities.downloadFile(fileName);
        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType(FileUtilities.getMediaType(fileName).toString());
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드에 실패하였습니다.");
        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }
    }
}
