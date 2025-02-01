package com.abanapps.deepseek.r1.data.utils

object Utils {

    private const val LOCAL_IP = "192.168.1.100"
    private const val PORT = "11434"
    internal const val DATA_STORE_FILE_NAME =  "prefs.preferences_pb"

    const val LOCAL = "http://localhost:$PORT"
    const val NETWORK = "http://$LOCAL_IP:$PORT"

    val models = listOf(
        "deepseek-coder:1.3b" to "DeepSeekCoder-1.3b",
        "deepseek-r1:1.5b" to "DeepSeekR1-1.5b"
    )



//     For production, you might want to add
//     const val PRODUCTION = "https://your-production-server.com"

}


