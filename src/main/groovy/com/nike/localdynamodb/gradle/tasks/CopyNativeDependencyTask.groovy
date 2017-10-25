/**
 * Copyright 2017-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 **/
package com.nike.localdynamodb.gradle.tasks

import com.nike.localdynamodb.gradle.LocalDynamodbPluginExtension
import org.gradle.api.tasks.Copy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Copies the required native libraries into the library path
 */
class CopyNativeDependencyTask extends Copy {

    private static final Logger LOGGER = LoggerFactory.getLogger(CopyNativeDependencyTask)

    public CopyNativeDependencyTask() {
        LocalDynamodbPluginExtension ext = project.extensions.getByType(LocalDynamodbPluginExtension)

        from(project.configurations.sqllite4javaCompile) {
            include "*.dylib", "*.so", "*.dll"
        }
        into ext.libraryPath
        LOGGER.info("copying into " + ext.libraryPath)
    }

}
