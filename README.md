# MyPuppy
Aplicación para curso Desarrollo de aplicaciones con Android

Se eligió como PrimaryColor CYAN #00BCD4, como AccentColor DEPP ORANGE #FF5722, de manera que DarkPrimaryColor quedó como #0097A7.

Para la semana 4 (JavaMail) el correo y password del remitente se almacenan en strings.xml en sender_mail y email_password,
reemplazar los valores por su email y password.

Debido a que hace uso de smtp es necesario que su correo (gmail) esté configurado para permitir acceso a "aplicaciones menos seguras".

# Semana 5

Para la tarea de la semana 5 (Persistencia), usé el siguiente modelo de datos:

![Alt text](/screenshots/PETAGRAM_ER.png?raw=true "ER")

No me pareció tan clara la petición del modelo de datos (una sola tabla y guardar sólo los ultimos 5), así que tomé como base la idea del ejemplo del curso, donde los likes se guardan en una tabla adicional.

la tabla pet almacena a todas las mascotas, la inserción de las mascotas se hace una sola vez al incio de la aplicación.

Cuando se están guardando los likes, se obtienen los ultimos 5 a los que se les ha dado like, del más reciente al más antiguo.

