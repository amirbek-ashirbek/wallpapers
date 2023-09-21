plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.kapt")
	id("org.jetbrains.kotlin.plugin.serialization")
	id("com.google.devtools.ksp")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.example.wallpapers"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.example.wallpapers"
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
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
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
}

dependencies {
	val room_version = "2.5.2"

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

	// Navigation
	implementation("androidx.navigation:navigation-compose:2.7.2")

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
	implementation("io.coil-kt:coil-compose:2.4.0")

	// Chucker
	debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
	releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")
}