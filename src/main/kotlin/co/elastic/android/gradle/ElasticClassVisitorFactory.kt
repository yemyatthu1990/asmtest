package co.elastic.android.gradle

import com.android.build.api.instrumentation.*
import org.apache.log4j.helpers.LogLog.debug
import org.apache.log4j.helpers.LogLog.warn
import org.gradle.api.provider.Property
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import java.util.logging.Logger

abstract class ElasticClassVisitorFactory: AsmClassVisitorFactory<InstrumentationParameters.None> {

    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        val classReader = ClassReader(classContext.currentClassData.className)
        val classWriter = ClassWriter(classReader, ClassReader.EXPAND_FRAMES)
        val visitor = AddMethodVisitor(classWriter)
        classReader.accept(visitor, 0)
        return classWriter
    }

    override fun isInstrumentable(classData: ClassData): Boolean {

        if (classData.className == "androidx.fragment.app.FragmentActivity") {
            println(classData.className)
            return true
        }
        return false
    }
}