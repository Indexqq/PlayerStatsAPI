# PlayerStatsAPI - PaperMC Plugin

**PlayerStatsAPI** es un plugin para PaperMC que gestiona las estadísticas de los jugadores, almacenándolas de forma persistente. Permite visualizar estadísticas personalizadas a través de comandos y almacenar los datos en una base de datos (MySQL).

## Características
- Registro de estadísticas de jugadores.
- Persistencia de datos usando archivos YAML o MySQL.
- Comando para ver estadísticas de jugadores (`/playerstats`).
- Soporte para eventos de unión y desconexión de jugadores.

## Requisitos
- **PaperMC** (versión 1.20 o superior).
- **Java 17+**.

## Instalación
1. Descarga el archivo `.zip` desde la [página de releases de GitHub](https://github.com/Indexqq/PlayerStatsAPI/releases).
2. Coloca el archivo `.zip` en la carpeta `plugins` de tu servidor PaperMC.
3. Reinicia tu servidor.

## Configuración
- **config.yml**: Aquí puedes configurar los parámetros de la base de datos (si decides usar MySQL) y otras opciones.
  
### Ejemplo de `config.yml`:
```yaml
mysql:
  enabled: false
  host: "localhost"
  port: 3306
  database: "player_stats"
  username: "root"
  password: "password"
