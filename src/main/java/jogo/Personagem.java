package jogo;

import java.awt.*;

public class Personagem {
    private int vida;
    private final int CHAO;
    private Teclado teclado;
    private int x;  // Posição x do personagem
    private int y;  // Posição y do personagem
    private final int largura = 50;  // Largura do personagem
    private final int altura = 50;   // Altura do personagem
    private boolean pulando = false;
    private final int alturaPulo = 100; 
    private final int gravidade = 5;
    


    public Personagem(int CHAO, Teclado teclado) {
        this.y = CHAO - 50;
        this.CHAO = CHAO;
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
            if (y >= CHAO - altura) {
                y = CHAO - altura;
                pulando = false;
            }
        }

        if (teclado.esquerda) x -= 15;
        if (teclado.direita) x += 15;

        // Ensure the character stays within the bounds of the game window
        if (x < 0) x = 0;
        if (x > Game.WIDTH - largura) x = Game.WIDTH - largura;
        if (y < 0) y = 0;
        if (y > CHAO - altura) y = CHAO - altura;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void PersonagemStart() {
        this.x = 50; // Posição inicial segura para evitar colisão com a porta
        this.y = CHAO - altura; // Ajuste conforme necessário
    }
    

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
