package org.example.dndmapdisplayerbackend.adapters.in.rest.file;

import org.example.dndmapdisplayerbackend.domain.port.in.file.DeleteFileUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.file.GetFileUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.file.UploadFileUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("api/users/{userId}/files")
public class FileController {

    private final UploadFileUseCase uploadFileUseCase;
    private final GetFileUseCase getFileUseCase;
    private final DeleteFileUseCase deleteFileUseCase;

    public FileController(UploadFileUseCase uploadFileUseCase, GetFileUseCase getFileUseCase, DeleteFileUseCase deleteFileUseCase) {
        this.uploadFileUseCase = uploadFileUseCase;
        this.getFileUseCase = getFileUseCase;
        this.deleteFileUseCase = deleteFileUseCase;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@PathVariable Long userId,
                                             @RequestParam("file") MultipartFile file,
                                             @AuthenticationPrincipal String email) throws IOException {
        String path = uploadFileUseCase.uploadFile(
                userId,
                email,
                file.getBytes(),
                file.getOriginalFilename(),
                file.getContentType()
        );

        return ResponseEntity.ok("File uploaded at: " + path);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> get(@PathVariable Long userId,
                                      @PathVariable String filename,
                                      @AuthenticationPrincipal String email) {
        byte[] data = getFileUseCase.getFile(userId, filename, email);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(data);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<Void> delete(@PathVariable Long userId,
                                       @PathVariable String filename,
                                       @AuthenticationPrincipal String email) {
        deleteFileUseCase.deleteFile(userId, filename, email);
        return ResponseEntity.noContent().build();
    }
}
