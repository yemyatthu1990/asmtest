package co.elastic.android.gradle

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ASM9

class OkHttpMethodVisitor (private val classContext: ClassContext, val classVisitor: ClassVisitor) : ClassVisitor(ASM9, classVisitor) {
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        //OkhttpClient.Build method
        if (name == "build") {
            println("Instrumenting method $name of Class ${classContext.currentClassData.className}")
            var mv = classVisitor.visitMethod(access, name, descriptor, signature, exceptions)
            mv = OkHttp3MethodAdapter(ASM9, mv, access, name, descriptor)
            return mv
        }
        return classVisitor.visitMethod(access, name, descriptor, signature, exceptions)
    }
}