package book

import book.allowuseroverride.AsciidoctorTask
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification


class AllowUserOverrideOfDependentLibrary2Spec extends Specification {

    def project = ProjectBuilder.builder().build()

    def "Usage example"() {
        given:
        project.with {
            apply plugin: AllowUserOverrideOfDependentLibraryPlugin2
        }

        def task = project.tasks.create('asciidoctor',AsciidoctorTask)

        project.allprojects {

            repositories {
                jcenter()
            }

            asciidoctorj {
                version = '1.5.2'
            }
        }
        project.evaluate()

        task.execute()

        expect:
        task.testInstance != null
        project.configurations.internal_asciidoctorj.dependencies.find { it.name == 'asciidoctorj' && it.version == '1.5.2' }

    }
}