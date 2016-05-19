package book.allowuseroverride

import groovy.transform.PackageScope
import org.gradle.api.Project

class AsciidoctorJExtension {
    String version = '1.5.0'

    AsciidoctorJExtension(Project proj) {
        project=proj
    }

    @PackageScope
    Project project
}
