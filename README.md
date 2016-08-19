# Local DynamoDB Gradle Plugin

## Add to the [Classpath](https://docs.gradle.org/current/userguide/organizing_build_logic.html) of Gradle Build Script
Add the library `'local-dynamodb-gradle-plugin'` to the classpath of gradle build script

    dependencies{
        classpath 'com.nike.retail:local-dynamodb-gradle-plugin:0.1'
    }
    
Please configure correct repositories as well.
    
## Apply Plugin
    
    apply plugin: 'com.nike.retail.common.plugins.gradle.localdynamodb'
    
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

