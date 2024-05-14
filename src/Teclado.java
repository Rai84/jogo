import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{

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
    public void keyTyped(KeyEvent e) { // Tecla pressionada
        System.out.println("Tecla digitada: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) { // Tecla pressionada
        teclas[e.getKeyCode()] = true;
        System.out.println("Tecla pressionada: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) { // Tecla liberada
        teclas[e.getKeyCode()] = false;
        System.out.println("Tecla liberada: " + e.getKeyChar());
    }
    
}
