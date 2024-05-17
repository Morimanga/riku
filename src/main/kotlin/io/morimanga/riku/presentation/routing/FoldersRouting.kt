package io.morimanga.riku.presentation.routing

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.resources.post
import io.morimanga.riku.presentation.controller.FoldersController
import kotlinx.serialization.SerialName

fun Route.foldersRouting() {
    get<FoldersRouting.AllFolders> {
        FoldersController(call).getFolders()
    }

    get<FoldersRouting.GetFolderComics> {
        FoldersController(call).getFolderContent(it.id)
    }

    post<FoldersRouting.EditFolder> {
        FoldersController(call).editFolder()
    }

    delete<FoldersRouting.DeleteFolder> {
        FoldersController(call).deleteFolder(it.id)
    }

    post<FoldersRouting.EditComics> {
        FoldersController(call).editComics(it.folderId)
    }

    delete<FoldersRouting.DeleteComics> {
        FoldersController(call).deleteComics(it.localComicsId)
    }
}

@Suppress("unused") // do not touch this.
@Resource("/folder")
class FoldersRouting {

    @Resource("all-folders")
    class AllFolders(val parent: FoldersRouting = FoldersRouting())

    @Resource("get-by-id")
    class GetFolderComics(val parent: FoldersRouting = FoldersRouting(), @SerialName("folder_id") val id: Int)

    @Resource("edit-comics")
    class EditComics(val parent: FoldersRouting = FoldersRouting(), @SerialName("folder_id") val folderId: Int)

    @Resource("delete-comics")
    class DeleteComics(val parent: FoldersRouting = FoldersRouting(), @SerialName("local_comics_id") val localComicsId: Int)

    @Resource("edit-folder")
    class EditFolder(val parent: FoldersRouting = FoldersRouting())

    @Resource("delete-folder")
    class DeleteFolder(val parent: FoldersRouting = FoldersRouting(), @SerialName("folder_id") val id: Int)
}