package jogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Imagens {
    public static Image carregarPortaAberta() {
        return carregarImagem("/img/portaAberta.png");
    }

    public static Image carregarPortaFechada() {
        return carregarImagem("/img/portaFechada.png");
    }

    public static Image carregarChao() {
        return carregarImagem("/img/chao.png");
    }

    public static Image carregarCoracao() {
        return carregarImagem("/img/favorito.png");
    }

    public static Image carregarChave() {
        return carregarImagem("/img/chave.png");
    }

    public static Image carregarObs1() {
        return carregarImagem("/img/Espinho.png");
    }

    public static Image carregarObs2() {
        return carregarImagem("/img/Espinho2.png");
    }

    public static Image carregarObs3() {
        return carregarImagem("/img/Vector 2.png");
    }

    public static Image carregarObs4() {
        return carregarImagem("/img/Fogo.png");
    }

    public static Image carregarBTN() {
        return carregarImagem("/img/Polygon 1.png");
    }

    private static Image carregarImagem(String caminho) {
        try {
            URL url = Imagens.class.getResource(caminho);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
