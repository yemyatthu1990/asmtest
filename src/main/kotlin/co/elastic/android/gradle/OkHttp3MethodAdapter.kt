package co.elastic.android.gradle

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class OkHttp3MethodAdapter(api: Int, methodVisitor: MethodVisitor?, access: Int, name: String?, descriptor: String?) :
    AdviceAdapter(ASM9, methodVisitor, access, name, descriptor) {
//    override fun visitCode() {
//        super.visitCode()
//        visitVarInsn(ALOAD, 0);
//        visitMethodInsn(INVOKESTATIC, "io/github/yemyatthu1990/apm/instrumentations/okhttp3/OkHttpNetworkInstrumentTracer", "getInstrumentCallFactory", "(Lokhttp3/OkHttpClient;)Lokhttp3/Call\$Factory;", false);
//        visitVarInsn(ALOAD, 1);
//        visitMethodInsn(INVOKEINTERFACE, "okhttp3/Call\$Factory", "newCall", "(Lokhttp3/Request;)Lokhttp3/Call;", true);
//        visitInsn(ARETURN);
//    }
    override fun onMethodEnter() {
        visitVarInsn(Opcodes.ALOAD, 0);
        visitFieldInsn(Opcodes.GETFIELD, "okhttp3/OkHttpClient\$Builder", "networkInterceptors", "Ljava/util/List;")
        visitMethodInsn(
            INVOKESTATIC,
            "io/github/yemyatthu1990/apm/instrumentation/OkHttpNetworkInstrumentTracer",
            "getInstrumentInterceptor",
            "()Lokhttp3/Interceptor;",
            false
        );
        visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
    }
}