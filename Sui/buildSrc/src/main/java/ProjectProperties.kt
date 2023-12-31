import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    const val APPLICATION_ID = "com.bestswlkh0310.sui"
    const val APPLICATION_COMPONENTS = "com.bestswlkh0310.components"
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    const val GROUP_ID = "com.bestswlkh0310.components"
    const val PUBLISHING_NAME = "maven"
    const val COMPONENT_ARTIFACT_ID = "sgx-components"
    const val COMPONENT_VERSION = "1.0.0"

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val PROGUARD_NAME = "proguard-android.txt"
    const val PROGUARD_FILE = "proguard-rules.pro"
    const val IS_MINIFY_ENABLE = false
    const val APPLICATION_BUILD_TYPE = "release"
    const val USE_SUPPORT_LIBRARY = true
    const val EXCLUDES = "/META-INF/{AL2.0,LGPL2.1}"

    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 33

    const val JVM_TARGET = "1.8"

    const val KT_LINT = "10.2.0"
    const val COMPILE_SDK_VERSION = 33

    const val ANDROID_VERSION = "7.4.1"
    const val JETBRAINS_KOTLIN = "1.7.10"

    const val BUILD_FEATURE_COMPOSE = true

    const val PROJECT_PATH = ":sgx-components"
    const val PATH = "path"
}
