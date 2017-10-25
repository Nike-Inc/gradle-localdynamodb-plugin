/**
 * Copyright 2017-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 **/
package com.nike.localdynamodb.gradle

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

import static org.gradle.testkit.runner.TaskOutcome.*

class FunctionalPluginSpec extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    static def gradleVersions = ['2.9', '3.0', '3.1', '3.2', '3.3', '3.4', '3.5', '4.0', '4.1', '4.2']

    private def writeFile(String path, String text) {
        File file = new File(testProjectDir.root, path)
        file.parentFile.mkdirs()
        file.text = text
    }

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        def url = getClass().getResource("/testkit-gradle.properties")
        writeFile('gradle.properties', url.text)
    }

    @Unroll
    def "sane execution with Gradle #gradleVersion"() {
        given:
        buildFile << """
            plugins {
                id 'com.nike.localdynamodb'
            }
            
            repositories {
                jcenter()
            }
            
            task listTables() {
                dependsOn dynamoStart
                finalizedBy dynamoStop
                doLast {
                    def credentials = new com.amazonaws.internal.StaticCredentialsProvider(new com.amazonaws.auth.BasicAWSCredentials("", ""))
                    def client = new com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient(credentials)
                    client.setEndpoint("http://localhost:" + localdynamo.dynamoPort)
                    def result = client.listTables()
                    println("found " + result.getTableNames().size() + " tables")
                }
            }
            
        """

        when:
        def result = GradleRunner.create()
                .withGradleVersion(gradleVersion)
                .withProjectDir(testProjectDir.root)
                .withArguments('listTables', '--stacktrace')
                .withPluginClasspath()
                .build()


        then:
        result.task(":dynamoStop").outcome == SUCCESS
        result.task(":dynamoStart").outcome == SUCCESS
        result.task(":listTables").outcome == SUCCESS
        result.output.contains("found 0 tables")

        where:
        gradleVersion << gradleVersions
    }

}
