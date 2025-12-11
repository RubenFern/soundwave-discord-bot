# 🎵 SoundWave Music Bot

## DESCRIPTION

SoundWave is a modern, high-performance Discord music bot written in **Java** (using JDA and Lavaplayer). It joins your voice channel and plays high-quality audio from YouTube. It supports direct links, playlists, and text search.

This bot is containerized with **Docker**, making it extremely easy to install and run on any machine without worrying about Java versions or dependencies.

## 📋 PREREQUISITES

To run SoundWave, you only need one thing installed on your computer or server:

* **[Docker Desktop](https://www.docker.com/products/docker-desktop/)** (Windows/Mac) or **Docker Engine** (Linux).
* **Git** (Optional, to clone the repo).

*You do NOT need to install Java or Maven manually; Docker handles all of that.*

## 🚀 INSTALLATION & SETUP

Follow these steps to get your bot running in minutes:

### 1\. Clone the repository

Open your terminal and run:

```sh
git clone https://github.com/RubenFern/SoundWave-Bot-Discord.git
cd SoundWave-Bot-Discord
```

*(Or download the ZIP from the "Code" button if you don't have Git).*

### 2\. Configure the Environment

The bot needs your Discord secrets to work.

1.  Locate the file named `.env.example`.
2.  Rename it to `.env` (or copy it).
3.  Open `.env` with a text editor and fill in your data:

<!-- end list -->

```ini
# .env file configuration

# Your Bot Token (Get it from Discord Developer Portal)
DISCORD_TOKEN=your_token_here_example_xyz

# The ID of the server where you are testing the bot
# (Required to register Slash Commands instantly)
GUILD_ID=123456789012345678
```

> **How to get the GUILD\_ID?**
> Enable "Developer Mode" in Discord Settings \> Advanced. Then, right-click on your server name (on the left sidebar) and click **"Copy Server ID"**.

## ▶️ EXECUTE WITH DOCKER

Once configured, you can start the bot with a single command. It will automatically compile the Java code and start the application.

### Start the Bot

```sh
docker-compose up -d
```

* The `-d` flag runs the bot in the background (detached mode).
* *Note: The first time you run this, it may take a few minutes to download dependencies and compile.*

### Check Logs

To see what the bot is doing or check for errors:

```sh
docker-compose logs -f
```

### Stop the Bot

To stop the execution:

```sh
docker-compose down
```

-----

## 🛠️ COMMANDS

The bot uses Discord **Slash Commands (/)**. Type `/` in your chat to see the menu.

| Command | Description |
| :--- | :--- |
| **/play** `query` | Plays audio from YouTube. You can use a **URL** or a **search term**. |
| **/skip** | Skips the current track and plays the next one in the queue. |
| **/stop** | Stops playback, clears the queue, and disconnects the bot. |

*(Note: If you don't see the commands immediately, try reinviting the bot or waiting a few minutes for Discord to refresh).*

-----

## 🔧 DEVELOPMENT (Optional)

If you want to run the bot locally without Docker (for development purposes), you will need:

* **Java JDK 21** or higher.
* **Maven** installed.

**Run command:**

```sh
mvn clean package
java -jar target/SoundWave.jar
```

-----

## 📄 LICENSE

This project is licensed under the MIT License - see the LICENSE file for details.