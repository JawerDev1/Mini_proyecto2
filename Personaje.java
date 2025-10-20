import java.util.ArrayList;
import java.util.List;

public abstract class Personaje {
    private String nombre;
    private int hp;
    private int mp;
    private int ataque;
    private int defensa;
    private int velocidad;
    private Estado estado;
    private List<Habilidad> habilidades;

    public Personaje(String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.hp = hp;
        this.mp = mp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.estado = Estado.VIVO;
        this.habilidades = new ArrayList<>();
    }

    public boolean estaVivo() {
        return this.estado == Estado.VIVO;
    }

    public void recibirDanio(int danio) {
        this.hp -= danio;
        if (this.hp <= 0) {
            this.hp = 0;
            this.estado = Estado.MUERTO;
        }
    }

    public void curar(int cantidad) {
        if (this.estado == Estado.VIVO) {
            this.hp += cantidad;
        }
    }

    public abstract void mostrarInfo();

    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getMp() { return mp; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }
    public Estado getEstado() { return estado; }
    public List<Habilidad> getHabilidades() { return habilidades; }

    public void setHp(int hp) { this.hp = hp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
}
