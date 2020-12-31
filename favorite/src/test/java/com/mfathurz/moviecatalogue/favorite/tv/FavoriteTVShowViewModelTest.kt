package com.mfathurz.moviecatalogue.favorite.tv

class FavoriteTVShowViewModelTest {

//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var viewModel: FavoriteTVShowViewModel
//
//    private var movieRepository: MovieRepository = mock()
//    private var observer: Observer<PagedList<TVShowEntity>> = mock()
//    private var pagedList: PagedList<TVShowEntity> = mock()
//
//    @Before
//    fun setUp() {
//        viewModel = FavoriteTVShowViewModel(movieRepository)
//    }
//
//
//    @Test
//    fun getFavoriteTVShows() {
//        val dummyFavoriteTVShows = pagedList
//
//        `when`(dummyFavoriteTVShows.size).thenReturn(6)
//        val favoriteTVShows = MutableLiveData<PagedList<TVShowEntity>>()
//        favoriteTVShows.value = dummyFavoriteTVShows
//
//        `when`(movieRepository.getPagedFavoriteTVShows()).thenReturn(favoriteTVShows)
//        val tvShowEntities = viewModel.favoriteTVShows.value
//        verify(movieRepository).getPagedFavoriteTVShows()
//        assertNotNull(tvShowEntities)
//        assertEquals(6, tvShowEntities?.size)
//
//        viewModel.favoriteTVShows.observeForever(observer)
//        verify(observer).onChanged(dummyFavoriteTVShows)
//    }

}