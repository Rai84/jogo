package jogo;

import java.awt.*;

public class Colisao {
    public static boolean verificarColisao(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }
}
