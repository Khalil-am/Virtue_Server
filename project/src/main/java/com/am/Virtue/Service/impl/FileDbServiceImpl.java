package com.am.Virtue.Service.impl;

import com.am.Virtue.entities.FileDb;
import com.am.Virtue.repository.FileDbRepo;
import com.am.Virtue.resources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class FileDbServiceImpl {


        @Autowired
        private FileDbRepo fileDbRepository;


        public FileResource store(MultipartFile file) throws IOException {
            String fileName = file.getOriginalFilename();
            FileDb fileDb = new FileDb(Long.parseLong(String.valueOf(UUID.randomUUID())), fileName, file.getContentType(), file.getBytes());
            fileDbRepository.save(fileDb);
            return mapToFileResponse(fileDb);
        }

        public FileDb getFileById(String id) {

            Optional<FileDb> fileOptional = fileDbRepository.findById(id);

            if (fileOptional.isPresent()) {
                return fileOptional.get();
            }
            return null;
        }

        public List<FileResource> getFileList() {
            return fileDbRepository.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
        }

        private FileResource mapToFileResponse(FileDb fileDb) {
            return new FileResource(fileDb.getId(), fileDb.getType(), fileDb.getName());
        }


    }
