public class Personaje {

    //Artributos
    private TipoPersonaje tipo; 
    private String nombre; 
    private int hp; // Vida del Personaje
    private int mp; // Como yo se que usted no sabe que es esto, es para los ataques magicos de los heroes
    private int ataque;
    private int defensa;
    private int velocidad;

    // Atributos De Estado
    private Estado estado = Estado.NORMAL; // Estado actual(Por ahora solo hay 2 estados DORMIDO / NORMAL)
    private int turnosEstado = 0; // cuanto dura el estado si se aplica

    // Constructor 
    public Personaje(TipoPersonaje tipo, String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.hp = hp;
        this.mp = mp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    // Getters
    public TipoPersonaje getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getMp() { return mp; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }
    public Estado getEstado() { return estado; }

    // Metodos De Estado //
    // Cambia El Estado Interno y cuantos turno dura
    public void setEstado(Estado nuevoEstado, int turnos) {
        this.estado = nuevoEstado;
        this.turnosEstado = turnos;
    }

    // Aplica el nuevo estado
    public void aplicarEstado(Estado nuevoEstado, int turnos) {
        setEstado(nuevoEstado, turnos);
        if (nuevoEstado != Estado.NORMAL) {
            System.out.println(nombre + " ahora esta " + nuevoEstado + " por " + turnos + " turnos.");
        } else {
            System.out.println(nombre + " ha vuelto a la normalidad.");
        }
    }

    // Reduce los turnos de un estado, cuando termina vuelve a Normal
    public void reducirTurnosEstado() {
        if (estado != Estado.NORMAL) {
            turnosEstado--;
            if (turnosEstado <= 0) {
                estado = Estado.NORMAL;
                System.out.println(nombre + " se ha recuperado del estado alterado.");
            }
        }
    }

    // Comate //
    // Metodo para atacar a otro personaje
    public void atacar(Personaje enemigo) {
       
        if (estado == Estado.DORMIDO) {// No ataca si esta dormido, como usted en discretas
            System.out.println(nombre + " esta dormido y no puede atacar.");
            return;
        }

        int danio = this.ataque - (enemigo.getDefensa() / 2);
        if(danio < 1) danio = 1;
        enemigo.recibirDanio(danio);
    }

    //Recibir daño: Resta Vida 
    public void recibirDanio(int danio) {
        hp -= danio;
        if (hp < 0) hp = 0;
        System.out.println(nombre + " recibe " + danio + " de daño. HP restante: " + hp);
    }

    // Gasta MP y muestra un mensaje
    public void gastarMp(int cantidad) {
        mp -= cantidad;
        if (mp < 0) mp = 0;
        System.out.println(nombre + " gasta " + cantidad + " MP. MP restante: " + mp);
    }

    //Curar Vida
    public void curar(int cantidad) {
        hp += cantidad;
        System.out.println(nombre + " se cura " + cantidad + " HP. HP actual: " + hp);
    }

    //Comprueba si el personaje esta vivo
    public boolean estaVivo() {
        return hp > 0;
    }

    
    // Muestra el estado del Personaje //
    public void mostrarEstado() {
        System.out.println("------------------------------");
        System.out.println("Estado de " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Estado: " + estado);
        System.out.println("------------------------------");
    }
}
