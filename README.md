# The Maze Runner 🏃‍♂️🧭

**The Maze Runner** is a top-down 2D maze exploration game built in Java using the City Engine. 

Instead of relying on static, pre-designed maps, this game utilizes a **Random Walk Algorithm** to procedurally generate a unique labyrinth every time you hit play. The objective is to navigate the unpredictable pathways and reach a randomly generated goal point within a strict time limit!

## ✨ Features

* **Procedural Map Generation:** Uses a random walk algorithm to carve out organic, unpredictable pathways, ensuring no two playthroughs are the same.
* **Three Difficulty Levels:** Choose from Easy, Medium, or Hard. The higher the difficulty, the larger and more complex the generated maze becomes.
* **Beat the Clock:** You are racing against a timer! Find the hidden goal before time runs out.
* **Custom Audio:** Features immersive audio loops and sound playback during gameplay.

## 🛠️ Technologies & Libraries

* **Language:** Java
* **Game Engine:** City Engine (Graphics & Physics)
* **UI/Layout Management:** Java Swing with `CardLayout` (for smooth menu and difficulty screen transitions)

## 🧠 How the Generation Works

The core of the map generation relies on the **Random Walk** concept. The algorithm starts at a specific coordinate on a solid grid and randomly "walks" step-by-step in different directions. As it moves, it carves out open paths. This creates sprawling and chaotic maze structures that require genuine problem-solving to navigate from the spawn point to the randomized target.

## 🚀 Getting Started

### Prerequisites
Make sure you have the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your machine. You will also need the **City Engine** library properly linked in your build path to compile and run the game.

### Installation & Running

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Usman-2805/the-maze-runner.git](https://github.com/Usman-2805/the-maze-runner.git)

2. **Navigate to the main game directory:**
   ```bash
   cd the-maze-runner/game/src/MainGame

3. **Compile and Run:**
   * Via IDE (Recommended): Open the project in your preferred Java IDE (such as VS Code, IntelliJ, or Eclipse), locate mainGame.java, and run it. The City Engine dependency is configured automatically.

