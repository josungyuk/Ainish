package ainish.post.controller;

import ainish.S3.S3Uploader;
import ainish.post.dto.createDto;
import ainish.post.dto.deleteDto;
import ainish.post.service.PostService;
import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final S3Uploader s3Uploader;
    @PostMapping()
    ResponseEntity create(@RequestBody createDto dto, @RequestPart MultipartFile multipartFile){
        String url = "";
        if(multipartFile != null){ // 파일 업로드한 경우에만

            try{// 파일 업로드
                url = s3Uploader.upload(multipartFile, "images"); // S3 버킷의 images 디렉토리 안에 저장됨
                System.out.println("fileName = " + url);
            }catch (IOException e){}
        }

        long response = postService.create(dto, url);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity delete(@RequestBody deleteDto dto){
        postService.delete(dto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
