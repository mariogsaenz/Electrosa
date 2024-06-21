package es.unirioja.paw.service;

import es.unirioja.paw.exception.StorageException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements FileStorageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //
    // NOTA: esta clase implementa la interfaz FileStorageService. 
    // Piensa en cómo se acopla a un tipo de almacenamiento concreto
    // y a entidades del framework Spring
    //
    /**
     * @param file El fichero subido
     * @param path Ruta destino
     */
    @Override
    public void store(MultipartFile file, String path) {
        final Path targetPath = Paths.get(path);
        try {
            if (file.isEmpty()) {
                throw new StorageException("No se puede guardar un fichero vacío");
            }
            Path destinationFile = targetPath.resolve(
                    Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(targetPath.toAbsolutePath())) {
                // Peligro de ataque Graph Traversal 
                throw new StorageException(
                        "No se puede guardar un fichero fuera del directorio indicado");
            }
            logger.info("Destination file: {}", destinationFile);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new StorageException("Error al guardar", ex);
        }
        logger.info("Fichero guardado: {} ({})", file.getOriginalFilename(), path);
    }

}
