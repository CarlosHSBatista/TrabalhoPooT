package TrabalhoPooT;

import java.util.ArrayList;
import java.util.List;

public class Corrida {

    private static final double DISTANCIA_TOTAL = 1000.0;
    private static final int NUMERO_DE_CARROS = 5;

    public static void main(String[] args) throws InterruptedException {
        List<Carro> carros = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        System.out.println("Preparando a corrida! Distância total: " + DISTANCIA_TOTAL + " metros.");
        System.out.println("Carros na largada: " + NUMERO_DE_CARROS);
        System.out.println("Em suas marcas... apontem... já!\n");

        for (int i = 1; i <= NUMERO_DE_CARROS; i++) {
            Carro carro = new Carro("Carro " + i, DISTANCIA_TOTAL);
            Thread thread = new Thread(carro, "Thread-Carro-" + i);
            carros.add(carro);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Podio.exibirResultadoFinal();
    }
}
