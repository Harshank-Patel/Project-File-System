# File System Command-Line Tool

This Java program simulates a hierarchical file system that allows users to:

- **CREATE `<directory_path>`** → Create a new directory.
- **LIST** → Display the current directory structure.
- **MOVE `<source> <destination>`** → Move a directory.
- **DELETE `<directory_path>`** → Delete a directory.
- **EXIT / QUIT / Q** → Quit the program.

&gt; **Note:** Directory names are **always case sensitive**.

---

## Prerequisites

Before running the program or its tests, ensure you have the following:

### 1. Java Development Kit (JDK)
- **Check if Java is installed:**
```sh
java -version
```
- **If Java is not installed, download the latest JDK:**  
  [Download Java (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Verify the installation:**
```sh
javac -version
```

### 2. JUnit 5 (Standalone JAR) – *For Unit Testing*
If you plan to run unit tests, you must have the JUnit 5 library. Missing this library will cause the tests to fail during compilation.

1. **Download the JUnit 5 Console Standalone JAR:**  
   Visit the following link and download the file:  
   [JUnit 5 Standalone JAR (version 1.8.2)](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar)
2. **Place the downloaded JAR in the same directory as your Java files.**

---

## How to Compile and Run the Program

### 1. Compile the Program
Open a terminal in the directory containing `FileSystem.java` and run:
```sh
javac FileSystem.java
```

### 2. Run the Program
```sh
java FileSystem
```

#### Example Session:
** Available Commands: **  
 * CREATE <directory_path>  -> Create a new directory  
 * MOVE <source> <destination>  -> Move a directory  
 * DELETE <directory_path>  -> Delete a directory  
 * LIST  -> Show the directory structure  
 * EXIT / QUIT / Q  -> Quit the program  

 ** WARN: Directory names are ALWAYS case sensitive **  

Enter a command: create fruits  
Enter a command: create fruits/apples  
Enter a command: list  
  fruits  
    apples  
Enter a command: move fruits/apples vegetables  
 **** Cannot move fruits/apples - destination vegetables does not exist  
Enter a command: create vegetables  
Enter a command: move fruits/apples vegetables  
Enter a command: list  
  fruits  
  vegetables  
    apples  
Enter a command: delete fruits/apples  
 **** Cannot delete fruits/apples - fruits does not exist  
Enter a command: delete vegetables/apples  
Enter a command: list  
  fruits  
  vegetables  
Enter a command: exit  
Exiting...

---

## Running Unit Tests (Without Maven)

### **Important:**  
To run unit tests, you **must** download the JUnit 5 standalone JAR (version 1.8.2) from the web and place it in the same directory as your Java files (`FileSystem.java` and `FileSystemTest.java`).

### 1. Compile the Test Files

- **On macOS/Linux:**
```sh
javac -cp .:junit-platform-console-standalone-1.8.2.jar FileSystem.java FileSystemTest.java
```
- **On Windows:** (Replace `:` with `;` in the classpath)
```sh
javac -cp .;junit-platform-console-standalone-1.8.2.jar FileSystem.java FileSystemTest.java
```

### 2. Run the Tests

Execute the following command to run the tests:
```sh
java -jar junit-platform-console-standalone-1.8.2.jar --class-path . --scan-classpath
```

If everything is set up correctly, you should see a summary indicating that all tests have passed.

---

## Code Overview

The `FileSystem` class uses a nested `Directory` class to represent each directory. Key features include:

- **`create(String path)`**: Creates directories and nested directories.
- **`list()`**: Recursively prints the directory structure.
- **`move(String sourcePath, String destinationPath)`**: Moves a directory from one location to another.
- **`delete(String path)`**: Deletes a directory.
- **Interactive Command Loop (`main` method)**: Displays available commands and processes user input.

---

## Author

Developed by Harshank Patel.
Harshank.TAMU@gmail.com