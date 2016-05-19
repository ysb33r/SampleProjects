package book.allowuseroverride

import groovy.transform.PackageScope
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class AsciidoctorTask extends DefaultTask {


    @TaskAction
    void exec() {
        def urls = project.configurations.internal_asciidoctorj.files.collect { it.toURI().toURL() }
        def classLoader = new URLClassLoader(urls as URL[], Thread.currentThread().contextClassLoader)
        def asciidoctorInstance = classLoader.loadClass('org.asciidoctor.Asciidoctor$Factory') 

        testInstance = asciidoctorInstance
    }

    def testInstance
}
