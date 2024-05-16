import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Representa uma imagem com capacidades de colisão.
 */
public class Colisao {
    private static final int IMAGE_NOT_LOADED_WIDTH = -1;
    private static final int IMAGE_NOT_LOADED_HEIGHT = -1;

    private Image image;
    private Rectangle bounds;
    private boolean visible;

    /**
     * Construtor que carrega a imagem do caminho especificado.
     * 
     * @param caminho o caminho para o arquivo de imagem
     */
    public Colisao(String caminho) {
        try {
            ImageIcon icon = new ImageIcon(caminho);
            this.image = icon.getImage();
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            
            if (imageWidth == IMAGE_NOT_LOADED_WIDTH || imageHeight == IMAGE_NOT_LOADED_HEIGHT) {
                throw new IllegalArgumentException("Imagem não pôde ser carregada do caminho: " + caminho);
            }

            this.bounds = new Rectangle(0, 0, imageWidth, imageHeight);
            this.visible = true;  // Inicialmente, a imagem é visível
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + e.getMessage());
            this.visible = false;
        }
    }

    /**
     * Retorna a imagem carregada.
     * 
     * @return a imagem
     */
    public Image getImage() {
        return image;
    }

    /**
     * Retorna os limites da imagem como um retângulo.
     * 
     * @return os limites da imagem
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Define a posição da imagem.
     * 
     * @param x a coordenada x
     * @param y a coordenada y
     */
    public void setPosition(int x, int y) {
        bounds.setLocation(x, y);
    }

    /**
     * Verifica se a imagem colide com outro retângulo.
     * 
     * @param outro o outro retângulo
     * @return true se houver colisão, caso contrário false
     */
    public boolean colideCom(Rectangle outro) {
        return bounds.intersects(outro);
    }

    /**
     * Retorna se a imagem é visível.
     * 
     * @return true se a imagem é visível, caso contrário false
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Define a visibilidade da imagem.
     * 
     * @param visible true para tornar a imagem visível, false para invisível
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
