import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Objeto {
    private int nivel = 1;
    private final int CHAO, WIDTH;
    private final Image imagemPortaAberta, imagemPortaFechada, imagemChave, imagemobs1, imagemobs2;
    private boolean colidiuObs = false;
    private boolean chave = false;
    private static final int LARGURA_OBSTACULO = 50;
    private static final int ALTURA_OBSTACULO = 50;
    private Rectangle boundsChave;
    private static final int ESPACO_ENTRE_OBSTACULOS = 200; // Espaço entre os obstáculos
    private final Personagem personagem;
    private final Timer timer = new Timer();
    private final Random random = new Random();

    private int num1, num2, num3, num4, num5;

    public Objeto(int CHAO, int WIDTH, Personagem personagem) {
        this.CHAO = CHAO;
        this.WIDTH = WIDTH;
        this.personagem = personagem;
        this.imagemPortaAberta = Imagens.carregarPortaAberta();
        this.imagemPortaFechada = Imagens.carregarPortaFechada();
        this.imagemChave = Imagens.carregarChave();
        this.imagemobs1 = Imagens.carregarObs1();
        this.imagemobs2 = Imagens.carregarObs2();
        generateRandomNumbers();
        if (imagemChave != null) {
            this.boundsChave = new Rectangle(num5, CHAO - 50, imagemChave.getWidth(null), imagemChave.getHeight(null));
        } else {
            this.boundsChave = new Rectangle(num5, CHAO - 50, 50, 50); // Tamanho padrão se falhar o carregamento da imagem
        }
        
    }

    private void generateRandomNumbers() {
        int min = 200;
        int max = 1300;

        List<Integer> positions = new ArrayList<>();

        while (positions.size() < 5) {
            int candidate = random.nextInt(max - min + 1) + min;
            boolean isValid = true;

            for (int pos : positions) {
                if (Math.abs(candidate - pos) < ESPACO_ENTRE_OBSTACULOS) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                positions.add(candidate);
            }
        }

        num1 = positions.get(0);
        num2 = positions.get(1);
        num3 = positions.get(2);
        num4 = positions.get(3);
        num5 = positions.get(4);

        // Exibir os números gerados (para depuração)
        System.out.println("Números aleatórios gerados:");
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(num5);
    }

    public void obs1(Graphics g) {
        g.drawImage(imagemobs1, num1, CHAO - 36, null);
    }

    public void obs2(Graphics g) {
        g.drawImage(imagemobs2, num2, CHAO - 166, null);
    }

    public void obs3(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(num3, CHAO - ALTURA_OBSTACULO, LARGURA_OBSTACULO, ALTURA_OBSTACULO);
    }

    public void obs4(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(num4, CHAO - ALTURA_OBSTACULO, LARGURA_OBSTACULO, ALTURA_OBSTACULO);
    }

    public void nivel(Graphics g) {
        obs1(g);
        obs2(g);
        obs3(g);
        obs4(g);
    }

    public int getNivel() {
        return nivel;
    }

    public void verificarColisaoChave() {
        if (!chave && personagem.getBounds().intersects(boundsChave)) {
            chave = true;
        }
    }

    public void ChaveEPorta(Graphics g) {
        if (!chave) {
            g.drawImage(imagemChave, boundsChave.x, boundsChave.y, null);
            g.drawImage(imagemPortaFechada, WIDTH - 100, CHAO - 100, null);
        } else {
            g.drawImage(imagemPortaAberta, WIDTH - 100, CHAO - 100, null);
        }
    }

    public void verificarColisaoPorta() {
        Rectangle boundsPorta = new Rectangle(WIDTH - 100, CHAO - 100, imagemPortaFechada.getWidth(null), imagemPortaFechada.getHeight(null));
        if (chave && personagem.getBounds().intersects(boundsPorta)) {
            nivel++;
            chave = false;
            generateRandomNumbers();
            Personagem.PersonagemStart();
        }
    }

    public void verificarColisaoObstaculos() {
        Rectangle boundsPersonagem = personagem.getBounds();
        Rectangle boundsObs1 = new Rectangle(num1, CHAO - 36, imagemobs1.getWidth(null), imagemobs1.getHeight(null));
        Rectangle boundsObs2 = new Rectangle(num2, CHAO - 166, imagemobs2.getWidth(null), imagemobs2.getHeight(null));
        Rectangle boundsObs3 = new Rectangle(num3, CHAO - ALTURA_OBSTACULO, LARGURA_OBSTACULO, ALTURA_OBSTACULO);
        Rectangle boundsObs4 = new Rectangle(num4, CHAO - ALTURA_OBSTACULO, LARGURA_OBSTACULO, ALTURA_OBSTACULO);

        if (!colidiuObs && boundsPersonagem.intersects(boundsObs1)) {
            personagem.setVida(personagem.getVida() - 25);
            colidiuObs = true;
            System.out.println("Colidiu com obstáculo 1");
            reset();
        }

        if (!colidiuObs && boundsPersonagem.intersects(boundsObs2)) {
            personagem.setVida(personagem.getVida() - 25);
            colidiuObs = true;
            System.out.println("Colidiu com obstáculo 2");
            reset();
        }

        if (!colidiuObs && boundsPersonagem.intersects(boundsObs3)) {
            personagem.setVida(personagem.getVida() - 25);
            colidiuObs = true;
            System.out.println("Colidiu com obstáculo 3");
            reset();
        }

        if (!colidiuObs && boundsPersonagem.intersects(boundsObs4)) {
            personagem.setVida(personagem.getVida() - 25);
            colidiuObs = true;
            System.out.println("Colidiu com obstáculo 4");
            reset();
        }
    }

    public void reset() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                colidiuObs = false;
            }
        }, 1000);
    }
}
