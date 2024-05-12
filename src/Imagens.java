import javax.swing.ImageIcon;
import java.awt.Image;

public class Imagens {
    public static Image carregarImagem(String caminho) {
        ImageIcon icon = new ImageIcon(caminho);
        return icon.getImage();
    }
}
