import java.awt.*;

public class Personagem {
    private int vida;

    public Personagem() {
        this.vida = 150;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void diminuirVida(int quantidade) {
        this.vida -= quantidade;
    }

    public void desenharVida(Graphics g, Image imagem) {
        int numImagens = (vida / 25) + (vida % 25 == 0 ? 0 : 1);
        int startX = 20;
        for (int i = 0; i < numImagens; i++) {
            g.drawImage(imagem, startX + i * 40, 20, null);
        }
    }
}
