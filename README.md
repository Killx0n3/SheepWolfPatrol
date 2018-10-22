# Sheep and Wolf - Patrol Strategy Pattern
![Java](https://img.shields.io/badge/Language-Java-orange.svg)
## Intro
This project uses Strategy Pattern to replicate a simple game, let's call it <b>Sheep and Wolf</b>. There are two main types of characters, Sheep and Wolf (and one sub-character, Tiger). I have created a strategy pattern. Each character will have a "sight radius" and so they might not see the other characters unless they are close enough.  The behaviour of the character will then depend on exactly what other characters it can see.

## Feature One
Each character (besides the shepherd who is you and can see the whole field) can see 5 squares away in any direction (this includes diagonals). The sheep and wolf will move randomly when they can't see anyone else.  A sheep moves towards the shepherd if it can see.  The wolf always moves randomly, even it if sees the sheep/shepherd.

## Feature Two
While it can't see the sheep, the wolf will "patrol" the field. It will move left until it hits the edge of the field, then move right until it reaches the other edge, ad nauseum. When the wolf can see the sheep it moves directly towards it.

## Feature Three
Tiger roams around randomly but if it sees Shepherd in 5 squares range, then it starts chasing the shepherd, but this tiger gets scared easily. If shepherd starts running towards the tiger, it runs away, the tiger thinks that shepherd is going to kill/beat him, but if shepherd does not do anything or runs in the opposite direction of the tiger or tiger reaches the boundary, then tiger starts chasing shepherd again.

## Project
Strategy Pattern is one of the best algorithms. This project shows how strategy pattern can be really useful in a situation like this. We are using characters’ behaviours to to determine what characters should do. By using strategy pattern, we are using all behaviours’ algorithm interchangeably. Strategy Pattern is mostly used to create an interchangeable family of algorithms from which the required process is chosen at run-time. There are undesirable interactions between patterns.


## About Me
I am TanVir Hossain. I am a Software Engineer.
Originally this project was done by me in October, 2016. 
