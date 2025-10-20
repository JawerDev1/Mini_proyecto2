public class Heroe extends Personaje {
    private TipoHeroe tipo;

    public Heroe(TipoHeroe tipo, String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        super(nombre, hp, mp, ataque, defensa, velocidad);
        this.tipo = tipo;
    }

    public TipoHeroe getTipo() {
        return this.tipo;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(getNombre() + " (" + this.tipo + ")");
        System.out.println("HP: " + getHp() + " | MP: " + getMp() +
                " | ATK: " + getAtaque() + " | DEF: " + getDefensa() +
                " | VEL: " + getVelocidad());
        System.out.println("Habilidades:");
        for (Habilidad h : getHabilidades()) {
            System.out.println(" - " + h);
        }
        System.out.println();
    }
}
