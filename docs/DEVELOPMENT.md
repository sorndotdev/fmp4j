## Prerequisites

You need to have **Java 17** installed to build and run this project.

---

## 1. Install Java 17

* **Mac**: Use [Homebrew OpenJDK 21](https://formulae.brew.sh/formula/openjdk@21) to install Java 17.
* **Linux (Debian/Ubuntu)**: Install via the package manager (`openjdk-17-jdk`).
  See [Ubuntu OpenJDK package](https://packages.ubuntu.com/search?keywords=openjdk-21).
* **Windows**: Download and install Java 21 from either:
  * [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  * [Adoptium Temurin 17](https://adoptium.net/en-GB/temurin/releases/?version=17)

### Verify Java Installation

```bash
java -version
```

You should see something like:

```
java version "17"
```

---

## 2. Clone the Project

```bash
git clone https://github.com/marketdataapi/fmp4j.git
cd fmp4j
```

---

## 3. Build the Project

Make sure you're in the **root directory** of the project (where the `build.gradle` file is located).

### On Mac/Linux:

```bash
./gradlew clean build
```

### On Windows (Command Prompt or PowerShell):

```bash
gradlew.bat clean build
```

> 🔄 This will clean previous build artifacts and compile the entire project.

---

## ✅ Done

If the build is successful, you're ready to start development!

Please set up [IntelliJ Checkstyle Plugin](CHECKSTYLE.md) next.