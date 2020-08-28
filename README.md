# warlock-runner
This project was completed as part of my second year Computer Science degree Java module. The finished application is a fully playable Android game made using Java with some optimizations (such as multi-threading) to increase performance. This project was purely for educational purposes and no commercial profit was, or is to be made.

**Abstract:**

Warlock Runner is a top-down scrolling infinite runner game (same genre as temple runner) that includes some bullet hell genre aspects such as dodging/killing enemies.

Score is gained over time, with each enemy also being worth a certain amount of score upon death depending on that enemy&#39;s difficulty. The level will increase when a score threshold has been met, resulting in the next tier of enemy to spawn, and the previous tiers to increase their respective spawn rates. If any enemy or enemy bullets are to hit the player, they will die.

The game features touch sensor control for the player character, allowing the user to move the sprite by dragging across the screen, and allowing the user to shoot by tapping.

There are four activities: a main menu activity allowing for navigation of other activities, the game activity, a small help activity containing information and a small tutorial, and a game-over activity. Upon player death, the user will be prompted to return to the main menu, where they can play again or quit the app.

Each activity (other than help activity) has its own music. The menu and game background songs loop indefinitely, whilst the game-over activity will play the death sound once.

The game includes two optimisation features, an FPS cap at 30, and multithreading in each enemies update method.

**App Showcase:**

The app launches with the menu activity allowing the user to navigate through the different activities.

![DocResources/fig_1.jpg](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/fig_1.jpg)

_Figure 1 - Main Menu_

When a game is started, the orientation will switch to landscape and the game will begin. The player will spawn at the centre left of the screen and will begin at level 1. The text in the bottom left prints out the current level and the text on the right prints out the current score.

![DocResources/fig_2.png](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/fig_2.png)

_Figure 2 - Gameplay_

As the game progresses, the score and level will increase, resulting in more difficult enemies spawning. After level 3 the player enters endless mode, where the spawn rate of enemies will continue to increase as score increases.

Eventually, the player will die, and all game functions will pause momentarily whilst the death message displays.

![DocResources/fig_3.png](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/fig_3.png)

_Figure 3 - Death Message_

Promptly after this, the activity will switch to the game over activity.

![DocResources/fig_4.png](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/fig_4.png)

_Figure 4 - Game Over_

The player has the option to return to the main menu, where they can then either exit the game, play again or view the help text.

![DocResources/fig_5.png](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/fig_5.png)

_Figure 5 - Help Text_

The help activity displays text containing information about the game, levels, and scoring system. The menu button in the top left will bring the user back to the menu

**Class diagram:**

![DocResources/class diagram.png](https://github.com/jounaidr/warlock-runner/blob/master/DocResources/class%20diagram.png)
