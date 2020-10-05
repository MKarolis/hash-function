Custom Hash function implementation in Java
===========================================
<h6>
Created by Karolis Medekša for BlockChain fundamentals course<br/>
Vilnius University, Faculty of Mathematics and Informatics, 2020 Fall
</h6>

About
-----

This application providesa  custom hash function implementation in Java, 
Gradle is used as a build and dependency management tool.

You can run the application from a preferred IDE (Intellij IDEA, Eclipse, etc.) 
or straight from command line using gradle build tool.

**JDK version 11 or higher is required to build and run the application**

#### Gradle tasks
Only requirement to build the application is a sufficient Java Development Kit version.

Application can be built and run from the command line using Gradle tasks:
- `./gradlew build` compiles main and test files, runs unit tests, creates an executable .jar file
- `./gradlew jar` build an executable .jar file in `/build/libs` directory
- `./gradlew createExe` build an executable .exe file in `/build/launch4j` directory 
- `./gradlew test` run unit test suites
- `./gradlew clean` clean build data

#### Running the application
1. Build with Gradle: `./gradlew build`
2. Navigate to build folder: `cd build/libs`
3. Launch `./java -jar HashFunction-[version].jar [Options] [Value]`

OR

1. Build with Gradle launch4j plugin: `./gradlew createExe`
2. Navigate to build folder: `cd build/launch4j`
3. Launch `./HashFunction.exe [Options] [Value]`

#### Command line arguments
App usage from .exe file (same applies to .jar): `./HashFunction.exe [Options] [Value]`
```
Options:
    --help 
        Shows app usage instructions
    -m, -mode
        Sets app operation mode
        Default: ARG
        Possible values:
            ARG - Hash String passed as [Value]
            I - Interactive mode
            BENCH - Benchmarking mode
            VS - Benchmarking against a specified algorithm mode. 
                 Name of the algorithm is accepted as [Value]
            FILE - Hash contents of a file, filename is accepted as [Value]

Value - Value that is used as an input by supported operation modes
```
