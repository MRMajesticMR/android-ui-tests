package club.dari.androiduitests

import android.app.Application
import club.dari.androiduitests.model.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)

            modules(
                listOf(
                    dataModule
                )
            )
        }
    }

}