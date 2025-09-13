package akibaroom.feature.figures.data.paging

import akibaroom.core.domain.model.Figure
import androidx.paging.PagingSource
import androidx.paging.PagingState
import akibaroom.core.network.models.Response
/*
class FiguresPagingSource(
    private val repository: FigureRepository
) : PagingSource<Int, Figure>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Figure> {
        val page = params.key ?: 1
        return when (val result = repository.fetchFigures(page)) {
            is Response.Success -> {
                val data = result.data
                val list = data.results
                val nextKey = if (list.isEmpty()) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                LoadResult.Page(
                    data = list,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            is Response.Error -> {
                LoadResult.Error(Exception("Unknown error"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Figure>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
*/

