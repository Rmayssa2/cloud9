package com.pi.tobeeb.Controllers;

import com.pi.tobeeb.Entities.ConsultationFile;
import com.pi.tobeeb.Entities.Test;
import com.pi.tobeeb.Services.ConsultationFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/consultation-files")
public class ConsultationFileController {

    @Autowired
    private ConsultationFileService consultationFileService;

    @GetMapping("/test/{testId}")
    public ResponseEntity<byte[]> getTestImage(@PathVariable Long testId) throws IOException {
        byte[] imageBytes = consultationFileService.getTestImage(testId);
        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable(value = "id") Long testId) {
        Test test = consultationFileService.getTestById(testId);
        if (test == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(test);
    }

    @PostMapping("/add-test")
    public ResponseEntity<Test> addNewTestToConsultationFile(@RequestBody Long id, @RequestBody String testName) {
        Test test = consultationFileService.addNewTestToConsultationFile(testName, id);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<?> deleteTestById(@PathVariable Long id) {
        consultationFileService.deleteTestById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tests")
    public ResponseEntity<?> deleteAllTests() {
        consultationFileService.deleteAllTests();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/test/{id}/image")
    public ResponseEntity<?> addImageToTest(@PathVariable Long id, @RequestParam("file") MultipartFile image) throws IOException {
        consultationFileService.addImageToTest(id, image);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/appointment/{id}")
    public ConsultationFile getConsultationFileByAppointment(@PathVariable Long id) {
        return consultationFileService.getConsultationFileByAppointment(id);
    }

    @GetMapping("/all")
    public List<ConsultationFile> getAllConsultationFiles() {
        return consultationFileService.getAllConsultationFiles();
    }

}