pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "Hospital_Automation"
include(":app")
include(":feature:signup")
include(":core:ui-components")
include(":core:network")
include(":core:datastore")
include(":feature:login")
include(":feature:email_verification")
include(":feature:upload_employee_documents")
include(":feature:add_residential_address")
include(":feature:reset_password")
include(":feature:enter_email")
include(":feature:home")
include(":feature:employee_profile")
include(":feature:upload_employee_profile_image")
include(":feature:guardians_search")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:utility")
include(":feature:children_search")
include(":feature:child_profile")
include(":feature:add_child_screen")
include(":feature:guardian_profile")
include(":feature:children")
include(":feature:employment_history")
include(":core:navigation")
include(":feature:admin_profile")
