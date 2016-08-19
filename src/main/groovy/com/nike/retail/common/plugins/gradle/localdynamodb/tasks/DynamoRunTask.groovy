package com.nike.retail.common.plugins.gradle.localdynamodb.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DynamoRunTask extends DefaultTask {

    @TaskAction
    public void runIt() {
        while (!Thread.interrupted()) {
            Thread.sleep(1000)
        }
    }
}
