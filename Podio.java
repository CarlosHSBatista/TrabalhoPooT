package TrabalhoPooT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Podio {

    private static final List<String> vencedores = Collections.synchronizedList(new ArrayList<>());
    private static final Object lock = new Object();

    public static void registrarChegada(String nomeCarro) {
        synchronized (lock) {
            if (vencedores.size() < 3) {
                vencedores.add(nomeCarro);
                int posicao = vencedores.size();
                System.out.printf("[PÓDIO] %s garantiu o %dº lugar!%n", nomeCarro, posicao);
            }
        }
    }

    public static void exibirResultadoFinal() {
        System.out.println("\n===== RESULTADO FINAL DA CORRIDA =====");
        String[] medalhas = {"1º lugar", "2º lugar", "3º lugar"};
        synchronized (lock) {
            for (int i = 0; i < vencedores.size(); i++) {
                System.out.printf("%s: %s%n", medalhas[i], vencedores.get(i));
            }
        }
        System.out.println("=======================================");
    }
}
