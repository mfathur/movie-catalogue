function copyEnvVarsToGradleProperties {
    GRADLE_PROPERTIES=$HOME"/.gradle/gradle.properties"
    export GRADLE_PROPERTIES
    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"

    if [ ! -f "$GRADLE_PROPERTIES" ]; then
        echo "Gradle Properties does not exist"

        echo "Creating Gradle Properties file..."
        touch $GRADLE_PROPERTIES

        echo "Writing LOCAL_DB_ENCRYPT_KEY to gradle.properties..."
        echo "LOCAL_DB_ENCRYPT_KEY=$LOCAL_DB_KEY_ENV_VAR" >> $GRADLE_PROPERTIES

        echo "Writing MOVIE_DB_API_KEY to gradle.properties..."
        echo "MOVIE_DB_API_KEY=$MOVIE_DB_API_KEY_ENV_VAR" >> $GRADLE_PROPERTIES
    fi
}