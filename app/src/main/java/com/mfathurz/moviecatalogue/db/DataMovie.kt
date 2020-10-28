package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.MovieEntity

object DataMovie {
    fun queryAllMovies(): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()

        movies.add(
            MovieEntity(
                "Ralph Breaks the Internet",
                R.drawable.poster_ralph,
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Phil Johnston, Rich Moore",
                "November 20, 2018",
                "Family, Animation, Comedy, Adventure",
                "Released",
                "1h 52m",
                "English",
                "John C. Reilly | Sarah Silverman | Gal Gadot | Taraji P. Henson | Jack McBrayer"
            )
        )

        movies.add(
            MovieEntity(
                "Aquaman",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                "December 7, 2018",
                "Action, Adventure, Fantasy",
                "Released",
                "2h 24m",
                "English",
                "Jason Momoa | Amber Heard | Willem Dafoe | Patrick Wilson | Nicole Kidman"
            )
        )

        movies.add(
            MovieEntity(
                "Mortal Engines",
                R.drawable.poster_mortal_engines,
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Christian Rivers",
                "November 27, 2018",
                "Adventure, Fantasy",
                "Released",
                "2h 9m",
                "English",
                "Hera Hilmar | Robert Sheehan | Hugo Weaving | Jihae | Ronan Raftery"
            )
        )

        movies.add(
            MovieEntity(
                "Spiderman - Into the Spider Universe",
                R.drawable.poster_spiderman,
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Rodney Rothman, Bob Persichetti",
                "December 6, 2018",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "Released",
                "1h 57m",
                "English",
                "Shameik Moore | Jake Johnson | Hailee Steinfeld | Mahershala Ali | Brian Tyree Henry"
            )
        )

        movies.add(
            MovieEntity(
                "Robin Hood",
                R.drawable.poster_robin_hood,
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "Otto Bathurst",
                "November 21, 2018",
                "Adventure, Action, Thriller",
                "Released",
                "1h 56m",
                "English",
                "Taron Egerton | Jamie Foxx | Ben Mendelsohn | Eve Hewson | Jamie Dornan"
            )
        )

        movies.add(
            MovieEntity(
                "How to Train Your Dragon: The Hidden World",
                R.drawable.poster_how_to_train,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Dean DeBlois",
                "January 3, 2019",
                "Animation, Family, Adventure",
                "Released",
                "1h 44m",
                "English",
                "Jay Baruchel | America Ferrera | Cate Blanchett | Craig Ferguson | F. Murray Abraham"
            )
        )

        movies.add(
            MovieEntity(
                "Glass",
                R.drawable.poster_glass,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
                "January 16, 2018",
                "Thriller, Drama, Science Fiction",
                "Released",
                "2h 9m",
                "English",
                "James McAvoy | Bruce Willis | Anya Taylor-Joy | Sarah Paulson | Samuel L. Jackson"
            )
        )

        movies.add(
            MovieEntity(
                "Alita: Battle Angel",
                R.drawable.poster_alita,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                "January 31, 2018",
                "Action, Science Fiction, Adventure",
                "Released",
                "2h 2m",
                "English",
                "Rosa Salazar | Christoph Waltz | Jennifer Connelly | Mahershala Ali | Ed Skrein"
            )
        )

        movies.add(
            MovieEntity(
                "T-34",
                R.drawable.poster_t34,
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "Alexey Sidorov",
                "December 27, 2018",
                "War, Action, Drama, History",
                "Released",
                "2h 19m",
                "Russian",
                "Alexander Petrov | Victor Dobronravov | Irina Starshenbaum | Vinzenz Kiefer | Petr Skvortsov"
            )
        )

        movies.add(
            MovieEntity(
                "Avenger: Infinity War",
                R.drawable.poster_infinity_war,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Joe Russo, Anthony Russo",
                "April 25, 2018",
                "Adventure,Action,Science Fiction",
                "Released",
                "2h 29m",
                "English",
                "Robert Downey Jr. | Chris Hemsworth |Chris Evans | Scarlett Johansson | Benedict Cumberbatch"
            )
        )

        return movies
    }
}