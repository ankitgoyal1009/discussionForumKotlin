package com.ankitgoyal1009.discussionforum.common

import android.content.Context
import java.io.InputStream

class Util {
    companion object {
        /**
         * This method will read a given file from Raw folder.
         */
        fun readJSONFromAsset(context: Context, resourceId: Int): String? {
            var json: String? = null
            try {
                val  inputStream: InputStream = context.resources.openRawResource(resourceId)
                json = inputStream.bufferedReader().use{it.readText()}
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }

    }
}
