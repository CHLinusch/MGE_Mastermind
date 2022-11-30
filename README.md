#MGE Mastermind
# Description
This application is for playing the game "Mastermind" where you have to find out the correct color and order of pins.
Two modes can be chosen (repeatable and non repeatable colors).


<figure><img src="https://user-images.githubusercontent.com/91124387/204896743-853c8c30-a9ca-4430-8379-8d71cea583c2.png" style="width:20%"><figcaption align = "center"><b>English main menu, bright mode</b></figcaption></figure>

You press the colored buttons and the bottom to place them at the first available spot in a row (left to right). You can press the placed button to remove it while the row is not completly filled.
Upon filling the row, the guess gets evaluated in the smaller box to the row. 
For each black pin you placed a button with the right color at the right place.
For each white pin you placed a button with the right color at the wrong place.
For each grey pin you placed a button with the wrong color.
The goal is to get 4 black pins before you used up all your tries.
If you chose repeated colors, a color can appear more than once. If you did not chose so, already used colors are disabled for that try.

<figure><img src="https://user-images.githubusercontent.com/91124387/204897406-ec054d9a-d511-48e3-957f-3ba4341485be.png" style="width:20%"><figcaption align = "center"><b>Finished game, bright mode</b></figcaption></figure>



You can restart the game with a new solution by pressing the restart button.

![grafik](https://user-images.githubusercontent.com/91124387/204900052-fcf5b2ca-b2ec-4245-8dcd-1e36564c8c99.png)

You can get back to the menu with the back button. The last finished guess will be saved.


It has a dark and bright mode available. The game state will be persisted, you can resume play even after closing the app.

<figure><img src="https://user-images.githubusercontent.com/91124387/204899800-18faf35d-5435-4249-ab85-9c7116b2bba3.png" style="width:20%"><figcaption align = "center"><b>Finished game, dark mode</b></figcaption></figure>


It is available currently in two languages: English and German. It is written in Kotlin.

<figure><img src="https://user-images.githubusercontent.com/91124387/204900154-083f0af7-81a2-4385-9e9d-cc77b3ba71b2.png" style="width:20%"><figcaption align = "center"><b>Dark mode German menu</b></figcaption></figure>


# Points
This project aims for at least the following points:



- Functionality: 1-2

- Persistence: At least 1

- Localisation: 1 (EN/DE)

- Styles: 1

- Kotlin: 3




# Version History:

0.1: Pegs, Game Evaluation Logic

0.2: Mainscreeen, Strings (de+en)

0.3: Layout Game

0.4: Persistence

0.4.1: Persistence in Activity

0.5: Layout, Button/Viewmapping

0.6-....: Game Logic, Styling, Locales, Bugfixing

1.0: First release


