package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.upload.ImagemService;
import com.joaosantosdev.feirouu.commons.utils.img.Diretorio;
import com.joaosantosdev.feirouu.handlers.ImagemException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private ImagemService imagemService;


    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> deleteById(@RequestParam(name = "imagem") MultipartFile imagem,
                                           @RequestParam(name = "tipo") Integer tipo,
                                           @RequestParam(name = "id") Long id) throws IOException {


        this.imagemService.salvar(imagem, id, tipo);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> obterImagem(@RequestParam(name = "id") Long id,
                                                           @RequestParam(name = "tipo") Integer tipo) throws IOException {

        InputStream inputStream = this.imagemService.obterImagem(id, tipo);

        if(inputStream == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(inputStream));
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id,
                                             @RequestParam(name = "tipo") Integer tipo) throws IOException {
        this.imagemService.excluir(id, tipo);

        return ResponseEntity.noContent().build();
    }
}
