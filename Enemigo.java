import java.util.Random;

public class Enemigo extends Personaje {

    private TipoEnemigo tipoEnemigo; // Para Identifcar que tipo de enemigo es (Por ahora solo tenemos 5 enemigos con el mini jefe)
    private Random random = new Random(); // Para generar numeros aleatorios en los ataques

    // Consrtuctor
    public Enemigo(TipoEnemigo tipoEnemigo, String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        super(TipoPersonaje.ENEMIGO, nombre, hp, mp, ataque, defensa, velocidad);
        this.tipoEnemigo = tipoEnemigo;
    }

    // Getter, asi identificamos que tipo de enemigo es 
    public TipoEnemigo getTipoEnemigo() {
        return tipoEnemigo;
    }

    // Ataque Normal
    public void ataqueNormal(Personaje objetivo) {
        int danio = calcularDanio(objetivo); // Calcula Daño Simple
        objetivo.recibirDanio(danio);        // Aplica daño al objetivo, osea a los heroes
        System.out.println(getNombre() + " ataca a " + objetivo.getNombre() + " causando " + danio + " de daño");
    }

    private int calcularDanio(Personaje objetivo) {
        
        int danio = getAtaque() - (objetivo.getDefensa() / 2); // Ataque menos la defensa del objetivo(En este caso lo heroes )
        if(danio > 1) danio = 1;
        
        return danio;
     }

    // Ataques Especiales De los Enemigos //

    // Slime
    public void ataqueSlime(Personaje objetivo) {
        if (tipoEnemigo != TipoEnemigo.SLIME) return;
        int danio = 3 + random.nextInt(4);
        objetivo.recibirDanio(danio);
        System.out.println(getNombre() + " usa Ataque basico y causa " + danio + " de daño");
    }

    // Dracky
    public void picotazo(Personaje objetivo) {
        if (tipoEnemigo != TipoEnemigo.DRACKY) return;
        double prob = Math.random();
        int danio = 5 + random.nextInt(4);
        if (prob <= 0.7) {
            System.out.println(getNombre() + " usa Picotazo y acierta.");
        } else {
            System.out.println(getNombre() + " falla Picotazo.");
            danio = 0;
        }
        objetivo.recibirDanio(danio);
    }

    // PatyPunk
    public void golpeGarrote(Personaje objetivo) {
        if (tipoEnemigo != TipoEnemigo.PATYPUNK) return;
        int danio = 6 + random.nextInt(5);
        objetivo.recibirDanio(danio);
        System.out.println(getNombre() + " usa Golpe con Garrote y causa " + danio + " de daño");
    }

    // Spiked Hare
    public void patadaGiratoria(Personaje objetivo) {
        if (tipoEnemigo != TipoEnemigo.SPIKED_HARE) return;
        int danio = 6 + random.nextInt(4);
        if (Math.random() <= 0.3) {
            System.out.println(getNombre() + " usa Patada Giratoria potenciada.");
            danio *= 1.2;
        } else {
            System.out.println(getNombre() + " usa Patada Giratoria.");
        }
        objetivo.recibirDanio(danio);
    }

    // Este es el mini jefe, Este mismo es el que usa el ataque para dormir, como usted en calculo
    public void sleepAttack(Personaje objetivo) {
        if (tipoEnemigo != TipoEnemigo.TERROR_TABBY) return;

        System.out.println(getNombre() + " usa ¡Sleep Attack!");
        int danio = 4 + random.nextInt(4); // Daño entre 4 y 7
        objetivo.recibirDanio(danio);

        // Probabilidad del 90% de dormir al objetivo
        if (Math.random() <= 0.9) {
            objetivo.aplicarEstado(Estado.DORMIDO, 2); // Duerme por 2 Turnos
        } else {
            System.out.println(objetivo.getNombre() + " resistio el sueño!");
        }
    }
}
