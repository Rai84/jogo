import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    public static int WIDTH = 1500, HEIGHT = 700;

    private Jogo jogo;
    private Thread thread;
    private JFrame frame;

    public Game() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.jogo = new Jogo(WIDTH, HEIGHT);
        this.addKeyListener(jogo.getTeclado());
        this.setFocusable(true);
    }

    public void update() {
        jogo.update();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        jogo.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame jframe = new JFrame("Meu jogo");
        jframe.add(game);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        game.setFrame(jframe); // Defina a referência do JFrame
        game.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }
}
