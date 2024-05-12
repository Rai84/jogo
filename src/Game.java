import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = 480;

    private int contador = 0;
    private Personagem personagem;
    private Image imagemPersonagem;

    public Game() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.personagem = new Personagem();
        this.imagemPersonagem = Imagens.carregarImagem("src/img/favorito (2).png");
    }

    public void update() {
        // Atualizar lógica do jogo aqui
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        personagem.desenharVida(g, imagemPersonagem);

        if (personagem.getVida() <= 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString("Game Over", 300, 240);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Pontos: " + contador, 550, 35);

        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame jframe = new JFrame("Meu jogo");
        jframe.add(game);
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while (true) {
            update();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
