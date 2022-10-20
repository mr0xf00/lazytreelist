plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

val compose_compiler_version: String by rootProject.extra
val compose_ui_version: String by rootProject.extra

android {
    namespace = "com.mr0xf00.lazytreelist"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_compiler_version
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.material:material:$compose_ui_version")
}

fun extProperty(key: String): String {
    return (extra.properties[key] ?: error("property $key not found in extras")) as String
}

publishing {
    val data = object {
        val groupId = "io.github.mr0xf00"
        val artifactId = "lazytreelist"
        val version = "0.1.0"
        private val releasesRepoUrl =
            uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        private val snapshotsRepoUrl =
            uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

        val repoUrl = if (version.endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
    }

    publications {
        register<MavenPublication>("release") {
            groupId = data.groupId
            artifactId = data.artifactId
            version = data.version
            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set("LazyTreeList")
                description.set("Lazy Tree List for jetpack compose")
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }
                url.set("https://github.com/mr0xf00/lazytreelist")
                scm {
                    connection.set("https://github.com/mr0xf00/lazytreelist.git")
                    url.set("https://github.com/mr0xf00/lazytreelist")
                }
                developers {
                    developer {
                        name.set("mr0xf00")
                        email.set("mr0xf00@proton.me")
                    }
                }
            }
        }
    }
    publications {
        repositories {
            maven {
                name = "oss"
                url = data.repoUrl
                credentials {
                    username = extProperty("ossrh.user")
                    password = extProperty("ossrh.pass")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}