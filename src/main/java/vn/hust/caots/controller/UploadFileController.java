package vn.hust.caots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.*;


@Controller
@RequestMapping("/")
public class UploadFileController {
    @Autowired
    ServletContext servletContext;

    // lấy tập tin gốc
    //xây dựng đường dẫn file
    //Ghi file
    private static final String UPLOAD_DICERTORY = "/file";

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }


    //Mọi thông tin của file sẽ được lưu trữ trong lớp CommonsMultipartFile
    @ResponseBody
    @PostMapping(value = "upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("multipartFile") CommonsMultipartFile file, ModelMap mm) {
        String path = servletContext.getRealPath(UPLOAD_DICERTORY);
        String fileName = file.getOriginalFilename();// lấy tên tập tin gốc
        byte[] data = file.getBytes();
        //Xây dựng đường dẫn file trong Java
        //File fileDown = new File(path + File.separator + fileName); HOẶC ->
        File fileDown = new File(path, fileName);
        OutputStream stream;
        try {
            //Ghi file
            stream = new BufferedOutputStream(new FileOutputStream(fileDown));
            stream.write(data);
            stream.flush();
            stream.close();
            // Hoặc có thể sử dụng:
            //file.transferTo(fileDown);
            return new ResponseEntity<>("upload Success ! <br>File : " + fileDown + "<br> Data : " + data, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
