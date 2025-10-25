import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = Integer.parseInt(sc.nextLine().trim());

        for (int c = 0; c < casos; c++) {
            int k = Integer.parseInt(sc.nextLine().trim());

            Map<String, List<List<String>>> gramatica = new LinkedHashMap<>();
            List<String> noTerminales = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                String linea = sc.nextLine().trim();
                String[] partes = linea.split("->");
                String noTerminal = partes[0].trim();
                noTerminales.add(noTerminal);
                String[] alternativas = partes[1].trim().split("\\s+");

                List<List<String>> producciones = new ArrayList<>();
                for (String alternativa : alternativas) {
                    List<String> produccion = new ArrayList<>();
                    for (int j = 0; j < alternativa.length(); j++) {
                        produccion.add(String.valueOf(alternativa.charAt(j)));
                    }
                    producciones.add(produccion);
                }
                gramatica.put(noTerminal, producciones);
            }

            eliminarRecursion(gramatica, noTerminales);

            for (String nt : gramatica.keySet()) {
                System.out.print(nt + " -> ");
                List<List<String>> producciones = gramatica.get(nt);
                for (int i = 0; i < producciones.size(); i++) {
                    for (String s : producciones.get(i)) {
                        System.out.print(s);
                    }
                    if (i < producciones.size() - 1) System.out.print(" ");
                }
                System.out.println();
            }

            if (c < casos - 1) System.out.println();
        }

        sc.close();
    }

    private static void eliminarRecursion(Map<String, List<List<String>>> G, List<String> N) {
        for (int i = 0; i < N.size(); i++) {
            String Ai = N.get(i);

            if (!G.containsKey(Ai) || G.get(Ai).isEmpty()) continue;

            for (int j = 0; j < i; j++) {
                String Aj = N.get(j);
                List<List<String>> nuevasProducciones = new ArrayList<>();

                for (List<String> prod : G.get(Ai)) {
                    if (!prod.isEmpty() && prod.get(0).equals(Aj)) {
                        for (List<String> delta : G.get(Aj)) {
                            List<String> nueva = new ArrayList<>(delta);
                            nueva.addAll(prod.subList(1, prod.size()));
                            nuevasProducciones.add(nueva);
                        }
                    } else {
                        nuevasProducciones.add(prod);
                    }
                }
                G.put(Ai, nuevasProducciones);
            }

            List<List<String>> alfa = new ArrayList<>();
            List<List<String>> beta = new ArrayList<>();

            for (List<String> prod : G.get(Ai)) {
                if (!prod.isEmpty() && prod.get(0).equals(Ai)) {
                    alfa.add(prod.subList(1, prod.size()));
                } else {
                    beta.add(prod);
                }
            }

            if (!alfa.isEmpty()) {
                String nuevoNT = generarNuevoNT(G);
                List<List<String>> nuevasBeta = new ArrayList<>();
                List<List<String>> nuevasAlfa = new ArrayList<>();

                for (List<String> b : beta) {
                    List<String> nueva = new ArrayList<>(b);
                    nueva.add(nuevoNT);
                    nuevasBeta.add(nueva);
                }

                for (List<String> a : alfa) {
                    List<String> nueva = new ArrayList<>(a);
                    nueva.add(nuevoNT);
                    nuevasAlfa.add(nueva);
                }
                nuevasAlfa.add(Arrays.asList("e"));

                G.put(Ai, nuevasBeta);
                G.put(nuevoNT, nuevasAlfa);
                N.add(nuevoNT);
            }
        }
    }

    private static String generarNuevoNT(Map<String, List<List<String>>> G) {
        for (char c = 'Z'; c >= 'A'; c--) {
            String candidato = String.valueOf(c);
            if (!G.containsKey(candidato)) return candidato;
        }
        throw new RuntimeException("No hay letras disponibles para nuevos no terminales");
    }
}
