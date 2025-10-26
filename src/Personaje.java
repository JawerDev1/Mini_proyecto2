public class Personaje {

    private TipoPersonaje tipo;
    private String nombre;
    private int hp;
    private int mp;
    private int ataque;
    private int defensa;
    private int velocidad;
    
    public Personaje(
             TipoPersonaje tipo,
             String nombre,
             int hp,
             int mp,
             int ataque,
             int defensa,
             int velocidad       
    ){

        this.tipo = tipo;
        this.nombre = nombre;
        this.hp = hp;
        this.mp = mp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;

    }

    public TipoPersonaje getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void atacar(Personaje enemigo){
        int danio = Math.max(1, this.ataque - enemigo.defensa);
        enemigo.recibirDanio(danio);
        System.out.println(nombre + " ataca a " + enemigo.nombre + " e inflinge " + danio + " de danio" );
    }

    public void recibirDanio(int danio){

        hp -= danio;
        if(hp < 0) hp = 0;
        System.out.println(nombre + "recibe" + danio + " de daño. HP restante: " + hp);
    }

    public void gastarMp(int cantidad){

        mp -= cantidad;
        if(mp < 0) mp = 0;
        System.out.println(nombre + " gasta " + cantidad + " MP. MP restante: " + mp);
    }

    public void curar(int cantidad){

        hp += cantidad;
        System.out.println(nombre + " se cura " + cantidad + "HP.HP actual: " + hp);
    }

    public boolean estaVivo(){

        return hp > 0;
    }

    public void mostrarEstado(){
        System.out.println("------------------------------");
        System.out.println("Estado de " + nombre);
        System.out.println("Tipo" + tipo);
        System.out.println( "HP" + hp);
        System.out.println("MP" + mp);
        System.out.println("Ataque" + ataque);
        System.out.println("Defensa" + defensa);
        System.out.println("Velocidad" + velocidad);
        System.out.println("------------------------------");
    }


 

    

}
