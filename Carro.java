package TrabalhoPooT;

import java.util.concurrent.ThreadLocalRandom;

public class Carro implements Runnable {

    private final String nome;
    private final double distanciaTotalCorrida;
    private volatile double distanciaPercorrida;
    private boolean pitStopFeito;

    public Carro(String nome, double distanciaTotalCorrida) {
        this.nome = nome;
        this.distanciaTotalCorrida = distanciaTotalCorrida;
        this.distanciaPercorrida = 0.0;
        this.pitStopFeito = false;
    }

    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        while (distanciaPercorrida < distanciaTotalCorrida) {
            double avanco = 5 + random.nextDouble() * 20;
            distanciaPercorrida += avanco;

            if (distanciaPercorrida > distanciaTotalCorrida) {
                distanciaPercorrida = distanciaTotalCorrida;
            }

            System.out.printf("%s andou %.1f metros e já percorreu %.1f de %.1f metros.%n",
                    nome, avanco, distanciaPercorrida, distanciaTotalCorrida);

            if (!pitStopFeito
                    && distanciaPercorrida >= distanciaTotalCorrida / 2.0
                    && random.nextInt(4) == 0) {
                pitStopFeito = true;
                System.out.printf("[PIT STOP] %s entrou nos boxes para uma parada rápida!%n", nome);
                dormir(random.nextInt(1500, 2500));
                System.out.printf("[PIT STOP] %s voltou para a pista!%n", nome);
            }

            dormir(random.nextInt(100, 501));
        }

        System.out.printf("[CHEGADA] O %s cruzou a linha de chegada!%n", nome);

        Podio.registrarChegada(nome);
    }

    private void dormir(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getNome() {
        return nome;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }
}