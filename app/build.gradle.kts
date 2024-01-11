plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.iotsystemparking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.iotsystemparking"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("net.objecthunter:exp4j:0.4.8")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("org.chromium.net:cronet-embedded:113.5672.61")
    implementation("com.google.firebase:firebase-database:20.3.0")
    testImplementation("junit:junit:4.13.2")
    implementation ("androidx.fragment:fragment-ktx:1.3.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.diogobernardino:williamchart:3.10.1")

    implementation ("com.squareup.okhttp3:okhttp:3.12.1")
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation ("com.google.code.gson:gson:2.8.5")
    implementation ("com.squareup.picasso:picasso:2.8")

}