# net.bestofcode.MovingPoint.MovingPoint
Java Game Engine built by maste150hhu

## Important Disclaimer
### As you can see the project has reached some size, but is
### missing all commits from before since I moved it here.


net.bestofcode.MovingPoint.MovingPoint tries to achieve the impossible! A lightweight engine to develop
games, animations or simulations.

After working a while with GMS, Unity I got trouble by the size of
the project files and also of the imported libraries. I wanted to
create some small things and firstly need Windows to run Unity and
GMS and secondly enough Hardware.

This lead me to the development of net.bestofcode.MovingPoint.MovingPoint. This project has
two important aspects:

## 1 Beginner Friendly / Educational
+ I love programming and I also love teaching it to other
  persons, so the main goal of net.bestofcode.MovingPoint.MovingPoint is to bring new
  motivated talents into programming.
+ A lot of people dream of creating their own games, net.bestofcode.MovingPoint.MovingPoint
  gives them a first impression of Game Design
+ Even childs with basic Java skills could use this to create simple games like TicTacToe or Snake!

## 2 Operating System Independence
+ since net.bestofcode.MovingPoint.MovingPoint is written in pure Java, you can run it
  on any Operating System

## 3 Hardware Independence
+ net.bestofcode.MovingPoint.MovingPoint is lightweight, you do not need the latest
  hardware to build your games.
+ You also do not need to import all modules, only use the
  code which you need to build your project.

## 4 Use all Java libraries!
+ Yes it is true. You can run any libraries and connect them
  to net.bestofcode.MovingPoint.MovingPoint
+ You are able to create Browsergames by running a Java Webapplication
  and importing net.bestofcode.MovingPoint.MovingPoint.
+ Also usable to create Android Apps by using Android Studio!

## 5 Open source
+ Since I am coding for around 5 years and I tried out many different things,
  this is my first try on writing a Game Engine, so I want everybody to
  help me with the development.
+ You are able to use the Engine as you want to. Modify it, give me tips
  and together we can create a new experience of Game Design.


__________________________
http://www.net.bestofcode.MovingPoint.MovingPoint.net
__________________________
https://www.bestofcode.net
__________________________

# Usage

## 1 Clone this repository into your project.

```
git clone https://github.com/maste150hhu/net.bestofcode.MovingPoint.MovingPoint
```

## 2 Extend the net.bestofcode.MovingPoint.MovingPoint class

```java
public class MyFirstGame extends net.bestofcode.MovingPoint.MovingPoint {

  public static void main(String[] args){

    MyFirstGame game = new MyFirstGame("Title of the game");
  }
}

```

## 3 Run the build.sh in the project's root folder to run net.bestofcode.MovingPoint.MovingPoint with all of its packages.

## 4 Profit! You can now develop your first game.

In the Tutorials folder you will find some Examples on things
which you could try out! Also examples on how to use net.bestofcode.MovingPoint.MovingPoint

!! Examples will be added again during the next weeks !!


# Start your first Game now
[Go to the Tutorials](http://movingpoint.net/tutorials.html)
## Tutorials not online yet.


# TODO
Here you can find some things I am interested in. They will be the next
features which will be added in net.bestofcode.MovingPoint.MovingPoint

* [ ] Add Event-Objects for specific events. Throw a MouseEventObject on click!
!!! + add MovingPointAnalysis to count existing GameObjects, RAM usage, fps, log Events !!!
* [ ] add locationOnScreen method
* [ ] penradius method
* [ ] getPenColor
* [ ] UI Framework for net.bestofcode.MovingPoint.MovingPoint based on Javax.swing
* [x] implement net.bestofcode.MovingPoint.GameObjectQuery
* [x] refactor DrawListener to net.bestofcode.MovingPoint.MovingPointCoreEngine
* [x] refactor Draw to net.bestofcode.MovingPoint.GraphicalComponent
* [x] Transformation to Maven Project
* [x] getJLabel
* [x] implemented first packages
* [x] debug TicTacToe Example
* [x] improve Project-Structure
* [x] fix Grid with border
* [x] fix Grid with border and color


# Most important
+ Build Scripts with more functionality
+ refactor method and variable names
+ Capsule components such as UserInput in their own modules
+ MovingPointStarter as Class which starts the Engine
+ net.bestofcode.MovingPoint.Refactor net.bestofcode.MovingPoint.GameObjectQuery and impllement it as Heap Data Structure with LRU Order
+ @net.bestofcode.MovingPoint.Player Annotation, if the annotation is set, the object with the annotation
  will be used and drawn instead of the net.bestofcode.MovingPoint.MovingPoint object. Otherwise the
  net.bestofcode.MovingPoint.MovingPoint will be drawn.
+ MovingPointUIManager where the net.bestofcode.MovingPoint.GraphicalComponent gets injected
+ improve project structure
+ work on the website:
  articles
  comment-section
  Documentation
+ rotated Images
+ rescaled images
+ rotated text
