# Minecraft Plugin Template for PaperMC / Spigot
> Useful for quicker and more efficient plugin development.

[![Build, Test, and Release](https://github.com/TropicalShadow/MinecraftPluginTemplate/actions/workflows/main.yml/badge.svg)](https://github.com/TropicalShadow/MinecraftPluginTemplate/actions/workflows/main.yml)

## Features
### GitHub Actions
* Automatic builds, tests, and releases
* Discord notifications for builds, tests, and releases (optional) - More Info Here: [Discord Message Notify](#discord-notifications)

### GitHub Bots
* **Probot: Stale**
    * Sadly Deprecated, but still accessible via [Probot: Stale](https://github.com/apps/stale/installations/new/permissions)
* **Dependabot**
    * Update GitHub actions workflow
    * Update Gradle dependencies

### Gradle PreDependencies
* [PaperLib](https://github.com/PaperMC/PaperLib) for Paper / Spigot api interfacing
* [Paper](https://papermc.io/) for main plugin api
* [SpotBugs](https://spotbugs.github.io/) for static analysis
* [CheckStyle](https://checkstyle.org/) to enforce code style

### CheckList
- [ ] updated project name in `settings.gradle.kts`
- [ ] updated project group in `build.gradle.kts` (not required)
- [ ] updated `plugin.yml` with correct information
- [ ] updated `README.md` with correct information

#### Release and Versioning Strategy
Stable versions of this repo are tagged `vX.Y.Z` and have an associated [release](https://github.com/TropicalShadow/MinecraftPluginTemplate/releases).

Testing versions of this repo are tagged `vX.Y.Z-RC-N` and have an associated [pre-release](https://github.com/TropicalShadow/MinecraftPluginTemplate/releases).

Development versions of this repo are pushed to the main branch and are **not** tagged.

| Event             | Plugin Version Format | CI Action                        | GitHub Release Draft? |
|-------------------|-----------------------|----------------------------------|-----------------------|
| PR                | yyMMdd-HHmm-SNAPSHOT  | Build and test                   | No                    |
| Cron              | yyMMdd-HHmm-SNAPSHOT  | Build, test, and notify          | No                    |
| Push to `main`    | 0.0.0-SNAPSHOT        | Build, test, release, and notify | No                    |
| Tag `vX.Y.Z-RC-N` | X.Y.Z-SNAPSHOT        | Build, test, release, and notify | Pre-release           |
| Tag `vX.Y.Z`      | X.Y.Z                 | Build, test, release, and notify | Release               |

### Discord Notifications
In order to use Discord notifications, you will need to create two GitHub secrets. `DISCORD_WEBHOOK_ID`
should be set to the id of your Discord webhook. `DISCORD_WEBHOOK_TOKEN` will be the token for the webhook.

You can find these values by copying the Discord Webhook URL:  
`https://discord.com/api/webhooks/<DISCORD_WEBHOOK_ID>/<DISCORD_WEBHOOK_TOKEN>`

For more information, see [Discord Message Notify](https://github.com/marketplace/actions/discord-message-notify).

## Building locally
Run the following command to build the project locally:

```sh
./gradlew build
```

This will check with CodeStyle, SpotBugs, and build the project.

compiled JARs can be found in `build/libs/`.

---

Something to finish the README.md