package co.elastic.android.gradle

import com.android.build.api.instrumentation.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.objectweb.asm.ClassVisitor

abstract class ElasticClassVisitorFactory: AsmClassVisitorFactory<ElasticClassVisitorFactory.SpanAddingParameters> {
//    private val listOfClassesInstrumentable: List<ClassInstrumentable> = listOf(OkHttpClassInstrumentable())
//    private var currentInstrumentableClass: ClassInstrumentable? = null

    interface SpanAddingParameters : InstrumentationParameters {

        /**
         * AGP will re-instrument dependencies, when the [InstrumentationParameters] changed
         * https://issuetracker.google.com/issues/190082518#comment4. This is just a dummy parameter
         * that is used solely for that purpose.
         */
        @get:Input
        @get:Optional
        val invalidate: Property<Long>
    }
    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        println("instrumenting class "+ classContext.currentClassData.className)
        return OkHttpMethodVisitor(classContext, nextClassVisitor)

    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return InstrumentHelper.isInstrumentable(classData)
    }
}