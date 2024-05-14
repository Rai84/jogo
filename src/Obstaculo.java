import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

public class Obstaculo {
    public static void obs1(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(100, 100, 50, 50); 
    }
    public static void obs2(Graphics g){
        g.setColor(Color.black);
        g.fillRect(100, 200, 50, 50); 
    }
    public static void obs3(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(100, 300, 50, 50); 
    }
    public static void obs4(Graphics g){
        g.setColor(Color.green);
        g.fillRect(100, 400, 50, 50); 
    }

    public static void nivel(Graphics g){
        List<Runnable> obstaculo = new ArrayList<>();

        obstaculo.add(() -> obs1(g));
        obstaculo.add(() -> obs2(g));
        obstaculo.add(() -> obs3(g));
        obstaculo.add(() -> obs4(g));

        Collections.shuffle(obstaculo);

        for (Runnable obs : obstaculo) {
            obs.run();
        }

    }
}
