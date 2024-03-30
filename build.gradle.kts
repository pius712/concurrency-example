import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")  apply false
    id("org.springframework.boot") apply false
    kotlin("plugin.jpa") apply false
    id("io.spring.dependency-management")
}


java.sourceCompatibility = JavaVersion.VERSION_17

val projectGroup: String by project
val applicationVersion: String by project


// root project 까지 관리
allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }

}

// settings.gradle 에 include 하는 하위 모듈에 모두 적용할 것들
subprojects  {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudDependenciesVersion")}")
        }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    // bootJar
    // application을 실행 가능한 JAR 파일로 패키징한다.
    // false 로 작업하는 경우, compile, test는 하지만,
    // executable Jar 를 만들지 않는다.
    tasks.getByName("bootJar") {
        enabled = false
    }

    // 기본 jar 패키징 작업을 활성화 함
    // 컴파일된 java 클래스, 추가 리소스는 포함되지만,
    // 종속성은 포함되지 않는 JAR 파일이 생성됨
    // java 플러그인 사용시 활성화 됨.
    tasks.getByName("jar") {
        enabled = true
    }

}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
