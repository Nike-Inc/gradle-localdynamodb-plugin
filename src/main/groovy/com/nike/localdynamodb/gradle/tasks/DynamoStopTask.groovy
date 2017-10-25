/**
 * Copyright 2017-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 **/
package com.nike.localdynamodb.gradle.tasks

import com.nike.localdynamodb.gradle.LocalDynamodbPluginExtension
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
