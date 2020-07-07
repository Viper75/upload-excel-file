package org.viper75.uploadexcelfile.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.viper75.uploadexcelfile.helpers.ExcelHelper;
import org.viper75.uploadexcelfile.model.Contact;
import org.viper75.uploadexcelfile.repository.ContactRepository;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelFileService {
    private final ContactRepository contactRepository;

    public void upload(MultipartFile file) {
        if (!ExcelHelper.hasExcelFormat(file))
            throw new RuntimeException("Invalid file type.");

        try {
            List<Contact> contacts = ExcelHelper.excelToContacts(file.getInputStream());
            contactRepository.saveAll(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }
}
