package ru.kyamshanov.mission.search_project.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.search_project.api.di.SearchProjectFacadeComponent

class SearchProjectFacadeComponentBuilder : ComponentBuilder<SearchProjectFacadeComponent> {

    override fun build(): SearchProjectFacadeComponent =
        DaggerModuleComponent.factory().create(
            networkComponent = requireNotNull(Di.getComponent())
        )
}