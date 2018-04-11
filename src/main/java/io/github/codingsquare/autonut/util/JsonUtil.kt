package io.github.codingsquare.autonut.util

import com.github.salomonbrys.kotson.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser

val GSON = Gson()
val JSON_PARSER = JsonParser()

fun String.parseJson(): JsonElement = JSON_PARSER.parse(this)

fun Any.toJson(): JsonElement = GSON.toJson(this).parseJson()

inline fun <reified T : Any> JsonElement.byClass(
    key: String? = null,
    noinline default: (() -> T)? = null,
    noinline getter: (JsonElement) -> T = { GSON.fromJson(it) },
    noinline setter: (T) -> JsonElement = { it.toJson() }
): JsonObjectDelegate<T> =
    JsonObjectDelegate(
        this.obj,
        getter,
        setter,
        key,
        default
    )

inline fun <reified T : Any> JsonElement.byNullableClass(
    key: String? = null,
    noinline default: (() -> T)? = null,
    noinline getter: (JsonElement) -> T = { GSON.fromJson(it) },
    noinline setter: (T?) -> JsonElement? = { it?.toJson() }
): NullableJsonObjectDelegate<T?> =
    NullableJsonObjectDelegate(
        this.obj,
        getter,
        { setter(it) ?: jsonNull },
        key,
        default
    )
