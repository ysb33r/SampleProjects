package sample

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification


class RulesSpec extends Specification {
    Project project = ProjectBuilder.builder().build()

    void setup() {
        project.apply plugin: Rules
    }

    def "Can create a component"() {
        when: 
        project.with {
            model {
                components {
                    tool(ToolSpec) 
                    
                }
            }
        }

        ToolSpec node = project.modelRegistry.find('components.tool',ToolSpec)

        then:
        node != null
        node?.toolName  == 'Tool'
    }
}
