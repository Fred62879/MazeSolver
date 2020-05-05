# Maze Solver GUI
This is the project directory for the Maze Solver GUI.

## Getting Started

### Prerequisites
To run this program, only JDK is required and an IDE (e.g. Intellij) is suggested but not a requisite.

### Installing
The program operates with no problem on java-1.8.0-amazon-corretto. If you run into troubles on other versions, please download this JDK from [here](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html). For basic JDK setup, please refer [here](https://www.jetbrains.com/help/idea/sdk.html)

### Operating
The Main.java file under src/main/ui directory is what should be run. Once the GUI is launched, you can choose to retrieve previously stored maze representation or enter new ones. Note that Maze is abstracted into 2D matrices, with path being represented as 1 and walls as 0.

To enter a new maze, first specify the size of the maze by filling ''' Row''' with height of maze and '''Column''' with width of the maze. Then, input the 0s and 1s in the input box below with each 0 and 1 separated by a space. If you enter anything other than 0 or 1, the GUI will require a new input.

Once you load the maze, please select the algorithm you want to use to solve the maze. Currently available algorithm options are Depth-First-Search (DFS) and Breadth-First-Search (BFS).

The program will return a random path if more than one path exist and path route is represented as '''*'''