package com.nike.retail.common.plugins.gradle.localdynamodb

import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.CopyNativeDependencyTask
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.DynamoStartTask
import com.nike.retail.common.plugins.gradle.localdynamodb.tasks.DynamoStopTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import static org.junit.Assert.*

class LocalDynamodbPluginTests {

    @Test
    public void localDynamoPluginAddsDynamoStartToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.retail.common.plugins.gradle.localdynamodb'

        assertTrue(project.tasks.dynamoStart instanceof DynamoStartTask)
        assertEquals(project.tasks.dynamoStart.group, "localdynamodb")
    }

    @Test
    public void localDynamoPluginAddsDynamoStopToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.retail.common.plugins.gradle.localdynamodb'

        assertTrue(project.tasks.dynamoStop instanceof DynamoStopTask)
        assertEquals(project.tasks.dynamoStop.group, "localdynamodb")
    }

    @Test
    public void localDynamoPluginAddsCopyNativeDependencyToProject() {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'com.nike.retail.common.plugins.gradle.localdynamodb'

        assertTrue(project.tasks.dynamoCopyNativeDependencies instanceof CopyNativeDependencyTask)
        assertEquals(project.tasks.dynamoCopyNativeDependencies.group, "localdynamodb")
    }
}
