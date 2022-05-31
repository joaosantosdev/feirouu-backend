package com.joaosantosdev.feirouu.adapters.in.upload;

import com.joaosantosdev.feirouu.adapters.out.persistence.services.LojaPersistence;
import com.joaosantosdev.feirouu.adapters.out.persistence.services.ProdutoPersistence;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import com.joaosantosdev.feirouu.commons.utils.img.Diretorio;
import com.joaosantosdev.feirouu.commons.utils.img.ImagemBuilder;
import com.joaosantosdev.feirouu.handlers.ImagemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImagemService {

    private final Path root = Paths.get("uploads");


    private UsuarioUtil usuarioUtil;
    private LojaPersistence lojaPersistence;
    private ProdutoPersistence produtoPersistence;

    @Value("${img.tamanho}")
    private int tamanho;

    public ImagemService(UsuarioUtil usuarioUtil,
                         LojaPersistence lojaPersistence,
                         ProdutoPersistence produtoPersistence) {
        this.usuarioUtil = usuarioUtil;
        this.lojaPersistence = lojaPersistence;
        this.produtoPersistence = produtoPersistence;

        this.criarDiretorio(this.root);
    }


    private String salvarImagem(MultipartFile file, Integer tipo, Long id) throws IOException {

        InputStream is = ImagemBuilder
                .carregar(file)
                .pngParaJpg()
                .cortar()
                .redimensionar(this.tamanho)
                .toInputStream("png");

        Diretorio diretorio = Diretorio.get(tipo);
        Path path = this.root.resolve(diretorio.pasta());
        this.criarDiretorio(path);

        String ext = "jpg";
        String fileName = id + "." + ext;


        Path fileDir = path.resolve(fileName);

        if (fileDir.toFile().exists()) {
            Files.delete(fileDir);
        }

        Files.copy(is, fileDir);
        return fileName;
    }


    public void excluir(Long id, Integer tipo) throws IOException {
        try {
            Diretorio diretorio = Diretorio.get(tipo);
            this.validar(id, tipo);
            Path path = Paths.get("uploads").resolve(diretorio.pasta()).resolve(id + ".jpg");
            Files.delete(path);
        }catch (Exception e){
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "Não foi possível excluir a imagem.");
        }
    }

    private void criarDiretorio(Path path) {
        try {
            Files.createDirectory(path);
        } catch (Exception e) {

        }
    }

    public void salvar(MultipartFile imagem, Long id, Integer tipo) throws IOException {
        this.validar(id, tipo);
        this.salvarImagem(imagem, tipo, id);
    }


    public InputStream obterImagem(Long id, Integer tipo) {
        try {
            Diretorio diretorio = Diretorio.get(tipo);
            Path path = Paths.get("uploads").resolve(diretorio.pasta()).resolve(id + ".jpg");
            return new FileInputStream(path.toFile());
        } catch (FileNotFoundException e) {
            return null;
        }
    }


    private void validarProduto(Long id) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Loja loja = this.lojaPersistence.buscarLojaPorUsuarioId(usuario.getId());

        if (loja == null) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "Loja não encontrada, não é possível salvar imagem.");
        }

        Optional<Produto> produto = produtoPersistence.buscar(id, loja.getId());

        if (produto.isEmpty()) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "Produto não encontrado, não é possível salvar imagem.");
        }

        if (!produto.get().getId().equals(id)) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "ID incorreto");
        }
    }

    public void validarLoja(Long id) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Loja loja = this.lojaPersistence.buscarLojaPorUsuarioId(usuario.getId());

        if (loja == null) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "Loja não encontrada, não é possível salvar imagem.");
        }

        if (!loja.getId().equals(id)) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "ID incorreto");
        }
    }

    private void validarUsuario(Long id) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();

        if (!usuario.getId().equals(id)) {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "ID incorreto");
        }
    }

    private void validar(Long id, Integer tipo){
        if (Diretorio.USUARIOS.tipo().equals(tipo)) {
            this.validarUsuario(id);
        } else if (Diretorio.LOJAS.tipo().equals(tipo)) {
            this.validarLoja(id);
        } else if (Diretorio.PRODUTOS.tipo().equals(tipo)) {
            this.validarProduto(id);
        } else {
            throw new ImagemException(HttpStatus.BAD_REQUEST.value(), "Tipo invalido");
        }
    }
}