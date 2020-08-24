package com.woowrale.openlibrary.di.components

import android.app.Application
import com.woowrale.openlibrary.MainApplication
import com.woowrale.openlibrary.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ThreadModule::class,
        ActivitiesBuilderModule::class,
        FragmentsBuilderModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MainApplication)
}