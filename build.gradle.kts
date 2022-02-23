buildscript {
	repositories {
        mavenCentral()
        google()
    }
    dependencies {
    	classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
   	}
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
		kotlinOptions {
			jvmTarget = "11"
		}
	}
}

subprojects {
	apply(plugin = "maven-publish")
	configure<PublishingExtension> {
		repositories {
			maven {
				name = "GitHubPackages"
				url = uri("https://maven.pkg.github.com/dk96-os/MathTools")
				credentials {
					username = System.getenv("GITHUB_ACTOR")
					password = System.getenv("GITHUB_TOKEN")
				}
			}
		}
	}
}