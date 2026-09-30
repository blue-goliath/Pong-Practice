Java Pong

A simple two-player implementation of the classic Pong game written in Java using Java Swing.

This project was created as an early Java programming project to practice object-oriented programming, event handling, graphics, collision detection, game loops, and basic game physics.

Features
Two-player local gameplay
Paddle movement
Ball movement and bouncing
Paddle-ball collision detection
Score tracking
First player to reach 10 points wins
Pause and resume functionality
Basic angle-based ball deflection depending on where the ball hits the paddle
Technologies
Language: Java
GUI: Java Swing
Graphics: Java AWT
Controls
Player 1
↑ — Move paddle up
↓ — Move paddle down
Player 2
← — Move paddle up
→ — Move paddle down
Game Controls
SPACE — Pause
K — Resume
How to Run
Requirements
Java Development Kit (JDK)

Check that Java is installed by running:

java --version
Running the Game

Clone the repository:

git clone <github.com/blue-goliath/Pong-Practice>

Navigate into the project directory:

cd <Pong-Practice>

Compile the program:

javac Pong.java

Run the game:

java Pong
How It Works

The game uses a Swing Timer to repeatedly update the game state and repaint the game window. The timer runs approximately every 16 milliseconds, targeting roughly 60 updates per second.

The main game loop is responsible for:

Checking whether a player has scored.
Updating the ball's position.
Checking collisions with the top and bottom boundaries.
Checking collisions between the ball and paddles.
Updating the display.

Keyboard input is handled using Java's KeyListener functionality.

Project Structure

The project is currently contained in a single Java source file:

Pong.java

The program contains:

Pong — Main game panel and game logic
RectangleObj — Represents a paddle
CircleObj — Represents the ball
Collision Detection

The game uses the relative positions of the ball and paddles to determine when a collision occurs.

When the ball hits a paddle, its horizontal velocity is reversed. The vertical velocity is also adjusted based on the location where the ball contacts the paddle, allowing the player to influence the ball's trajectory.

What I Learned

This project helped me practice:

Java classes and objects
Basic object-oriented programming
Java Swing
Java AWT graphics
Keyboard event handling
Game loops
Collision detection
Basic game physics
Managing game state
Working with ArrayList
Future Improvements

Possible improvements include:


Adding a start/restart screen
Adding sound effects
Separating the game into multiple classes
Adding additional game modes

Project Status

Complete — with potential future improvements.

This project represents an early Java programming project and was primarily created as a learning exercise.