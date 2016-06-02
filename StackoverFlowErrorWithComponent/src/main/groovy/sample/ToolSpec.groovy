package sample

import org.gradle.model.Managed

@Managed
abstract class ToolSpec implements BaseSpec {

    @Override
    String getToolName() { 'Tool' }

}
