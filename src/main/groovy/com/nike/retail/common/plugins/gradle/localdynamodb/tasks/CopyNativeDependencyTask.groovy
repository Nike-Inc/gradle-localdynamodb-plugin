package com.nike.retail.common.plugins.gradle.localdynamodb.tasks

import com.nike.retail.common.plugins.gradle.localdynamodb.LocalDynamodbPluginExtension
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
