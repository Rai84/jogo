import javax.swing.ImageIcon;
import java.awt.*;

public class Imagens {
    public static String Coracao = "src/img/favorito.png";
    public static String Chao = "src/img/chao.png";
    public static String PortaAberta = "src/img/portaAberta.png";
    public static String PortaFechada = "src/img/portaFechada.png";
    public static String Chave = "src/img/chave.png";
    public static String Espinho = "src/img/epinho.png";

    public static ImagemComColisao carregarCoracao(String caminho) {
        return new ImagemComColisao(caminho);
    }

    public static ImagemComColisao carregarChao(String caminho) {
        return new ImagemComColisao(caminho);
    }

    public static ImagemComColisao carregarPortaAberta(String caminho) {
        return new ImagemComColisao(caminho);
    }

    public static ImagemComColisao carregarPortaFechada(String caminho) {
        return new ImagemComColisao(caminho);
    }

    public static ImagemComColisao carregarChave(String caminho) {
        return new ImagemComColisao(caminho);
    }

    public static void Chao(Graphics g, ImagemComColisao imagem) {
        int numImagens = 1920 / 96;
        for (int i = 0; i < numImagens; i++) {
            g.drawImage(imagem.getImage(), i * 100, 890, null);
        }
    }

    public static boolean verificarColisao(ImagemComColisao img1, Rectangle rect) {
        return img1.colideCom(rect);
    }
}
