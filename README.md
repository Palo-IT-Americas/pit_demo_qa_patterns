# Patrones de diseño

El objetivo de este taller es brindar herramientas para roles de QA que buscan mejorar sus habilidades de abstraccion y desarrollo de codigo.

## Requerimientos
- Java 21
- Maven 3.x

## Patrones
Podemos definir patron como una serie de hechos que se repiten en el tiempo. Al repetirse en el tiempo podemos decir que es un evento predecible, por lo tanto podemos crear un modelo que nos permita predecir el comportamiento y de esta forma podemos tomar desiciones para controlar el resultado.

## Patrones de diseño

Al identificar un patorn (evento recurrente y predecible) 
podemos crear un modelo que permita optimizar/mitigar el evento
al repetir este modelo podemos decir que se convierte en un nuevo patron.

Los patrones de diseño se definen como una solucion a un problema recurrente. 

Los patrones de diseño se especifican mediante
- Nombre
- Contexto (Problema que resuelve/su objetivo)
- Solucion (Como se resuelve el problema)
- Esquema (Diagrama tipicamente clases)

> Los patrones se volvieron particularmente populares tras la publicacion del libro "Design Patterns: Elements of Reusable Object-Oriented Software" por Erich Gamma, Richard Helm, Ralph Johnson y John Vlissides en 1994. Mejor conocidos como "Gang of Four" (GoF). 
> Pese a que los patornes listados en el libro siguen vigentes y en constante uso es importante entender el contexto historico en el que fueron creados. Una epoca con pocos frameworks, iniciaba el desarrollo de aplicaciones de interfaces de usuario, los lenguajes de programacion orientados a objetos estaban en sus inicios. 

Pese a que nos se han publicado nuevos patrones, los patrones de diseño listados en el libro siguen vigentes y en constante uso, hemos seleccionado los siguientes patrones para nuestro taller:

| **#** | **Patrón / Principio**                    | **Descripción**                                                             | **Uso en pruebas de APIs**                                                                 | **Ejemplo**                                                                     |
| ----: | ----------------------------------------- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------- |
|     1 | **Single Responsibility Principle (SRP)** | Cada módulo/función debe tener una sola responsabilidad.                    | Separar lógica de autenticación, validación, requests, configuración, etc.                 | Ej: `auth.js` solo maneja autenticación, `validators.js` solo validaciones.     |
|     2 | **Factory Pattern**                       | Crea objetos o datos preconfigurados reutilizables.                         | Generar usuarios, tokens, órdenes, payloads comunes.                                       | Ej: `crearUsuario(tipo)` puede generar usuarios válidos, inválidos, admin, etc. |
|     3 | **Abstract Factory Pattern**              | Crea familias de objetos relacionados sin especificar sus clases concretas. | Crear distintas variantes de payloads o configuraciones según el tipo de prueba o entorno. | Ej:`UserFactory.admin().build()` vs `UserFactory.invitado().build()`            |
|     4 | **Builder Pattern**                       | Construcción progresiva de objetos complejos sin errores.                   | Crear requests personalizados paso a paso sin lógica repetida.                             | Ej: `new OrdenBuilder().conProducto(3).conCantidad(2).build()`                  |
|     5 | **Strategy Pattern**                      | Permite intercambiar comportamientos sin alterar la lógica principal.       | Cambiar método de login, validaciones o setup por tipo de prueba.                          | Ej: diferentes estrategias de login (OAuth, JWT, apikey) seleccionables.        |
|     6 | **Adapter Pattern**                       | Convierte una interfaz existente en otra esperada por el sistema.           | Unificar el acceso a diferentes herramientas HTTP (axios, supertest, etc.).                | Ej: `httpClient.get(...)` que por debajo usa axios o superagent según config    |
|     7 | **Decorator Pattern**                     | Añade funcionalidades a una función sin modificar su estructura.            | Agregar logging, retries, métricas o validaciones a funciones existentes.                  | Ej: `withRetry(crearOrden)` o `withLogger(getUser)`                             |
|     8 | **Command Pattern**                       | Encapsula acciones como objetos independientes.                             | Representar acciones como comandos reusables: `CrearUsuario`, `ActualizarOrden`, etc.      | `await new CrearUsuarioCommand(payload).ejecutar()`                             |
 | 9 | **Singleton Pattern**                       | Garantiza que una clase tenga una sola instancia y proporciona un punto de acceso global. | Manejo de configuración, logging, conexión a base de datos, etc. | `JsonMapper.getInstance()` |







