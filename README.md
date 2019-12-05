# MovingPoint
Java Game Engine built by maste150hhu

## Important Disclaimer
### As you can see the project has reached some size, but is
### missing all commits from before since I moved it here.


MovingPoint tries to achieve the impossible! A lightweight engine to develop
games, animations or simulations.

After working a while with GMS, Unity I got trouble by the size of
the project files and also of the imported libraries. I wanted to
create some small things and firstly need Windows to run Unity and
GMS and secondly enough Hardware.

This lead me to the development of MovingPoint. This project has
two important aspects:

## 1 Beginner Friendly / Educational
+ I love programming and I also love teaching it to other
  persons, so the main goal of MovingPoint is to bring new
  motivated talents into programming.
+ A lot of people dream of creating their own games, MovingPoint
  gives them a first impression of Game Design
+ Even childs with basic Java skills could use this to create
+ simple games like TicTacToe or Snake!

## 2 Operating System Independence
+ since MovingPoint is written in pure Java, you can run it
  on any Operating System

## 3 Hardware Independence
+ MovingPoint is lightweight, you do not need the latest 
  hardware to build your games.
+ You also do not need to import all modules, only use the
  code which you need to build your project.

## 4 Use all Java libraries!
+ Yes it is true. You can run any libraries and connect them
  to MovingPoint
+ You are able to create Browsergames by running a Java Webapplication
  and importing MovingPoint.
+ Also usable to create Android Apps by using Android Studio!

## 5 Open source
+ Since I am coding for around 5 years and I tried out many different things,
  this is my first try on writing a Game Engine, so I want everybody to
  help me with the development.
+ You are able to use the Engine as you want to. Modify it, give me tips
  and together we can create a new experience of Game Design.


__________________________
http://www.MovingPoint.net
__________________________
https://www.bestofcode.net
__________________________

# Usage

## 1 Clone this repository into your project.

```
git clone https://github.com/maste150hhu/MovingPoint
```

## 2 Extend the MovingPoint class

```java
public class MyFirstGame extends MovingPoint {

  public static void main(String[] args){

    MyFirstGame game = new MyFirstGame("Title of the game");
  }
}

```

## 3 Run the build.sh in the project's root folder to run MovingPoint with all of its packages.

## 4 Profit! You can now develop your first game.

In the Tutorials folder you will find some Examples on things
which you could try out! Also examples on how to use MovingPoint

!! Examples were build by using an old Version of MovingPoint !!
!!        They have to get updated in a future commit         !!


# Start your first Game now
[Go to the Tutorials](http://movingpoint.net/tutorials.html)
## Tutorials not online yet.


# TODO
Here you can find some things I am interested in. They will be the next
features which will be added in MovingPoint


!!! + add MovingPointAnalysis to count existing GameObjects, RAM usage, fps, log Events !!!
+ add locationOnScreen method
+ penradius method
+ getPenColor

+ UI Framework for MovingPoint based on Javax.swing

# Most important
+ improve project structure
+ work on the website:
  articles
  comment-section
  Documentation
+ rotated Images
+ rescaled images
+ rotated text



# Changelog
+ getJLabel
* implemented first packages
* debug TicTacToe Example
* improve Project-Structure
* fix Grid with border 
* fix Grid with border and color
