import java.awt.*;
import java.awt.event.KeyListener;


public class Jogo {
    private final int WIDTH, HEIGHT;
    private final int CHAO;
    private final Image imagemCoracao;
    private final Image imagemChao;
    private final Image imagemBTN;
    private final Teclado teclado;
    private final Personagem personagem;
    private final Objeto objeto;

    public Jogo(int largura, int altura) {
        this.WIDTH = largura;
        this.HEIGHT = altura;
        this.CHAO = HEIGHT - 50;
        this.teclado = new Teclado();
        this.personagem = new Personagem(CHAO, teclado);
        this.imagemCoracao = Imagens.carregarCoracao();
        this.imagemChao = Imagens.carregarChao();
        this.imagemBTN = Imagens.carregarBTN();

        this.objeto = new Objeto(CHAO, WIDTH, personagem);
    }
    

    public void update() {
        teclado.update();
        personagem.mover();
        objeto.verificarColisaoChave();
        objeto.verificarColisaoObstaculos();
        objeto.verificarColisaoPorta();
        
        // if (personagem.getVida() <= 0) {
        //     recomecar();
        // }
    }

    public void render(Graphics g) {
        Fundo(g);
        Chao(g);
        if (personagem.getVida() > 0) {
            nivel(g);
        } else {
            GameOver(g); // Renderiza a tela de Game Over apenas se o jogo terminou
        }
    }

    private void Fundo(Graphics g) {
        g.setColor(new Color(255, 165, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    private void Chao(Graphics g) {
        int numImagens = WIDTH / 100;
        for (int i = 0; i < numImagens; i++) {
            if (imagemChao != null) {
                g.drawImage(imagemChao, i * 100, HEIGHT - 50, null);
            }
        }
    }

    private void GameOver(Graphics g) {
        if (personagem.getVida() <= 0) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Game Over", WIDTH / 2 - 80, HEIGHT / 2);
            g.drawImage(imagemBTN, WIDTH / 2 + 100, HEIGHT / 2 - 18, null);
            personagem.setVida(100);
            personagem.PersonagemStart();
            objeto.setNivel(1);
        }
    }

    public void recomecar() {
        personagem.setVida(100);
        objeto.setNivel(1);
        objeto.generateRandomNumbers();
        personagem.PersonagemStart();
    }

    public void nivel(Graphics g) {
        objeto.nivel(g);  
        objeto.ChaveEPorta(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.drawString("Nível: " + objeto.getNivel(), WIDTH - 150, 70);
        objeto.nivel(g); 
        personagem.desenhoPersonagem(g);
        personagem.Vida(g, imagemCoracao);
    }

    public KeyListener getTeclado() {
        return teclado;
    }
}
