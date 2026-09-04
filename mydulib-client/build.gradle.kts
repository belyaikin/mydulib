group = "kz.edu.astanait"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":mydulib-core"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.1")
    implementation("com.squareup.okhttp3:okhttp:5.3.2")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}