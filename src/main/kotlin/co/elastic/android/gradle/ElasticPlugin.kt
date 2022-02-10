package co.elastic.android.gradle

import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.logging.Logger

class ElasticPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        println("here at least")
        project.pluginManager.withPlugin("com.android.application") {
            println("here too")
            val androidComponentsExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)
            androidComponentsExtension.onVariants {
                println(it.name + " gradle variant")
                it.transformClassesWith(ElasticClassVisitorFactory::class.java, InstrumentationScope.ALL){}
            }
        }
    }
}