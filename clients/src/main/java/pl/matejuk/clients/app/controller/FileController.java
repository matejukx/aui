package pl.matejuk.clients.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;
import pl.matejuk.clients.app.dto.GetClientsResponse;
import pl.matejuk.clients.app.dto.GetFilesResponse;
import pl.matejuk.clients.app.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("api/files")
public class FileController {


    private final FileService service;

    @Autowired
    public FileController(FileService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GetFilesResponse> getFiles(){
        return ResponseEntity.ok(GetFilesResponse.entityToDtoMapper().apply(this.service.findAll()));
    }

    @GetMapping ("{id}")
    public ResponseEntity<Resource> DownloadFile(@PathVariable("id") Long id, HttpServletRequest request) throws MalformedURLException {
        var resource = this.service.GetFile(id);
        if (resource == null) return ResponseEntity.notFound().build();

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
           System.out.println("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping
    public ResponseEntity<Void> UploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            UriComponentsBuilder builder) throws IOException {
        var clientFile = this.service.SaveFile(file, author, description);
        return ResponseEntity.created(builder.pathSegment("api", "files", "{id}").buildAndExpand(clientFile.getId()).toUri()).build();
    }
}
