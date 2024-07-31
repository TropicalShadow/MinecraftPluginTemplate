import com.github.spotbugs.snom.SpotBugsTask
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

plugins {
    id("checkstyle")
    id("com.github.spotbugs") version "6.0.19"
    id("io.github.goooler.shadow") version "8.1.8"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("java")
}

group = "club.tesseract"
val packagelocation = "${group}.${project.name.lowercase()}"

fun getTime(): String {
    val sdf = SimpleDateFormat("yyMMdd-HHmm")
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(Date()).toString()
}

val ver = findProperty("ver")?.toString()
var shortVersion: String? = null
if (!ver.isNullOrEmpty()) {
    shortVersion = if (ver.startsWith("v")) {
        ver.substring(1).uppercase()
    } else {
        ver.uppercase()
    }
}

version = if (shortVersion.isNullOrEmpty()) {
    "${getTime()}-SNAPSHOT"
} else {
    val rcIdx = shortVersion!!.indexOf("-RC-")
    if (rcIdx != -1) {
        "${shortVersion!!.substring(0, rcIdx)}-SNAPSHOT"
    } else {
        shortVersion!!
    }
}


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.6")
    implementation("io.papermc:paperlib:1.0.8")
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.13.0")
}

checkstyle {
    toolVersion = "10.14.0"
    maxWarnings = 0
}

configurations.checkstyle {
    resolutionStrategy.capabilitiesResolution.withCapability("com.google.collections:google-collections") {
        select("com.google.guava:guava:23.0")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {

    jar{
        enabled = false
    }

    assemble {
        dependsOn("shadowJar")
    }

    runServer{
        minecraftVersion("1.21")
    }

    shadowJar {
        archiveClassifier.set("")
        relocate("io.papermc.lib", "${packagelocation}.lib.paperlib")
        minimize()
    }

    register("release") {
        dependsOn("shadowJar")
        doFirst {

            if(!version.toString().endsWith("-SNAPSHOT", true)) {
                shadowJar.get().archiveVersion.set("")
             }
        }
    }

    compileJava {
        options.encoding = "UTF-8"
        options.release = 21
    }

    withType<Checkstyle>().configureEach {
        reports {
            xml.required = false
            html.required = true
        }
    }

    withType<SpotBugsTask>().configureEach {
        reports.create("html") {
            required = true
        }
        reports.create("xml") {
            required = false
        }
    }

    processResources {
        filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to
                mapOf("version" to project.version, "name" to project.name, "package" to packagelocation))
    }

    register("printProjectName") {
        doLast {
            println(project.name)
        }
    }

    register("printVersion") {
        doLast {
            println(project.version)
        }
    }
}