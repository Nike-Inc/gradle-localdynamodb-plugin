# Local DynamoDB Gradle Plugin

## Add to the [Classpath](https://docs.gradle.org/current/userguide/organizing_build_logic.html) of Gradle Build Script
Add the library `gradle-localdynamodb-plugin'` to the classpath of gradle build script

```groovy
    dependencies {
        classpath 'com.nike.localdynamodb:gradle-localdynamodb-plugin:0.2-SNAPSHOT'
    }
```

Please configure correct repositories as well.

## Apply Plugin

    apply plugin: 'com.nike.localdynamodb'

## Provided Tasks

The plugin adds the following tasks by default. 

+ dynamoStart : Starts a dynamo server running in the background on the given port

+ dynamoRun : Runs dynamo in a blocking fashion

+ dynamoStop : Starts a dynamo server running in the background on the given port

+ dynamoCopyNativeDependencies : Copies all dylib, so, and dll files on the classpath. Specifically needed for Local DynamoDB with SQLLite.

## Configurations

The configuration block is called "cloudformation" and accepts the following configurations:

|Property Name   	| Description |Default Value  	|
|---	|---	| --- |
| dynamoPort   	| The port for the Local DynamoDB server| 7999   	|
| libraryPath  	| Where native libraries are copied | build/libs  	|

