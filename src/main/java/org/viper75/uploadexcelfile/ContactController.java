package org.viper75.uploadexcelfile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.viper75.uploadexcelfile.model.Contact;
import org.viper75.uploadexcelfile.service.ExcelFileService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    private final ExcelFileService fileService;

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file) {
        fileService.upload(file);
    }

    @GetMapping
    public List<Contact> getAll() {
        return fileService.getAll();
    }
}
