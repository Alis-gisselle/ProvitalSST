# PROVITAL SST Android

Módulo Android nativo en Java que implementa la pantalla equivalente a
`src/main/webapp/personaDetalle.jsp`.

La aplicación inicia con una pantalla de acceso que valida el formato del correo
y una contraseña de al menos seis caracteres. Mientras no exista una API REST
en el sistema web, el acceso es local y abre datos de demostración.

## Abrirlo

1. Abrir la carpeta `AndroidPersonaDetalle` desde Android Studio.
2. Esperar a que Gradle sincronice las dependencias.
3. Ejecutar la configuración `app` en un emulador o teléfono con Android 7.0 (API 24) o posterior.

La actividad de inicio usa una persona de muestra. Desde un listado real, se abre así:

```java
Persona persona = new Persona(12, "Ana", "Pérez", "4.123.456",
        "empleado", "Empresa S.A.", "admisional");
Intent intent = new Intent(this, PersonaDetalleActivity.class);
intent.putExtra(PersonaDetalleActivity.EXTRA_PERSONA, persona);
startActivity(intent);
```

La pantalla conserva las reglas de la JSP: solo muestra **Ficha médica** para personas de categoría `admisional`; Estudios y Certificado siempre están disponibles. Los botones muestran temporalmente el módulo y el ID de persona, listos para reemplazarse por las actividades o llamadas HTTP de cada módulo.
