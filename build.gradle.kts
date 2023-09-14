import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


group = "com.thawdezin"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
}

//This is use to clean build directory
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("androidx.compose.ui:ui:1.1.1")
                implementation("androidx.compose.ui:ui-tooling:1.1.1")
                implementation("androidx.compose.material:material:1.1.1")
                implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
                implementation("androidx.compose.ui:ui-tooling:1.1.1")

                implementation("io.ktor:ktor-client-core:2.3.4")
                implementation("io.ktor:ktor-client-json:2.3.4")
                implementation("io.ktor:ktor-client-serialization:2.3.4")
                //implementation("io.ktor:ktor-client-android:2.3.4")
                implementation("io.ktor:ktor-client-cio:2.3.4")
                //implementation("io.ktor:ktor-client-darwin:2.3.4")

                implementation("io.ktor:ktor-client-core:1.6.5")
                implementation("io.ktor:ktor-client-cio:1.6.5")

                implementation("io.github.microutils:kotlin-logging:2.0.6")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")

                implementation("io.ktor:ktor-client-core:2.3.4")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}


compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Dizys"
            packageVersion = "1.0.0"
        }


    }

}

