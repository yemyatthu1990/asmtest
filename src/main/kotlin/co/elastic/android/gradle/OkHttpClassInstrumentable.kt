package co.elastic.android.gradle

import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class OkHttpClassInstrumentable : ClassInstrumentable {
    private  val okHttp3Keyword = "okhttp3.OkHttpClient.Builder"
    override fun isInstrumentable(classData: ClassData): Boolean {
        return okHttp3Keyword == classData.className
    }

    override fun getVisitor(classContext: ClassContext, classVisitor: ClassVisitor): ClassVisitor {
        return OkHttpMethodVisitor(classContext ,classVisitor)
    }
}