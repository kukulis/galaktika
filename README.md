# galaktika
Game for galactic wars online; implementing with java.

The idea of the game is to design own ships and then to build them for battles. Ship has attack, defence, speed and cargo capacity.
Each attribute requires amount of resources to build. Also attributes relates between each other. For example speed depends on power of engines divided by the total mass of the ship. And defence depends on how much resources you put for it divided by the size of ship ( total mass ^ 1/3 ).

Resources are limited by the size of your planet, which produces some amount each turn, depending on how many population lives in it.
Also you may invest your planet resources for upgrading technologies of attack, defence, engines or cargo.

You also may inhabit other planets. Because there are other players in the galaxy, there is some concurency between them. But you may make alliances.

The game is turn based - one turn a day.

BTW, the game idea is very old; it is already implemented with some C++ clients and players used to play by sending command files through email.

My purpose is to make it a web based game. A bit simpler than the original one, but with possibility to do some additions, like different weapon types, add own ship images, some events in the universe, special artifacts and so on ... but this will be later. First step is to make a simple playable version. May be even without any graphics. 

Next step is to make graphics, implement possibility to communicate between players in the site, to make alliances and so on.

And after that start to make some additions in to the game, like artifacts, different weapons and various events in to the galaxy.

# build notes
When you import maven project in to eclipse, you must add target/generated-resources as the source folder in the project properties -> java build path -> source.

# running notes
You must create some users in to the "users" table, to be able to login from GUI.
