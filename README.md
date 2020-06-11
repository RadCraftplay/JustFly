# JustFly
Simple "fly mode" plugin for Bukkit

# Requirements

- Bukkit **1.8-R0.1-SNAPSHOT** or newer (works with Spigot too)

# Note

In order for this plugin to work, flying has to be enabled on your server (look for that in your *server.properties* file).

# Commands

| Command                      | Description                                | Example   |
| ---------------------------- | ------------------------------------------ | --------- |
| ```/fly```                   | Turns on/off player's ability to fly       | /fly      |
| ```/fly [player nickname]``` | Turns on/off other players' ability to fly | /fly John |

# Permissions

| Permission        | Description                                      |
| ----------------- | ------------------------------------------------ |
| justfly.*         | Gives access to all JustFly commands             |
| justfly.fly       | Lets player enable/disable fly for themselves    |
| justfly.flyothers | Lets player enable/disable fly for other players |

# Error messages

- **Flying is disabled on this server!**: Flying is not enabled. Make sure, in your *server.properties* file *allow-flight* is set to *true*
- **Unable to find player with nickname 'nickname'**: You probably misspelled player's nick. Make sure you typed player's name correctly
- **This command can be run only by players!**: You can't just type ``` /fly ``` in your server console, because you are not a player. Use ```/fly [player nickname]``` to enable or disable fly for other players
