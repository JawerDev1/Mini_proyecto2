# 🐉 Mini Proyecto 2 - Dragon Quest  
### Universidad del Valle  
**Asignatura:** Programación Orientada a Eventos  
**Lenguaje:** Java (POO + Swing)  

---

## 👥 Integrantes del grupo
| Nombre                         | Rol / Aporte principal                                   | Código       |
|--------------------------------|----------------------------------------------------------|--------------|
| **Kevin Andrés Rosero Romo**   | Lógica base del combate y desarrollo de clases principales | 2459554-2724 |
| **Jhon Jawer Cuero Gómez**     | Interfaz gráfica y sistema de sonido | 2459544-2724 |


---

## 🧩 Descripción general del proyecto

Este proyecto implementa un **sistema de combate tipo RPG (Dragon Quest)** utilizando **Java** y **Swing**.  
El objetivo principal es aplicar los principios de **Programación Orientada a Objetos (POO)** y **manejo de eventos gráficos** en un entorno interactivo.

Los jugadores controlan un grupo de héroes que se enfrentan a varios enemigos en turnos, utilizando ataques, curaciones y habilidades especiales.  
A medida que avanza la batalla, aparece un **Mini Jefe** con una habilidad especial.

---

## 🧱 Arquitectura del proyecto

El proyecto está dividido en **módulos y clases** bien organizadas que reflejan los conceptos de herencia, encapsulamiento y polimorfismo.

### 🔸 Clases principales

| Clase | Descripción |
|--------|-------------|
| **Personaje.java** | Clase abstracta base para héroes y enemigos. Contiene atributos comunes como HP, MP, ataque, defensa y velocidad. |
| **Heroe.java** | Representa a los héroes controlados por el jugador. Puede atacar, curarse y usar habilidades. |
| **Enemigo.java** | Representa a los enemigos comunes. Tiene métodos de ataque simples. |
| **Combate.java / CombateGUI.java** | Controlan la lógica de turnos y la interfaz gráfica del combate. |
| **MiniJefe.java** | Nuevo tipo de enemigo con habilidad especial (ataque doble). |
| **Habilidad.java** | Define las habilidades con nombre, tipo (ataque/curación) y costo de MP. |
| **Sonido.java** | Permite reproducir efectos de sonido durante las acciones del combate. |
| **Tipos y Enums** | `TipoHeroe`, `TipoEnemigo`, `TipoHabilidad`, `Estado` organizan los posibles valores de cada categoría. |

---

## 🎮 Funcionalidades principales

✅ Sistema de combate por turnos.  
✅ Ataques físicos, curaciones y hechizos.  
✅ Aparición automática de un **Mini Jefe** después de derrotar tres enemigos.  
✅ **Efectos de sonido** en cada acción (ataque, curación, derrota, hechizo).  
✅ Visualización del **HP actual** de héroes y enemigos después de cada acción.  
✅ Interfaz gráfica sencilla con botones para las acciones principales.  

---

## 🧰 Tecnologías utilizadas

- **Java 17+**
- **Swing** (Interfaz gráfica)
- **POO** (Herencia, polimorfismo, encapsulamiento)
- **javax.sound.sampled** (manejo de audio)
- **Git / GitHub** (control de versiones en equipo)

---

## ⚙️ Ejecución del proyecto

### 1️⃣ Compilar todos los archivos:
```bash
javac *.java
