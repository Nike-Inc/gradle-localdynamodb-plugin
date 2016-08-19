package com.nike.retail.common.plugins.gradle.localdynamodb

import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.CopyNativeDependencyTask
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.DynamoRunTask
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.DynamoStartTask
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.DynamoStopTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LocalDynamodbPlugin implements Plugin<Project> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDynamodbPlugin)
    public static final String LOCAL_DYNAMO_EXTENSION_NAME = 'localdynamo'

    @Override
    void apply(Project project) {
        LOGGER.info("Adding extension")
        project.extensions.create(LOCAL_DYNAMO_EXTENSION_NAME, LocalDynamodbPluginExtension)
        project.extensions.getByType(LocalDynamodbPluginExtension).libraryPath = new File(project.buildDir, "libs").absolutePath

        project.apply plugin: "java"
        project.sourceSets {

            //create a source set just to track the JNI dependencies of sqllite so we can have access to them for DynamoDB Local
            sqllite4java {
            }
        }

        project.dependencies {
            sqllite4javaCompile group: 'com.almworks.sqlite4java', name: 'libsqlite4java-osx', version: '1.0.392'
            sqllite4javaCompile group: 'com.almworks.sqlite4java', name: 'libsqlite4java-linux-amd64', version: '1.0.392'
            sqllite4javaCompile group: 'com.almworks.sqlite4java', name: 'libsqlite4java-linux-i386', version: '1.0.392'
            sqllite4javaCompile group: 'com.almworks.sqlite4java', name: 'sqlite4java-win32-x64', version: '1.0.392'
            sqllite4javaCompile group: 'com.almworks.sqlite4java', name: 'sqlite4java-win32-x86', version: '1.0.392'
        }

        project.task("dynamoCopyNativeDependencies", group: "localdynamodb", type: CopyNativeDependencyTask, description: "Copies native libraries to a library location to be used by JNI")
        project.task("dynamoStart", group: "localdynamodb", type: DynamoStartTask, description: "Starts the Local Dynamo Server in the background")
            .dependsOn "dynamoCopyNativeDependencies"
        project.task("dynamoStop", group: "localdynamodb", type: DynamoStopTask, description: "Stops the Local Dynamo Server")
            .dependsOn "dynamoStart"

        project.task("dynamoRun", group: "localdynamodb", type: DynamoRunTask, description: "Runs a local dynamo server")
            .dependsOn("dynamoStart")
            .finalizedBy("dynamoStop")
    }

}

class LocalDynamodbPluginExtension {
    int dynamoPort = 7999
    String libraryPath

    DynamoDBProxyServer server
}
