package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar
            (@PathVariable long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/avatar_db/{studentId}")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long studentId) {
        Avatar avatar = avatarService.findAvatar(studentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("avatar_file/{studentId}")
    public void downloadAvatar(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(studentId);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(avatar.getData().length);
            is.transferTo(os);
        }
    }

    @GetMapping("/paging")
    public ResponseEntity<List<Avatar>> getAllPaging(@RequestParam Integer pageNumber, @RequestParam Integer size) {
        if (pageNumber < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Avatar> avatars = avatarService.findAll(pageNumber, size);
        if (avatars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avatars);
    }

}