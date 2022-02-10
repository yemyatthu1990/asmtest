package co.elastic.android.gradle

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodAdapter(api: Int, methodVisitor: MethodVisitor?, access: Int, name: String?, descriptor: String?) :
    AdviceAdapter(ASM9, methodVisitor, access, name, descriptor) {
    override fun onMethodEnter() {
        mv.visitLdcInsn(name)
        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"io/github/yemyatthu1990/apm/instrumentations/InstrumentTracker","enterMethodPrint","(Ljava/lang/String;)V",false)
    }

    override fun onMethodExit(opcode: Int) {
        if (opcode != Opcodes.ATHROW){
            mv.visitLdcInsn(name)
            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"io/github/yemyatthu1990/apm/instrumentations/InstrumentTracker","exitMethodPrint","(Ljava/lang/String;)V",false)
        }
    }
}