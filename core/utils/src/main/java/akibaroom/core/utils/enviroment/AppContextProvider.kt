package akibaroom.core.utils.enviroment

import android.app.Activity
import android.app.Application
import android.content.Context

object AppContextProvider {

    private lateinit var application: Application
    lateinit var activity: Activity

    val context: Context get() = application.applicationContext

    fun init(application: Application) {
        this.application = application
    }
}
