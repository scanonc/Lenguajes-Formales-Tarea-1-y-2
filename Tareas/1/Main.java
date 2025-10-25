import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = Integer.parseInt(sc.nextLine().trim());

        for (int c = 0; c < casos; c++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            String[] alfabeto = sc.nextLine().trim().split("\\s+");

            String[] finalesStr = sc.nextLine().trim().split("\\s+");
            Set<Integer> estadosFinales = new HashSet<>();
            for (String f : finalesStr) {
                estadosFinales.add(Integer.parseInt(f));
            }

            int[][] transiciones = new int[n][alfabeto.length];
            for (int i = 0; i < n; i++) {
                String[] partes = sc.nextLine().trim().split("\\s+");
                for (int j = 0; j < alfabeto.length; j++) {
                    transiciones[i][j] = Integer.parseInt(partes[j]);
                }
            }

            Set<Set<Integer>> particiones = new HashSet<>();
            Set<Integer> finales = new HashSet<>(estadosFinales);
            Set<Integer> noFinales = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!finales.contains(i)) noFinales.add(i);
            }
            if (!finales.isEmpty()) particiones.add(finales);
            if (!noFinales.isEmpty()) particiones.add(noFinales);

            boolean cambio = true;
            while (cambio) {
                cambio = false;
                Set<Set<Integer>> nuevasParticiones = new HashSet<>();

                for (Set<Integer> grupo : particiones) {
                    Map<String, Set<Integer>> subgrupos = new HashMap<>();

                    for (int estado : grupo) {
                        StringBuilder firma = new StringBuilder();
                        for (int simbolo = 0; simbolo < alfabeto.length; simbolo++) {
                            int siguiente = transiciones[estado][simbolo];
                            int idGrupo = obtenerIdGrupo(siguiente, particiones);
                            firma.append(idGrupo).append(",");
                        }
                        String clave = firma.toString();
                        subgrupos.computeIfAbsent(clave, k -> new HashSet<>()).add(estado);
                    }

                    nuevasParticiones.addAll(subgrupos.values());
                    if (subgrupos.size() > 1) cambio = true;
                }

                particiones = nuevasParticiones;
            }

            List<String> pares = new ArrayList<>();
            for (Set<Integer> grupo : particiones) {
                if (grupo.size() > 1) {
                    List<Integer> lista = new ArrayList<>(grupo);
                    Collections.sort(lista);
                    for (int i = 0; i < lista.size(); i++) {
                        for (int j = i + 1; j < lista.size(); j++) {
                            pares.add("(" + lista.get(i) + ", " + lista.get(j) + ")");
                        }
                    }
                }
            }

            Collections.sort(pares);
            System.out.println(String.join(" ", pares));
        }

        sc.close();
    }

    private static int obtenerIdGrupo(int estado, Set<Set<Integer>> particiones) {
        int id = 0;
        for (Set<Integer> grupo : particiones) {
            if (grupo.contains(estado)) return id;
            id++;
        }
        throw new RuntimeException("Estado no encontrado " + estado);
    }
}
