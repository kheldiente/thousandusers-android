package kheldiente.apps.thousandusers

import android.app.Application
import kheldiente.apps.thousandusers.di.AppDependencies

class ThousandUserApplication: Application() {

    val appDependencies by lazy { AppDependencies(applicationContext) }

}