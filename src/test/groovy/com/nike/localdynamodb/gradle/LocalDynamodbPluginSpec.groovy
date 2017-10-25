package com.nike.localdynamodb.gradle

import com.nike.localdynamodb.gradle.tasks.CopyNativeDependencyTask
import com.nike.localdynamodb.gradle.tasks.DynamoStartTask
import com.nike.localdynamodb.gradle.tasks.DynamoStopTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import static org.junit.Assert.*

class LocalDynamodbPluginSpec {

    @Test
    void localDynamoPluginAddsDynamoStartToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.localdynamodb'

        assertTrue(project.tasks.dynamoStart instanceof DynamoStartTask)
        assertEquals(project.tasks.dynamoStart.group, "localdynamodb")
    }

    @Test
    void localDynamoPluginAddsDynamoStopToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.localdynamodb'

        assertTrue(project.tasks.dynamoStop instanceof DynamoStopTask)
        assertEquals(project.tasks.dynamoStop.group, "localdynamodb")
    }

    @Test
    void localDynamoPluginAddsCopyNativeDependencyToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.localdynamodb'

        assertTrue(project.tasks.dynamoCopyNativeDependencies instanceof CopyNativeDependencyTask)
        assertEquals(project.tasks.dynamoCopyNativeDependencies.group, "localdynamodb")
    }
}
