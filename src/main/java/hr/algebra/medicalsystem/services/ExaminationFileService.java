package hr.algebra.medicalsystem.services;

import hr.algebra.medicalsystem.entities.Examination;
import hr.algebra.medicalsystem.entities.ExaminationFile;
import hr.algebra.medicalsystem.repositories.ExaminationFileRepository;
import hr.algebra.medicalsystem.repositories.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ExaminationFileService {

    private final ExaminationFileRepository fileRepository;
    private final FileStorageService fileStorageService;
    private final ExaminationRepository examinationRepository;

    @Autowired
    public ExaminationFileService(ExaminationFileRepository fileRepository,
                                  FileStorageService fileStorageService,
                                  ExaminationRepository examinationRepository) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
        this.examinationRepository = examinationRepository;
    }


    public Optional<ExaminationFile> storeFileForExamination(Long examinationId, MultipartFile file) {
        return examinationRepository.findById(examinationId).map(exam -> {

            String filePath = fileStorageService.storeFile(file);


            ExaminationFile examFile = new ExaminationFile();
            examFile.setExamination(exam);
            examFile.setFilePath(filePath);


            return fileRepository.save(examFile);
        });
    }


    public boolean storeMultipleFilesForExamination(Long examinationId, MultipartFile[] files) {
        Optional<Examination> maybeExam = examinationRepository.findById(examinationId);
        if (maybeExam.isEmpty()) {
            return false;
        }
        Examination exam = maybeExam.get();

        for (MultipartFile mf : files) {
            String filePath = fileStorageService.storeFile(mf);
            ExaminationFile examFile = new ExaminationFile();
            examFile.setExamination(exam);
            examFile.setFilePath(filePath);
            fileRepository.save(examFile);
        }
        return true;
    }


}
