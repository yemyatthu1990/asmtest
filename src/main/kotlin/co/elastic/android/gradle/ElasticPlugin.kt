package co.elastic.android.gradle

import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.logging.Logger

class ElasticPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.withPlugin("com.android.application") {
            val androidComponentsExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)
            androidComponentsExtension.onVariants {
                it.transformClassesWith(ElasticClassVisitorFactory::class.java, InstrumentationScope.ALL){params ->
                    params.invalidate.set(System.currentTimeMillis())

                }
            }
        }
    }
}