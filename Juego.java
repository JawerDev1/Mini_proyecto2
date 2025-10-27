import java.util.*;

public class Juego {

    private static Scanner scanner = new Scanner(System.in); // lee los datos del usuario

        // Lista para almacenar heroes y enemigos
        public static void main(String[] args) {
        List<Jugador> heroes = new ArrayList<>();
        List<Enemigo> enemigos = new ArrayList<>();

        //creacion de los heroes 
        heroes.add(new Jugador(TipoHeroe.HEROE, "Heroe", 45, 8, 8, 6, 6));
        heroes.add(new Jugador(TipoHeroe.YANGUS, "Yangus", 50, 0, 10, 8, 3));
        heroes.add(new Jugador(TipoHeroe.YESSICA, "Jessica", 40, 14, 7, 5, 7));
        heroes.add(new Jugador(TipoHeroe.ANGELO, "Angelo", 42, 12, 8, 6, 6));

        //creacion de los enemigos
        enemigos.add(new Enemigo(TipoEnemigo.SPIKED_HARE, "Spiked Hare", 30, 6, 6, 5, 7));
        enemigos.add(new Enemigo(TipoEnemigo.DRACKY, "Dracky", 35, 6, 7, 5, 8));
        enemigos.add(new Enemigo(TipoEnemigo.PATYPUNK, "PatyPunk", 40, 4, 9, 7, 5));
        enemigos.add(new Enemigo(TipoEnemigo.TERROR_TABBY, "Terror Tabby", 42, 8, 9, 6, 7)); // NUEVO ENEMIGO

       
         System.out.println("=== DRAGON QUEST");
         iniciarCombate(heroes, enemigos); // Se inicia la batalla entre heroes y enemigos
    }

    // Metodo que controla el flujo dle combate
    public static void iniciarCombate(List<Jugador> heroes, List<Enemigo> enemigos) {
        Random random = new Random();// para desiciones aleatorias, ataques o hailiades

        // Bucle principal del combate, meintras haya gente viva el juego sigue
        while (hayVivos(heroes) && hayVivos(enemigos)) {

            // Turnos De heroes
            for (Jugador j : heroes) {
                if (!j.estaVivo()) continue;

                // Si el héroe está dormido, no puede hacer nada
                if (j.getEstado() == Estado.DORMIDO) {
                    System.out.println("\n " + j.getNombre() + " esta dormido y no puede actuar.");
                    j.reducirTurnosEstado(); // Disminuye la duracion del sueño 
                    continue;
                }

                // Muestra Informacion Basica del heroe
                System.out.println("\n--- Turno de " + j.getNombre() + " ---");
                System.out.println("HP: " + j.getHp() + " | MP: " + j.getMp());

                //Muestar enemigos disponibles 
                mostrarEnemigos(enemigos);
                System.out.print("Elige un enemigo (numero): ");
                int opcion = leerEntero() - 1;

                // Para validar la opcion elegida
                if (opcion < 0 || opcion >= enemigos.size()) {
                    System.out.println(" Opcion invalida.");
                    continue;
                }


                Enemigo objetivo = enemigos.get(opcion);
                if (!objetivo.estaVivo()) {
                    System.out.println(" Ese enemigo ya fue derrotado.");
                    continue;
                }

                // Menu de acciones del heroe
                System.out.println("1. Ataque normal");
                System.out.println("2. Habilidad especial");
                System.out.print("Elige accion: ");
                int accion = leerEntero();

                if (accion == 2)
                    j.usarHabilidadEspecial(objetivo);
                else
                    j.atacar(objetivo);

                
                if (!objetivo.estaVivo())
                    System.out.println( objetivo.getNombre() + " ha sido derrotado.");
            }

            // Si ya no hay enemigos vivos termiana el comate
            if (!hayVivos(enemigos)) break;

            //Turno de los enemigos
            for (Enemigo e : enemigos) {
                if (!e.estaVivo()) continue;

                // Verifica el estado del enemigo si esta domrido o despierto, por ahora no se usa
                if (e.getEstado() == Estado.DORMIDO) {
                    System.out.println("\n " + e.getNombre() + " esta dormido y no puede actuar.");
                    e.reducirTurnosEstado();
                    continue;
                }

                // Se elige un heroe vivo al azar
                Jugador objetivoHeroe = obtenerHeroeVivoAleatorio(heroes);
                if (objetivoHeroe == null) break; // si no hay heroes vivo termian el combate

                System.out.println("\n Turno de " + e.getNombre());

                //probabilidad de usar ataque especial o normal
                boolean usarEspecial = random.nextBoolean();

                if (usarEspecial) {
                    // Cada Enmigo tiene su habilidad especial distinta
                    switch (e.getTipoEnemigo()) {
                        case SLIME:
                            e.ataqueSlime(objetivoHeroe);
                            break;
                        case DRACKY:
                            e.picotazo(objetivoHeroe);
                            break;
                        case PATYPUNK:
                            e.golpeGarrote(objetivoHeroe);
                            break;
                        case SPIKED_HARE:
                            e.patadaGiratoria(objetivoHeroe);
                            break;
                        case TERROR_TABBY: // 
                            e.sleepAttack(objetivoHeroe);
                            break;
                    }
                } else {
                    // si no la sua tira un ataque normal
                    e.ataqueNormal(objetivoHeroe);
                }

                if (!objetivoHeroe.estaVivo())
                    System.out.println(objetivoHeroe.getNombre() + " ha sido derrotado.");
            }
        }

        
        if (hayVivos(heroes))
            System.out.println("\n ¡Los heroes han ganado la batalla!");
        else
            System.out.println("\n Los enemigos han triunfado...");
    }

    // Metodos Auxiliares

    // Comprueba si aun hay personajes vivos en la lista
    private static boolean hayVivos(List<? extends Personaje> lista) {
        for (Personaje p : lista) {
            if (p.estaVivo()) return true;
        }
        return false;
    }

    //Muastra todos los enemigos en pantalla con sus estadisticas
    private static void mostrarEnemigos(List<Enemigo> enemigos) {
        System.out.println("\nEnemigos:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            String estado = e.estaVivo() ? "(HP: " + e.getHp() + ")" : "(DERROTADO)";
            System.out.println((i + 1) + ". " + e.getNombre() + " " + estado);
        }
    }

    //Devuelve un heroe aleatorio que este vivo
    private static Jugador obtenerHeroeVivoAleatorio(List<Jugador> heroes) {
        List<Jugador> vivos = new ArrayList<>();
        for (Jugador h : heroes) {
            if (h.estaVivo()) vivos.add(h);
        }
        if (vivos.isEmpty()) return null;
        return vivos.get(new Random().nextInt(vivos.size()));//selecciona aleartoria
    }

    //Lee un numero entero en la consola, para evitar erreoes de entrada
    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Ingresa un numero válido: ");
            }
        }
    }
}
