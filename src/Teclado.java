import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    private boolean[] teclas = new boolean[256];
    public boolean cima, baixo, esquerda, direita, espaco;

    public void update() {
        cima = teclas[KeyEvent.VK_W];
        baixo = teclas[KeyEvent.VK_S];
        esquerda = teclas[KeyEvent.VK_A];
        direita = teclas[KeyEvent.VK_D];
        espaco = teclas[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }
}
