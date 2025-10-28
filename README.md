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
