plugins {
    id("java-platform")
    id("maven-publish")
}

val activityCompose = "1.5.0"
val compose = Versions.COMPOSE
val composeThemeAdapter = "1.1.14"
val coreKtx = "1.8.0"
val lifecycle = "2.5.0"

dependencies {
    constraints {
        api("${Libs.ACTIVITY_COMPOSE}:$activityCompose")
        api("${Libs.COMPOSE_RUNTIME}:$compose")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_FOUNDATION}:$compose")
        api("${Libs.COMPOSE_FOUNDATION_LAYOUT}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_RUNTIME_LIVEDATA}:$compose")
        api("${Libs.COMPOSE_UI_TEST_MANIFEST}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING_PREVIEW}:$compose")
        api("${Libs.COMPOSE_THEME_ADAPTER}:$composeThemeAdapter")
        api("${Libs.CORE_KTX}:$coreKtx")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}