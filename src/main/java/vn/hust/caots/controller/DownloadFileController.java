package vn.hust.caots.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/*
Content-disposition: browser sẽ mở hộp thoại cho download file.
inline:nội dung sẽ hiển thị tự động.
attachment:Tập tin đính kèm.
form-data:Dữ liệu form.
*/

@RestController
@RequestMapping("/download")
public class DownloadFileController {
    /*
      Client gửi 1 request tới server để tải file về
      Server sẽ kiểm tra xem request đó yêu cầu lấy về file nào để đọc file đó lên (có thể từ database hoặc ổ cứng)
      Server đọc file và ghi file đó ra response trả về cho client
    */


    @Autowired
    ServletContext servletContext;

    public static final String PATH_FILE = "/file/demo.txt";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile(HttpServletRequest request)
            throws IOException {
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            //InputStreamResource : Là dữ liệu của tập tin mà người dùng download về.

            File file = new File(servletContext.getRealPath(PATH_FILE));
            byte[] data = FileUtils.readFileToByteArray(file);

            // Set mimeType response / Trình duyệt download nội dung đó lập tức, không cần hỏi người dùng.
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // THiết lập thông tin trả về Header
            responseHeader.set("Content-disposition", "attachment;filename = " + file.getName());
            responseHeader.setContentLength(data.length);
            //Không khởi tạo đk InputStream mà phải khởi tạo qua các class con của nó
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamSource = new InputStreamResource(inputStream);
            return new ResponseEntity<>(inputStreamSource, responseHeader, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
