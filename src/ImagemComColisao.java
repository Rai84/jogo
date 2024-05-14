import javax.swing.ImageIcon;
import java.awt.*;

public class ImagemComColisao {
    private Image image;
    private Rectangle bounds;
    private boolean visible;

    public ImagemComColisao(String caminho) {
        ImageIcon icon = new ImageIcon(caminho);
        this.image = icon.getImage();
        this.bounds = new Rectangle(0, 0, image.getWidth(null), image.getHeight(null));
        this.visible = true;  // Inicialmente, a imagem é visível
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(int x, int y) {
        bounds.setLocation(x, y);
    }

    public boolean colideCom(Rectangle outro) {
        return bounds.intersects(outro);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
