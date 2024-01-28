package ru.hogwarts.hogwartsdatastreams.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.hogwartsdatastreams.model.Avatar;
import ru.hogwarts.hogwartsdatastreams.model.Student;
import ru.hogwarts.hogwartsdatastreams.repository.AvatarRepository;

import javax.imageio.ImageIO;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
@Transactional
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }


    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.findStudent(studentId);

        Path filePath = Path.of(avatarDir, studentId + "." + getExtensions(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatarByStudentId(studentId);

        if (avatar == null) {
            avatar = new Avatar();
        }
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }


    private String getExtensions(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }


    private Avatar findAvatarByStudentId(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    public Avatar findAvatar(Long avatarId) {
        return avatarRepository.findByStudentId(avatarId).get();
    }


    public void delete(Long id) {
        avatarRepository.deleteById(id);
    }


//    private Avatar findAvatarByStudentId(Long studentId) {
//        return avatarRepository.findByAvatarId(studentId); //.orElse(new Avatar());
//    }
//
//    public Avatar findAvatar(Long id) {
//        return avatarRepository.findByAvatarId(id);//.get();
//    }


    private byte[] generateDataForDB(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();
            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), bos);
            return bos.toByteArray();

        }

    }
}