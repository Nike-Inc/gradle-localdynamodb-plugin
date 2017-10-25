/**
 * Copyright 2017-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 **/
package com.nike.localdynamodb.gradle.tasks

import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBRequestHandler
import com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBServerHandler
import com.nike.localdynamodb.gradle.LocalDynamodbPluginExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Starts a DynamoDB server
 */
class DynamoStartTask extends DefaultTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoStartTask)

    @TaskAction
    def runIt() {
        LocalDynamodbPluginExtension ext = project.extensions.getByType(LocalDynamodbPluginExtension)
        System.setProperty("sqlite4java.library.path", ext.libraryPath)

        ext.server = new DynamoDBProxyServer(ext.dynamoPort, new LocalDynamoDBServerHandler(new LocalDynamoDBRequestHandler(0, true, null, true, false), null));
        LOGGER.info("Initializing DynamoDB server on port ${ext.dynamoPort}");
        ext.server.start();
        LOGGER.info("Initialized DynamoDB server on port ${ext.dynamoPort}");
    }
}
