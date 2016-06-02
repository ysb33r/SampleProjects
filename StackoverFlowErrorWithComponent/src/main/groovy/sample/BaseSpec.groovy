package sample

import org.gradle.model.Managed
import org.gradle.platform.base.ComponentSpec
import org.gradle.platform.base.VariantComponentSpec

@Managed
interface BaseSpec extends ComponentSpec, VariantComponentSpec {
  String getToolName()
}
