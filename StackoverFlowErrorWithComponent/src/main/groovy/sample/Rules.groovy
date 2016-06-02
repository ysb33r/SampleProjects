package sample

import groovy.transform.CompileStatic
import org.gradle.model.RuleSource
import org.gradle.platform.base.ComponentType
import org.gradle.platform.base.TypeBuilder

@CompileStatic
class Rules extends RuleSource {
    @ComponentType
    void registerComponent(TypeBuilder<ToolSpec> builder ) {
    }
}
