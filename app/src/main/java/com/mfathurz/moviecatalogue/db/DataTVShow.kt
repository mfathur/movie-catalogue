package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.TVShowEntity

object DataTVShow {
    fun queryAllTVShow(): List<TVShowEntity> {
        val tvShows = mutableListOf<TVShowEntity>()

        tvShows.add(
            TVShowEntity(
                "The Simpsons",
                R.drawable.poster_the_simpson,
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "Matt Groening",
                "December 16, 1989",
                "Animation, Comedy, Family, Drama",
                "Returning Series",
                "22m",
                "English",
                "Dan Castellaneta | Julie Kavner | Nancy Cartwright | Yeardley Smith | Hank Azaria"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Naruto Shippūden",
                R.drawable.poster_naruto_shipudden,
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "Masashi Kishimoto",
                "February 15, 2007",
                "Animation, Comedy, Drama",
                "Ended",
                "25m",
                "Japanese",
                "Kazuhiko Inoue | Jouji Nakata | Akira Ishida | Hideo Ishikawa | Kōichi Tōchika"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Arrow",
                R.drawable.poster_arrow,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg",
                "October 10, 2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "Ended",
                "42m",
                "English",
                "Stephen Amell | David Ramsey | Katie Cassidy | Willa Holland | Paul Blackthorne"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Game of Thrones",
                R.drawable.poster_got,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "David Benioff, D. B. Weiss",
                "April 17, 2011",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                "Ended",
                "1h",
                "English",
                "Emilia Clarke | Lena Headey | Sophie Turner | Kit Harington | Peter Dinklage"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Hanna",
                R.drawable.poster_hanna,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "David Farr",
                "March 28, 2019",
                "Action & Adventure, Drama\n",
                "Returning Series",
                "50m",
                "English",
                "Esme Creed-Miles | Mireille Enos | Noah Taylor | Joel Kinnaman | Cherrelle Skeete"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Gotham",
                R.drawable.poster_gotham,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Bruno Heller",
                "September 22, 2014",
                "Drama, Fantasy, Crime\n",
                "Ended",
                "43m",
                "English",
                "Ben McKenzie | Donal Logue | David Mazouz | Sean Pertwee | Robin Lord Taylor"
            )
        )

        tvShows.add(
            TVShowEntity(
                "The Umbrella Academy",
                R.drawable.poster_the_umbrella,
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "Steve Blackman",
                "February 15, 2019",
                "Action & Adventure, Sci-Fi & Fantasy, Comedy, Drama",
                "Returning Series",
                "55m",
                "English",
                "Tom Hopper | David Castañeda | Emmy Raver-Lampman | Robert Sheehan | Aidan Gallagher"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Supernatural",
                R.drawable.poster_supernatural,
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "Eric Kripke",
                "September 13, 2005",
                "Drama, Mystery, Sci-Fi & Fantasy",
                "Returning Series",
                "45m",
                "English",
                "Jensen Ackles | Jared Padalecki | Misha Collins | Jim Beaver | Ruth Connell"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Marvel's Iron Fist",
                R.drawable.poster_iron_fist,
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "March 17, 2017",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Canceled",
                "55m",
                "English",
                "Finn Jones | Jessica Henwick | Jessica Stroup | Tom Pelphrey | Rosario Dawson"
            )
        )

        tvShows.add(
            TVShowEntity(
                "Doom Patrol",
                R.drawable.poster_doom_patrol,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Jeremy Carver",
                "February 15, 2019",
                "Sci-Fi & Fantasy, Action & Adventure",
                "Returning Series",
                "49m",
                "",
                "English"
            )
        )

        return tvShows
    }
}