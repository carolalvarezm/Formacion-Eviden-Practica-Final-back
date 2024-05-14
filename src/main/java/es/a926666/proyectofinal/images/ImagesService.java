package es.a926666.proyectofinal.images;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagesService {
    public void saveImage(MultipartFile fichero, String path) throws IOException {
        String fileName =fichero.getOriginalFilename();
        String uploadDir=path;
        Path uploadPath = Paths.get(uploadDir);
        System.out.println(uploadPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
          
        try (InputStream inputStream = fichero.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
    public byte[]  getImage(String imageName,String path) {
        try{
            String uploadDir=path;
            Path uploadPath = Paths.get(uploadDir);
            Path imagePath = uploadPath.resolve(imageName);
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return imageBytes; 
            } else {
                return null; 
            }
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
}
