Tank is a 2D game. This version is just a demonstration of the game and doesn't include all the features. It shows the following behaviour:
1. Key 'f' is used to fire.
2. Key 'q' is used to exit.
3. The "Pause" button is used to pause the tanks.
4. The mouse can be used to select any tank or game objects.
5. The "Reverse" button is used the deflect the tanks from their original path. To produce this effect: click pause -> select a tank -> click reverse -> click play.

Architecture - MVC
Design Patterns used: Observer, Iterator, Proxy, Strategy, Command

Concepts used:
Object Oriented Design with UML class diagrams.
Java key bindings and mouse events.
GUI programming using Java Foundation Classes (Swing GUI components, Pluggable look and feel, Java2D API).
Animation: To represent movement of moveable game objects. This is achieving by registering observers to the timer object.
Collision detection and response. In the demo available, you can see this when the sound is turned on. You can make out the collision sound when the tanks collide and a different sound when a missile collides with the tank.
Sound API is used.
Object selection and game modes like pause and play are used.
