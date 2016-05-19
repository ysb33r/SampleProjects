package book

import book.allowuseroverride.AsciidoctorJExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AllowUserOverrideOfDependentLibraryPlugin2 implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create('asciidoctorj',AsciidoctorJExtension,project)
        def conf = project.configurations.maybeCreate( 'internal_asciidoctorj' )

        if( conf.respondsTo('defaultDependencies')) {
            conf.defaultDependencies { deps ->
                deps.add( project.dependencies.create(
                    "org.asciidoctor:asciidoctorj:${project.asciidoctorj.version}")
                )
            }
        } else {
            project.afterEvaluate {
                project.dependencies {
                    internal_asciidoctorj "org.asciidoctor:asciidoctorj:${project.asciidoctorj.version}"
                }
            }
        }
    }
}

