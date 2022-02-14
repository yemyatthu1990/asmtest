package co.elastic.android.gradle

import com.android.build.api.instrumentation.ClassData

class InstrumentHelper {
    companion object {
        private const val androidActivityKeyword = "android.app.Activity"
        private const val androidKeyword = "android"
        private const val okHttp3Keyword = "okhttp3.OkHttpClient\$Builder"

        fun isInstrumentable(classData: ClassData) : Boolean {
            return classData.className == okHttp3Keyword
        }
    }
}