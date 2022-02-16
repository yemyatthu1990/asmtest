package co.elastic.android.gradle

import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor

interface ClassInstrumentable{

    fun isInstrumentable(classData: ClassData): Boolean

    fun getVisitor(classContext: ClassContext, classVisitor: ClassVisitor) : ClassVisitor
}