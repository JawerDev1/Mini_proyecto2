DRAGON QUEST – Módulo de Batalla
Implementación del módulo de combate, interfaz gráfica y sistema de audio para un JRPG.

Desarrollador
Jhon Jawer Cuero Gomez

Componentes y Funcionalidades
A continuación se describen las clases y archivos principales del proyecto.

.gitignore
Archivo de configuración para Git que ignora archivos compilados, temporales y específicos del IDE.

Exclusiones clave:

Archivos compilados (.class)

Carpetas de salida (/out, /bin)

Archivos de configuración del IDE (.idea/, .vscode/)

Archivos de sistema (.DS_Store, Thumbs.db)

Objetivo: Evitar subir archivos innecesarios al repositorio y mantener un control de versiones limpio.

🎮 ControladorJuego.java
Clase que maneja la lógica central del combate entre héroes y enemigos. Coordina turnos, ataques, efectos de estado (como “Sueño”) y determina el fin de la batalla.

Responsabilidades principales:

Controlar el flujo de turnos.

Validar si un personaje está vivo o dormido.

Aplicar ataques normales y especiales.

Detectar el fin de batalla (victoria o derrota).

🪄 InterfazJuego.java
Implementa la interfaz gráfica (GUI) de la batalla utilizando Java Swing.

Características:

Panel de registro visual del combate (JTextArea con JScrollPane).

Panel de acciones (JPanel) con botones y selección de enemigo (JComboBox).

Botones de acción: Atacar y Habilidad.

Muestra mensajes detallados de daño, HP restante, efectos y enemigos derrotados.

Los turnos avanzan automáticamente al ejecutar una acción (sin botón "Pasar Turno").

Objetivo: Proporcionar una experiencia visual interactiva y ordenada del combate.

AudioPlayer.java
Clase que gestiona la música del juego, implementando el patrón de diseño Singleton.

Funciones principales:

Reproduce música de fondo (musica_batalla.wav) en bucle.

Detiene y reinicia la pista según el estado de la batalla (inicio o fin).

Evita que se solapen varias pistas de audio.

Permite cargar archivos de audio desde el sistema de archivos o el classpath del proyecto.

Objetivo: Agregar ambientación sonora que se activa al iniciar la batalla y se detiene al finalizar.

MenuPrincipal.java
Ventana inicial y punto de entrada (main) de la aplicación. Permite al jugador navegar entre las opciones del sistema.

Elementos del menú:

Iniciar batalla: Lanza la InterfazJuego y activa la música a través del AudioPlayer.

Créditos: Muestra información de los desarrolladores.

Salir: Cierra la aplicación (System.exit(0)).

Carpeta music/
Contiene los archivos de audio del proyecto.

music/musica_batalla.wav (Utilizada por AudioPlayer.java)

Ejecución
Abre el proyecto en tu IDE preferido (Eclipse, IntelliJ, VS Code con extensión de Java).

Asegúrate de que la carpeta music/ esté en la raíz del proyecto. La estructura de archivos debe ser similar a esta:

TuProyecto/
├── src/
│   ├── ControladorJuego.java
│   ├── InterfazJuego.java
│   ├── AudioPlayer.java
│   ├── MenuPrincipal.java
│   └── ... (otras clases)
├── music/
│   └── musica_batalla.wav
└── README.md
Ejecuta la clase MenuPrincipal.java.

En el menú principal, presiona "Iniciar batalla" para comenzar.

Tecnologías Utilizadas
Java SE 17+

Java Swing (javax.swing, java.awt) para la interfaz gráfica.

javax.sound.sampled para el manejo de audio.

Programación Orientada a Objetos (POO) y Programación Orientada a Eventos.

Dragon Quest VIII           

Kevin Andres Rosero Romo – 2459554-2724

 Actualización y mejoras

Esta versión del código representa una actualización respecto a la versión inicial, con varias mejoras para asemejarse más al juego original y mejorar la sostenibilidad del código:

1. Mejoras en la estructura del código

Se reorganizó el proyecto en clases modulares (Personaje, Jugador, Enemigo, Juego) para cumplir mejor con principios de programación orientada a objetos.

Se añadieron enums (TipoHeroe, TipoEnemigo, Estado, TipoPersonaje) para manejar los tipos de personajes y estados de manera más clara y escalable.

Se implementaron métodos bien definidos para ataques, habilidades especiales y manejo de estados alterados (DORMIDO), lo que hace el juego más robusto y sostenible a largo plazo.

2. Héroes y habilidades

Héroes disponibles:

Heroe: Cura básica (12 HP, consume 3 MP)

Angelo: Cura avanzada (15 HP, consume 4 MP)

Jessica: Frizz (daño mágico 10–15, consume 4 MP)

Yangus: Golpe poderoso (+50% daño, 30% probabilidad de acertar)

Nota: Cada héroe solo puede usar su habilidad especial correspondiente.

3. Enemigos y mini jefe

Enemigos clásicos agregados: Slime, Dracky, PatyPunk, Spiked Hare.

Mini jefe: Terror Tabby

Tiene estadísticas superiores a los demás enemigos.

Posee un ataque especial (Sleep Attack) que puede dormir a los héroes durante 2 turnos con un 90% de probabilidad.

También inflige daño adicional (4–7 HP).

Esto lo convierte en un enemigo desafiante y fiel al concepto de mini jefe del juego original.

4. Mecánica del combate

Los turnos se alternan entre héroes y enemigos.

Los héroes pueden elegir entre ataque normal o habilidad especial.

Los enemigos pueden usar ataques normales o habilidades especiales de forma aleatoria.

Los personajes afectados por estados alterados (DORMIDO) pierden su turno hasta que el efecto desaparezca.

El combate termina cuando todos los héroes o todos los enemigos son derrotados.

5. Mejoras de estabilidad

Se validan entradas del usuario y objetivos de ataques.

Se controla el gasto de MP y curaciones para evitar errores de ejecución.

El juego maneja correctamente la resolución de estados alterados y los turnos de los efectos especiales.

Resumen

Esta versión actualizada:

Se acerca más al juego original en cuanto a personajes y enemigos.

Introduce un mini jefe con mecánica especial de sueño.

Optimiza la sostenibilidad y robustez del código para facilitar futuras ampliaciones.