package co.elastic.android.gradle

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodAdapter(api: Int, methodVisitor: MethodVisitor?, access: Int, name: String?, descriptor: String?) :
    AdviceAdapter(ASM9, methodVisitor, access, name, descriptor) {
    override fun onMethodEnter() {
        println(name)
        visitFieldInsn(Opcodes.GETSTATIC, "io/github/yemyatthu1990/apm/instrumentations/InstrumentTracer", "Companion",
                        "Lio/github/yemyatthu1990/apm/instrumentations/InstrumentTracer" + '$' + "Companion;")
        mv.visitLdcInsn(name)
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"io/github/yemyatthu1990/apm/instrumentations/InstrumentTracer\$Companion","enterMethodPrint","(Ljava/lang/String;)V",false)
    }

    override fun onMethodExit(opcode: Int) {
        if (opcode != Opcodes.ATHROW){
            println(name)
            visitFieldInsn(Opcodes.GETSTATIC, "io/github/yemyatthu1990/apm/instrumentations/InstrumentTracer", "Companion",
                        "Lio/github/yemyatthu1990/apm/instrumentations/InstrumentTracer" + '$' + "Companion;")
            mv.visitLdcInsn(name)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"io/github/yemyatthu1990/apm/instrumentations/InstrumentTracer\$Companion","exitMethodPrint","(Ljava/lang/String;)V",false)
        }
    }
}