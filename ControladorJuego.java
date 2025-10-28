import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// CONTROLADOR DEL JUEGO
public class ControladorJuego {
    private ArrayList<Personaje> heroes;
    private ArrayList<Personaje> enemigos;
    private int turnoActual = 0;

    public ControladorJuego(ArrayList<Personaje> heroes, ArrayList<Personaje> enemigos) {
        this.heroes = heroes;
        this.enemigos = enemigos;
    }

    public ArrayList<Personaje> getHeroes() {
        return heroes;
    }

    public ArrayList<Personaje> getEnemigos() {
        return enemigos;
    }

    //Ejecuta laaccion del heroe (1=atacar, 2 = habilidad)
    public String accionHeroe(int indiceHeroe, int tipoAccion, int indiceObjetivo){
        Personaje heroe = heroes.get(indiceHeroe);
        Personaje enemigo = enemigos.get(indiceObjetivo);
        StringBuilder resultado = new StringBuilder();

        if (tipoAccion == 1) {
            // Ataque normal
            resultado.append(heroe.getNombre()).append(" ataca a").append(enemigo.getNombre()).append("...\n");
            heroe.atacar(enemigo);
        } else if (tipoAccion == 2) {
            // Aqui puedes adaptar para las habilidades especiales
            resultado.append(heroe.getNombre()).append(" intenta usar una habilidad especial...\n");
        }


        // Verificar si el enemigo fue derrotado 
        if(!enemigo.estaVivo()){
            resultado.append(enemigo.getNombre()).append(" ha sido derrotado.\n");
            enemigos.remove(indiceObjetivo);
        }

        return resultado.toString();
    }

    // Turno de los Enemigos
    public String turnoEnemigos(){
        StringBuilder resultado = new StringBuilder();
        Random rand = new Random();

        for(Personaje enemigo : enemigos){
            if (heroes.isEmpty()) break;
            
            int objetivo = rand.nextInt(heroes.size());
            Personaje heroe = heroes.get(objetivo);

            resultado.append(enemigo.getNombre()).append(" ataca a ").append(heroe.getNombre()).append("...\n");
            enemigo.atacar(heroe);

            if (!heroe.estaVivo()) {
                resultado.append(heroe.getNombre()).append(" ha sido derrotado.\n");
                heroes.remove(objetivo);
            }
        }
        return resultado.toString();
    }

    // Verificamos si aun hay herores vivos
    public boolean hayHeroesVivos(){
        return !heroes.isEmpty();
    }

    // Verificamos si aun hay enemigos vivos
    public boolean hayEnemigosVivos(){
        return !enemigos.isEmpty();
    }

    
}