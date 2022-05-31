package com.joaosantosdev.feirouu.commons.utils.img;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class ImagemBuilder {
    private BufferedImage image;

    public ImagemBuilder(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    public static ImagemBuilder carregar(MultipartFile file) throws IOException {
        return new ImagemBuilder(ImagemUtil.toBufferedReader(file));
    }

    public ImagemBuilder cortar() {
        this.image = ImagemUtil.cortar(image);
        return this;
    }

    public ImagemBuilder redimensionar(int size) {
        this.image = ImagemUtil.resize(image, size);
        return this;
    }

    public ImagemBuilder pngParaJpg() {
        this.image = ImagemUtil.pngParaJpg(image);
        return this;
    }

    public InputStream toInputStream(String extension) {
        return ImagemUtil.getInputStream(this.image, extension);
    }
}

