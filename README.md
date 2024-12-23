# RestoreCraft

RestoreCraft is a Bukkit plugin for Minecraft that allows players to heal themselves or other players using the `/heal` command. The plugin provides configurable options to customize the healing behavior, such as removing potion effects and displaying who performed the healing.

## Features

- Heal yourself or other players with the `/heal` command.
- Configurable options:
  - Remove all active potion effects upon healing.
    - Show a message indicating who healed the player.
    - Permission-based access to the healing command.

## Installation

1. Download the latest version of RestoreCraft.
2. Place the `RestoreCraft.jar` file into the `plugins` folder of your Bukkit/Spigot server.
3. Start or restart your server.
4. Configure the plugin by editing the `config.yml` file generated in the `plugins/RestoreCraft` directory.

## Configuration

The plugin comes with a default configuration file that can be modified to suit your needs. The following options are available:

```yaml
remove-effects: true  # Set to true to remove all potion effects when healing
show-who-healed: false # Set to true to show who healed the player
```

## Commands

### `/heal [player]`

- **Description**: Heals the player who executes the command or the specified player.
- **Permissions**: `heal.admin`
- **Usage**:
  - To heal yourself: `/heal`
  - To heal another player: `/heal <playerName>`

## Development

This plugin is developed using Java and the Bukkit API. Contributions and improvements are welcome! Please feel free to submit issues or pull requests.

## License

This project is licensed under the GPLv3 License. See the [LICENSE](LICENSE) file for more details.

## Support

For support or questions, please open an issue on the GitHub repository.
