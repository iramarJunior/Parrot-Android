package com.iramarjunior.parrot.core.application

import android.app.Application
import com.iramarjunior.parrot.BuildConfig
import com.iramarjunior.parrot.core.SessionController
import com.iramarjunior.parrot.modules.authentication.database.AuthenticationDatabase
import io.realm.Realm
import io.realm.RealmConfiguration

class ParrotAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {

        Realm.init(this)

        val realmConfigBuilder = RealmConfiguration.Builder()
        realmConfigBuilder.schemaVersion(BuildConfig.VERSION_CODE.toLong())
        realmConfigBuilder.deleteRealmIfMigrationNeeded()

        Realm.setDefaultConfiguration(realmConfigBuilder.build())

        SessionController.user = AuthenticationDatabase.getUser()
        SessionController.sessionAuthentication = AuthenticationDatabase.getSessionAuthentication()
    }

}