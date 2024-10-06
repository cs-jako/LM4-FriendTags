plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "net.crazy"
version = "2.5.0"

labyMod {
    defaultPackageName = "net.crazy.friendtags"

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    // When the property is set to true, you can log in with a Minecraft account
                    // devLogin = true
                }
            }
        }
    }

    addonInfo {
        namespace = "friendtags"
        displayName = "FriendTags"
        author = "CrazySchnetzler"
        description = "With this addon you see a tag above the head of LabyChat Friends"
        minecraftVersion = "*"
        iconUrl = "https://dl.labymod.net/latest/addons/72115a66-1f9b-4cf0-b346-918075847a7d/icon.png"
        version = "2.5.0"
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}