package com.atharok.btremote.common.injections

import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.content.Context
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.bluetoothHidDescriptor
import com.atharok.btremote.data.bluetooth.BluetoothHidProfile
import com.atharok.btremote.data.bluetooth.BluetoothInteractions
import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.data.repositories.BluetoothHidProfileRepositoryImpl
import com.atharok.btremote.data.repositories.BluetoothRepositoryImpl
import com.atharok.btremote.data.repositories.SettingsDataStoreRepositoryImpl
import com.atharok.btremote.domain.entity.keyboard.layout.DEKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.ESKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.FRKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.UKKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.USKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import com.atharok.btremote.domain.repositories.BluetoothRepository
import com.atharok.btremote.domain.repositories.SettingsDataStoreRepository
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.domain.usecases.BluetoothHidUseCase
import com.atharok.btremote.domain.usecases.BluetoothUseCase
import com.atharok.btremote.domain.usecases.SettingsUseCase
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModules by lazy {
    listOf<Module>(androidModule, viewModelModule, useCaseModule, repositoryModule, dataModule)
}

private val androidModule: Module = module {
    single<BluetoothManager> {
        androidContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    single<BluetoothHidDeviceAppSdpSettings> {
        BluetoothHidDeviceAppSdpSettings(
            androidContext().getString(R.string.app_name),
            "Bluetooth HID Device",
            "Atharok",
            BluetoothHidDevice.SUBCLASS2_UNCATEGORIZED,
            bluetoothHidDescriptor
        )
    }

    single { USKeyboardLayout() }
    single { UKKeyboardLayout() }
    single { ESKeyboardLayout() }
    single { FRKeyboardLayout() }
    single { DEKeyboardLayout() }
}

private val viewModelModule: Module = module {
    viewModel {
        BluetoothViewModel(
            useCase = get<BluetoothUseCase>()
        )
    }

    viewModel {
        BluetoothHidViewModel(
            useCase = get<BluetoothHidUseCase>()
        )
    }

    viewModel {
        SettingsViewModel(
            useCase = get<SettingsUseCase>()
        )
    }
}

private val useCaseModule: Module = module {
    single {
        BluetoothUseCase(
            bluetoothRepository = get<BluetoothRepository>()
        )
    }

    single {
        BluetoothHidServiceUseCase(
            repository = get<BluetoothHidProfileRepository>()
        )
    }

    single {
        BluetoothHidUseCase(
            repository = get<BluetoothHidProfileRepository>()
        )
    }

    single {
        SettingsUseCase(
            repository = get<SettingsDataStoreRepository>()
        )
    }
}

private val repositoryModule: Module = module {

    single<BluetoothRepository> {
        BluetoothRepositoryImpl(
            bluetoothInteractions = get<BluetoothInteractions>()
        )
    }

    single<BluetoothHidProfileRepository> {
        BluetoothHidProfileRepositoryImpl(
            hidProfile = get<BluetoothHidProfile>()
        )
    }

    single<SettingsDataStoreRepository> {
        SettingsDataStoreRepositoryImpl(
            settingsDataStore = get<SettingsDataStore>()
        )
    }
}

private val dataModule: Module = module {
    single {
        BluetoothInteractions(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter
        )
    }

    single {
        BluetoothHidProfile(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter,
            hidSettings = get<BluetoothHidDeviceAppSdpSettings>()
        )
    }

    single {
        SettingsDataStore(
            context = androidContext()
        )
    }
}