    import java.util.Random;

    public class Jugador extends Personaje {

        private TipoHeroe tipoHeroe; // Que tipo de Heroe es, solo son 4 heroes 
        private Random random = new Random(); //Para ataques con daño aleatorio o probabilidad de acertar

        // Constructor
        public Jugador(TipoHeroe tipoHeroe, String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
            super(TipoPersonaje.HEROE, nombre, hp, mp, ataque, defensa, velocidad);
            this.tipoHeroe = tipoHeroe;
        }

        public TipoHeroe getTipoHeroe() {
            return tipoHeroe;
        }

    //Ataque Normal
    // Tdoos los heroes hacen un ataque babsico
        public void ataqueNormal(Personaje objetivo) {
            atacar(objetivo); //Este metodo es de la clase Personaje
        }

        // Hailidades Especiales

        //Heroe -> Cura: Recupera 12HP, consume 3MP
        public void cura12() {
            if (getMp() >= 3) { // Para verificar que tenga suficiente MP
                gastarMp(3);
                curar(12);
                System.out.println(getNombre() + " usa Cura y recupera 12 HP.");
            } else {
                System.out.println(getNombre() + " no tiene suficiente MP para usar Cura.");
            }
        }

        // Angelo -> Cura: Recupera 15HP, consume 4MP
        public void cura15() {
            if (getMp() >= 4) {
                gastarMp(4);
                curar(15);
                System.out.println(getNombre() + " usa Cura y recupera 15 HP.");
            } else {
                System.out.println(getNombre() + " no tiene suficiente MP para usar Cura.");
            }
        }

        // Yessica -> Frizz: Daño magico entre 10 y 15, consume 4MP
        public void frizz(Personaje objetivo) {
            if (getMp() >= 4) {
                gastarMp(4);
                int danioMagico = random.nextInt(6) + 10; // 10-15
                objetivo.recibirDanio(danioMagico);
                System.out.println(getNombre() + " lanza Frizz e inflige " + danioMagico + " de daño magico a " + objetivo.getNombre() + ".");
            } else {
                System.out.println(getNombre() + " no tiene suficiente MP para lanzar Frizz.");
            }
        }

        // Yangus -> Golpe poderoso: +50% daño, 80% probabilidad de acertar
        public void golpePoderoso(Personaje objetivo) {
            int probabilidad = random.nextInt(100) + 1;// 1 a 100
            if (probabilidad <= 30) { // 30% de exito
                int danioBase = Math.max(1, getAtaque() - objetivo.getDefensa());
                int danioTotal = (int) (danioBase * 1.5); // Aumenta Daño 50%
                objetivo.recibirDanio(danioTotal);
                System.out.println(getNombre() + " usa Golpe Poderoso e inflige " + danioTotal + " de daño a " + objetivo.getNombre() + ".");
            } else {
                System.out.println(getNombre() + " falla Golpe Poderoso.");
            }
        }

    
        // Metodo Que Llama El juego

        //Segun el tipo de heroe usa la habilidad especial correcta
        
        public void usarHabilidadEspecial(Personaje objetivo) {
            switch (tipoHeroe) {
                case HEROE:
                    
                    this.cura12();
                    break;
                case ANGELO:
                    
                    this.cura15();
                    break;
                case YESSICA:
                    
                    if (objetivo != null && objetivo.estaVivo()) {
                        this.frizz(objetivo);
                    } else {
                        System.out.println(getNombre() + " no tiene un objetivo valido para Frizz.");
                    }
                    break;
                case YANGUS:
                    
                    if (objetivo != null && objetivo.estaVivo()) {
                        this.golpePoderoso(objetivo);
                    } else {
                        System.out.println(getNombre() + " no tiene un objetivo valido para Golpe Poderoso.");
                    }
                    break;
                default:
                    System.out.println(getNombre() + " no tiene una habilidad especial definida.");
                    break;
            }
        }
    }
