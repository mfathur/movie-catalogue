package com.mfathurz.moviecatalogue.db

class MovieRepository {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository()
            }
    }
}