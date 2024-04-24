package io.morimanga.riku.presentation.routing

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.morimanga.riku.presentation.controller.AddonsController
import kotlinx.serialization.SerialName

fun Route.addonsRouting() {
    get<AddonsRouting.GetAllAddons> {
        AddonsController(call).getAllAddons()
    }
    get<AddonsRouting.GetComics> {
        AddonsController(call).getTitleById(it.remoteId, it.addonId)
    }
    get<AddonsRouting.GetLatestComics> {
        AddonsController(call).getLatestTitles(it.page, it.addonId)
    }
    get<AddonsRouting.SearchComics> {
        AddonsController(call).searchTitle(it.addonId, it.page, it.sortType, it.name, it.years, it.genres)
    }
    get<AddonsRouting.GetComicsChapter> {
        AddonsController(call).getTitleChapterById(it.addonId, it.chapterId, it.remoteId)
    }
}

@Suppress("unused") // Do not touch this.
@Resource("/addons")
class AddonsRouting {

    @Resource("get-all-addons")
    class GetAllAddons(val parent: AddonsRouting = AddonsRouting())

    @Resource("get-comics")
    class GetComics(
        val parent: AddonsRouting = AddonsRouting(),
        @SerialName("remote_id")
        val remoteId: String,
        @SerialName("addon_id")
        val addonId: Int
    )

    @Resource("get-comics-chapter")
    class GetComicsChapter(
        val parent: AddonsRouting = AddonsRouting(),
        @SerialName("addon_id")
        val addonId: Int,
        @SerialName("remote_id")
        val remoteId: String,
        @SerialName("chapter_id")
        val chapterId: Int
    )

    @Resource("get-latest-comics")
    class GetLatestComics(
        val parent: AddonsRouting = AddonsRouting(),
        @SerialName("addon_id")
        val addonId: Int,
        val page: Int
    )

    @Resource("search-comics")
    class SearchComics(
        val parent: AddonsRouting = AddonsRouting(),
        @SerialName("addon_id")
        val addonId: Int,
        val page: Int,
        @SerialName("sort")
        val sortType: Boolean,
        val name: String? = null,
        val years: List<Int>? = null,
        val genres: List<String>? = null
    )

}