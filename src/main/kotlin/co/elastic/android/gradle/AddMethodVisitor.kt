package co.elastic.android.gradle

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ASM9

class AddMethodVisitor(private val classVisitor: ClassVisitor) : ClassVisitor(ASM9, classVisitor) {

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        if (name == "onCreate") {
            var mv = classVisitor.visitMethod(access, name, descriptor, signature, exceptions)
            mv = TraceMethodAdapter(ASM9, mv, access, name, descriptor)
            return mv
        }
        return classVisitor.visitMethod(access, name, descriptor, signature, exceptions)
    }
}