package kh.farrukh.espielspringsecurity.common.paging

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Page

class PagingResponse<T>(

    @JsonProperty("next_page")
    val nextPage: Int? = null,

    @JsonProperty("prev_page")
    val prevPage: Int? = null,

    @JsonProperty("total_pages")
    val totalPages: Int = 0,

    @JsonProperty("total_items")
    val totalItems: Int = 0,

    val page: Int = 1,

    val items: List<T> = emptyList(),
)

fun <T> Page<T>?.toPagingResponse(): PagingResponse<T> {
    if (this == null) return PagingResponse()
    return PagingResponse(
        if (hasNext()) nextPageable().pageNumber + 1 else null,
        if (hasPrevious()) previousPageable().pageNumber + 1 else null,
        totalPages,
        totalElements.toInt(),
        pageable.pageNumber + 1,
        content
    )
}