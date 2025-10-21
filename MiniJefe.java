import java.util.Random;

public class MiniJefe extends Enemigo {
    private boolean habilidadUsada;

    public MiniJefe(String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        super(TipoEnemigo.DRAGON, nombre, hp, mp, ataque, defensa, velocidad);
        this.habilidadUsada = false;
    }

    public String usarHabilidadEspecial(Heroe objetivo) {
        if (!habilidadUsada) {
            int danio = getAtaque() * 2;
            objetivo.recibirDanio(danio);
            habilidadUsada = true;
            return getNombre() + " usa su Habilidad Especial y hace " + danio + " de daño a " + objetivo.getNombre() + "!\n";
        }
        return "";
    }

    @Override
    public String atacar(Heroe objetivo) {
        Random r = new Random();
        if (!habilidadUsada && r.nextDouble() < 0.3) { // 30% chance de habilidad especial
            return usarHabilidadEspecial(objetivo);
        }

        int danio = getAtaque() - objetivo.getDefensa();
        if (danio < 0) danio = 0;
        objetivo.recibirDanio(danio);
        return getNombre() + " ataca a " + objetivo.getNombre() + " causando " + danio + " de daño.\n";
    }
}
