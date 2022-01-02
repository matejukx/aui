package pl.matejuk.clients.app.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.matejuk.clients.app.entity.Client;
import pl.matejuk.clients.app.entity.ClientFile;
import pl.matejuk.clients.app.repository.IClientFileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final IClientFileRepository repository;
    private final Path storageLocation;

    @Autowired
    public FileService(IClientFileRepository repository, @Value("${storage.path}") String storagePath){

        this.repository = repository;
        this.storageLocation = Paths.get(storagePath).toAbsolutePath().normalize();
    }

    public Optional<ClientFile> find(Long id) {
        return repository.findById(id);
    }

    public List<ClientFile> findAll() {
        return repository.findAll();
    }

    public ClientFile SaveFile(MultipartFile file, String author, String description) throws IOException {

        var clientFile = ClientFile.builder().author(author).description(description).build();
        var clientSaved = this.repository.save(clientFile);

        var fileName = file.getOriginalFilename();
        var ext = fileName.split("\\.")[1];
        var newFileName = clientSaved.getId().toString() + '.' + ext;
        Path targetLocation = this.storageLocation.resolve(newFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        clientSaved.setFilename(newFileName);
        return this.repository.save(clientSaved);
    }

    public Resource GetFile(Long id) throws MalformedURLException {
        var file = this.repository.findById(id).orElse(null);

        if (file == null) return null;

        Path filePath = this.storageLocation.resolve(file.getFilename()).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            return resource;
        }
        else throw new IllegalArgumentException("File does not exist");
    }
}
