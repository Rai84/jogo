import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    public static int WIDTH = 1920, HEIGHT = 1200;

    private int contador = 1000;
    private boolean chave = false;
    private Personagem personagem;
    private Colisao imagemCoracao;
    private Colisao imagemChao;
    private Colisao imagemPortaAberta;
    private Colisao imagemPortaFechada;
    private Colisao imagemChave;
    private Teclado teclado;

    public Game() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.teclado = new Teclado();
        this.personagem = new Personagem(teclado);
        this.imagemCoracao = Imagens.carregarCoracao(Imagens.Coracao);
        this.imagemChao = Imagens.carregarChao(Imagens.Chao);
        this.imagemPortaAberta = Imagens.carregarPortaAberta(Imagens.PortaAberta);
        this.imagemPortaFechada = Imagens.carregarPortaFechada(Imagens.PortaFechada);
        this.imagemChave = Imagens.carregarChave(Imagens.Chave);
        this.addKeyListener(teclado);
        this.setFocusable(true);
    }

    public void update() {
        teclado.update();
        personagem.mover();                                                                        // MOVER O PERSONAGEM
        personagem.diminuirVida(0);                                            // DIMINUIR VIDA DO PERSONAGEM

        
        imagemChave.setPosition(840, 850);                                                       // POSIÇÃO DA CHAVE
        imagemPortaFechada.setPosition(1740, 657);                                                  // POSIÇÃO PORTA
        imagemPortaAberta.setPosition(1740, 657);                                                   // POSIÇÃO PORTA

        // Verificar se houve colisão entre a chave e o personagem
        if (imagemChave.isVisible() && Imagens.verificarColisao(imagemChave, personagem.getBounds())) { 
            chave = true;  
            imagemChave.setVisible(false);  
        }

        
    }

    public void render() {                                               // Alterar o método render para desenhar o jogo
        BufferStrategy bs = this.getBufferStrategy();   
        if (bs == null) {                                                               
            this.createBufferStrategy(3);                                                       
            return;                                                                                          
        }

        Graphics g = bs.getDrawGraphics();               
        g.setColor(new Color(185, 185, 185));                                                  
        g.fillRect(0, 0, WIDTH, HEIGHT);                         

        personagem.desenhoPersonagem(g);                                  // Alterar o método para desenhar o Personagem
        personagem.Vida(g, imagemCoracao.getImage());             // Alterar o método para desenhar a Vida do Personagem
        Imagens.Chao(g, imagemChao);                                            // Alterar o método para desenhar o Chão

        Obstaculo.nivel(g);                                                

        if (imagemChave.isVisible()) {                                       // Verificar se a imagem da chave é visível
            g.drawImage(imagemChave.getImage(), 840, 850, null);                 
        }

        if (!chave) {                                                               // Verificar se a chave foi coletada
            g.drawImage(imagemPortaFechada.getImage(), 1740, 657, null); 
        } else {
            g.drawImage(imagemPortaAberta.getImage(), 1740, 657, null);   
        }

        if (personagem.getVida() <= 0) {                                                          // FUNÇÃO DE GAME OVER
            g.setColor(Color.WHITE);                                                           
            g.setFont(new Font("Arial", Font.PLAIN, 12));                                    
            g.drawString("Game Over", 300, 240);                                           
        }

        g.setColor(Color.BLACK);                                                                   // CONTADOR DE PONTOS
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.drawString("Pontos: " + contador, 1700, 50);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {                                // Alterar o método main para iniciar o jogo
        Game game = new Game();                                                         
        JFrame jframe = new JFrame("Meu jogo");                                              
        jframe.add(game);                                                               
        jframe.setLocationRelativeTo(null);                                                      
        jframe.pack();                                                                    
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                        
        jframe.setVisible(true);                                                             

        game.requestFocus();

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
