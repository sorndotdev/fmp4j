````md
# Versioning

## Setup

In `~/.gradle/gradle.properties`, set:

```
sonatypeUsername=
sonatypePassword=
sonatypeUsername=
sonatypePassword=
signing.keyId=
signing.password=
signing.secretKeyRingFile=
```

## Publish

This project follows [Semantic Versioning](https://semver.org/).

**1. Update versions in configuration and documentation**

Update the version number in the following files:

- `gradle.properties`
- `README.md`

**2. Run Gradle commands**

```sh
./gradlew clean build
./gradlew publish --no-daemon
````

**3. Verify publication**

Publish the deployment from:

[https://central.sonatype.com/](https://central.sonatype.com/) (GitHub account)
