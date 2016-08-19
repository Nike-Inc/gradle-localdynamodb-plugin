package com.nike.retail.common.plugins.gradle.localdynamodb.tasks

import com.nike.retail.common.plugins.gradle.localdynamodb.LocalDynamodbPluginExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DynamoStopTask extends DefaultTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoStartTask)

    @TaskAction
    def run() {
        LOGGER.info("Stopping Mock Dynamo")
        LocalDynamodbPluginExtension ext = project.extensions.getByType(LocalDynamodbPluginExtension)
        if (ext.server == null) {
            LOGGER.info("Server not found")
        } else {
            ext.server.stop();
        }
        LOGGER.info("Stopped Mock Dynamo")
    }
}
