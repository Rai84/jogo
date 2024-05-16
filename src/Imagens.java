import javax.swing.ImageIcon;
import java.awt.*;

public class Imagens {
    public static String Coracao = "src/img/favorito.png";
    public static String Chao = "src/img/chao.png";
    public static String PortaAberta = "src/img/portaAberta.png";
    public static String PortaFechada = "src/img/portaFechada.png";
    public static String Chave = "src/img/chave.png";
    public static String Espinho = "src/img/epinho.png";

    public static Colisao carregarCoracao(String caminho) {
        return new Colisao(caminho);
    }

    public static Colisao carregarChao(String caminho) {
        return new Colisao(caminho);
    }

    public static Colisao carregarPortaAberta(String caminho) {
        return new Colisao(caminho);
    }

    public static Colisao carregarPortaFechada(String caminho) {
        return new Colisao(caminho);
    }

    public static Colisao carregarChave(String caminho) {
        return new Colisao(caminho);
    }

    public static void Chao(Graphics g, Colisao imagem) {
        int numImagens = 1920 / 96;
        for (int i = 0; i < numImagens; i++) {
            g.drawImage(imagem.getImage(), i * 100, 890, null);
        }
    }

    public static boolean verificarColisao(Colisao img1, Rectangle rect) {
        return img1.colideCom(rect);
    }
}
