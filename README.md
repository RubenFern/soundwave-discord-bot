# SoundWave Musica Bot

## DESCRIPTION

SoundWave is a YouTube audio playback bot in discord. SoundWave will join the voice channel you are in and play the content you have chosen, it allows you to play through a link to a Youtube video or playlist, or by a normal search.

## INSTALATION

In order to install SoundWave on your Discord server you will need to download/install the following:

* **NodeJS:** If you want to run the bot on your computer without the need to have a server to host the bot you must install NodeJS from its [official web site](https://nodejs.org/es).

* **Source code:** You must download the source code of the bot, to do so you can run 

```sh
git fork https://github.com/RubenFern/SoundWave-Bot-Discord.git
```

Or, if you don't have Git, download it directly from the ***Code*** - ***Download ZIP*** button. 

* **Discord application:** You must create your Discord application, here is a link to a page where it explains how [Tutorial](https://www.ionos.es/digitalguide/servidores/know-how/discord-bot/#:~:text=Create%20a%20bot%20of%20your%20own%20in%20Discord%3A&text=Click%20on%20%20E2%80%9CApplications%E2%80%9D%20in,bot%20and%20save%20the%20file).

## CONFIGURATION

For the bot to work you must:

1. Rename the file ```config.json.example``` to ```config.json```.

2. Fill in the fields it has inside:

* token: It is the token you generate when you create your application at (https://discord.com/developers/applications).
* clientId: It is the ID of the bot. You can access it from (https://discord.com/developers/applications) after creating the application.
* guildId: It is the ID of the server where the Bot is going to be installed.
* API_KEY: This is the Youtube Data API v3 API key. You can create one at (https://console.cloud.google.com).

## EXECUTE

To execute it, all you have to do is open a console terminal, go to the project directory and execute.

```sh
node index.js
```

To end the execution you can press ***Ctrl + C***.

## COMMANDS

The commands available to the Bot are:

* **/play**: You can play songs or videos (audio only) from Youtube. To do this you must execute. 

```sh
/play [link_video]/[link_playlist]/[search_by_text]
```

* **/stop**: You can stop the entire playback of the ones you are listening to.

* **/next**: You can skip to the next song (if it exists).

* **/pause**: You can pause the song you are listening to.

* **/continue**: You can continue playing what you were listening to.