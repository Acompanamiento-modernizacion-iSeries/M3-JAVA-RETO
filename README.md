# M3-JAVA-RETO

# Reto: Aplicación de Gestión de Cuenta Bancaria
>**Larry M. Ramírez - Coach Técnico**
## Objetivo
El objetivo de esta aplicación es crear un sistema que permita la gestión de cuentas bancarias, implementando los conceptos de Programación Orientada a Objetos y estructuras de datos en memoria.

## Funcionalidades Principales
1. **Cuentas Bancarias:**
	-   Debes crear una clase abstracta `Cuenta` con los siguientes atributos:
	    -   `saldo`: saldo global de la cuenta.
	    -   `numeroCuenta`: identificador único de la cuenta.
	-   A partir de esta clase crear dos subclases:
	    -   `CuentaBásica`: estándar sin beneficios adicionales.
	    -   `CuentaPremium`: permite algunas operaciones sin costo adicional.
2. **Operaciones sobre las cuentas:** Implementa las siguientes operaciones que afectarán el saldo de la cuenta. Usa polimorfismo para que algunas operaciones se comporten de manera diferente según el tipo de cuenta:
	-   **Depósito desde sucursal**: sin costo.
	-   **Depósito desde cajero automático**: tiene un costo de 2 USD (solo en `CuentaBásica`).
	-   **Depósito desde otra cuenta**: tiene un costo de 1.5 USD.
	-   **Compra en establecimiento físico**: sin costo.
	-   **Compra en página web**: costo adicional de 5 USD por seguro contra robos.
	-   **Retiro en cajero**: tiene un costo de 1 USD.
3. **Historial de transacciones:**
	-   Al realizar una operación se debe registrar la transacción en un historial (puedes usar una **Lista** o **ArrayList**).
	-   Cada transacción debe tener:
	    -   Tipo de transacción (depósito, retiro, compra, etc.).
	    -   Monto de la operación.
	    -   Fecha y hora de la transacción.
	    -   Un código único de la transacción.
4. **Consultas:**
	-   Permitir al usuario consultar el saldo actual de su cuenta.
	-   Consultar el **historial de las últimas 5 transacciones** realizadas.
## Requerimientos Técnicos
1.  **Estructuras de Datos:**
    -   Utiliza una **Lista** o **ArrayList** para almacenar las transacciones.
    -   No se usará una base de datos; todo debe almacenarse en memoria.
2.  **Polimorfismo y Herencia:**
    -   Las operaciones deben variar dependiendo del tipo de cuenta, usando polimorfismo para que el comportamiento de los métodos cambie entre `CuentaBásica` y `CuentaPremium`.
3.  **Clases y Métodos:**
    -   Organiza la aplicación usando clases y métodos para reflejar una arquitectura clara y modular.
    -   Implementa un menú interactivo en consola que permita al usuario elegir entre las opciones de depósito, compra, retiro, y consulta de saldo e historial. El programa sólo debe terminar cuando el usuario lo decida, por lo que se debe hacer una correcta validación de datos y posible errores. 
## Criterios de Evaluación
- Correcta implementación de la herencia y polimorfismo.
- Gestión adecuada de las transacciones y el historial en memoria.
- Implementación de las operaciones con los costos y reglas definidos.
- Funcionalidad del menú interactivo para realizar las operaciones y consultar el saldo.
- Estructura limpia del código y uso adecuado de las clases y métodos.
- Validación de datos y correcto manejo de errores. 

**Nota:** Al igual que los talleres, el reto debe entregarse por medio de la estrategia establecida para la formación, por medio de un Pull Request desde el repositorio Fork hacia la rama main del repositorio del reto.
