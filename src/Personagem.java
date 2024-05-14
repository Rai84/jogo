import java.awt.*;

public class Personagem {
    private int vida;
    private final int chao = 840;
    private final int parede = 0;
    private int x = parede, y = chao;  
    private boolean pulando = false;
    private Teclado teclado;

    private final int alturaPulo = 100; 
    private final int gravidade = 5; 
    private final int largura = 50;  // Largura do personagem
    private final int altura = 50;   // Altura do personagem

    public Personagem(Teclado teclado) {  
        this.vida = 100;
        this.teclado = teclado;  
    }

    public void mover() {
        if (teclado.cima && !pulando) {
            y -= alturaPulo;
            pulando = true; 
        }

        if (pulando) {
            y += gravidade;
            if (y >= chao) {
                y = chao;
                pulando = false;
            }
        }

        if (teclado.esquerda && x > parede) x -= 20;
        if (teclado.direita) x += 20;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
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

    public void Vida(Graphics g, Image imagem) {
        int numImagens = (vida / 25) + (vida % 25 == 0 ? 0 : 1);
        int startX = 20;
        for (int i = 0; i < numImagens; i++) {
            g.drawImage(imagem, startX + i * 40, 20, null);
        }
    }

    public void desenhoPersonagem(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, largura, altura);
    }
}
