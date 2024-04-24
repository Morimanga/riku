package io.morimanga.riku.data.map

import extensions.base.Extensions
import io.morimanga.riku.domain.model.Addon
import module.extension.ExtensionInfo

fun addonMapToDomain(id: Int, extension: ExtensionInfo) = Addon(
    id = id,
    name = extension.name,
    poster = extension.imagePath,
    description = extension.description
)