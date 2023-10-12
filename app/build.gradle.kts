plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.kapt")
	id("org.jetbrains.kotlin.plugin.serialization")
	id("com.google.devtools.ksp")
	id("com.google.dagger.hilt.android")
	id("com.google.gms.google-services")
	id("com.google.firebase.crashlytics")
}

android {
	namespace = "com.amirbek.wallpapers"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.amirbek.wallpapers"
		minSdk = 24
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}

		ksp {
			arg("room.schemaLocation", "$projectDir/schemas")
		}

		val unsplashApiKey = project.properties["UNSPLASH_API_KEY"].toString()

		buildConfigField("String", "UNSPLASH_API_KEY", unsplashApiKey)
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName("debug")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.3"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	applicationVariants.all {
		addJavaSourceFoldersToModel(
			File(buildDir, "generated/ksp/$name/kotlin")
		)
	}
	androidComponents.onVariants { variant ->
		val name = variant.name
		sourceSets {
			getByName(name).kotlin.srcDir("${buildDir.absolutePath}/generated/ksp/${name}/kotlin")
		}
	}
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	val room_version = "2.5.2"

	implementation("androidx.activity:activity-ktx:1.8.0-beta01")
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
	implementation("androidx.activity:activity-compose:1.7.2")
	implementation(platform("androidx.compose:compose-bom:2023.03.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// Compose
	implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

	// Navigation
	implementation("androidx.navigation:navigation-compose:2.7.3")

	// Serialization
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

	// Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

	// Moshi
	implementation("com.squareup.moshi:moshi:1.14.0")
	ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

	// Paging
	implementation("androidx.paging:paging-runtime-ktx:3.2.1")
	implementation("androidx.paging:paging-compose:3.2.1")

	// Dagger - Hilt
	implementation("com.google.dagger:hilt-android:2.48")
	kapt("com.google.dagger:hilt-android-compiler:2.48")
	kapt("androidx.hilt:hilt-compiler:1.0.0")
	implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

	// Room
	implementation("androidx.room:room-ktx:$room_version")
	ksp("androidx.room:room-compiler:$room_version")
	implementation("androidx.room:room-paging:$room_version")

	// Coil
	implementation("io.coil-kt:coil-base:2.4.0")
	implementation("io.coil-kt:coil-compose:2.4.0")

	// Chucker
	debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
	releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

	// Compose Destinations
	implementation("io.github.raamcosta.compose-destinations:core:1.9.53")
	ksp("io.github.raamcosta.compose-destinations:ksp:1.9.53")

	// LeakCanary
	debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")

	// DataStore
	implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Testing
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("com.google.truth:truth:1.1.4")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

	// Firebase
	implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
	implementation("com.google.firebase:firebase-analytics-ktx")
	implementation("com.google.firebase:firebase-crashlytics-ktx")
	implementation("com.google.firebase:firebase-messaging-ktx")

}