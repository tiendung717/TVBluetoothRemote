package com.atharok.btremote.domain.entity

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class ThirdLibrary(
    @StringRes val title: Int,
    @StringRes val id: Int,
    @StringRes val codeHost: Int,
    @StringRes val codeUrl: Int,
    @StringRes val license: Int,
    @StringRes val licenseUrl: Int,
) {
    CORE_KTX(
        title = R.string.androidx_core_ktx_name,
        id = R.string.androidx_core_ktx_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    ACTIVITY_COMPOSE(
        title = R.string.androidx_activity_compose_name,
        id = R.string.androidx_activity_compose_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    COMPOSE_BOM(
        title = R.string.androidx_compose_bom_name,
        id = R.string.androidx_compose_bom_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    COMPOSE_UI(
        title = R.string.androidx_compose_ui_name,
        id = R.string.androidx_compose_ui_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    COMPOSE_UI_GRAPHICS(
        title = R.string.androidx_compose_ui_graphics_name,
        id = R.string.androidx_compose_ui_graphics_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    COMPOSE_MATERIAL_3(
        title = R.string.androidx_compose_material3_name,
        id = R.string.androidx_compose_material3_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    COMPOSE_MATERIAL_ICONS_EXTENDED(
        title = R.string.androidx_compose_material_icons_extended_name,
        id = R.string.androidx_compose_material_icons_extended_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    LIFECYCLE_RUNTIME_COMPOSE(
        title = R.string.androidx_lifecycle_runtime_compose_name,
        id = R.string.androidx_lifecycle_runtime_compose_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    NAVIGATION_COMPOSE(
        title = R.string.androidx_navigation_compose_name,
        id = R.string.androidx_navigation_compose_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    DATA_STORE_PREFERENCES(
        title = R.string.androidx_data_store_preferences_name,
        id = R.string.androidx_data_store_preferences_id,
        codeHost = R.string.github,
        codeUrl = R.string.androidx_jetpack_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    ),

    KOIN_ANDROIDX_COMPOSE(
        title = R.string.koin_name,
        id = R.string.koin_id,
        codeHost = R.string.github,
        codeUrl = R.string.koin_url,
        license = R.string.apache_license_2_0,
        licenseUrl = R.string.apache_license_2_0_url
    )
}