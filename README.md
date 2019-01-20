# Tennis

## About this Kata
This Kata is about implementing a simple tennis game. I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game.

The scoring system is rather simple:
- Each player can have either of these points in one game 0 15 30 40
- If you have 40 and you win the ball you win the game, however there are special rules.
- If both have 40 the players are deuce. a. If the game is in deuce, the winner of a ball will have advantage and game ball. b. If the player with advantage wins the ball he wins the game c. If the player without advantage wins they are back at deuce.

## Used technologies and principles
- Test-driven development
- Java 8 (with emphasis on the usage of functional interfaces)
- JUnit 5
- Maven 3
- Lombok 1.18.4
- IntelliJ IDEA 2018.3

## Clone repository
```
git clone https://github.com/jjaros/kata-tennis-functional-java
```

## Example solutions
- http://github.com/follesoe/TennisKataJava Example solution in Java from Trondheim Coding Dojo
- http://github.com/goeran/Katas/tree/master/Tennis/csharp/2ndTry/ Example solution in .NET
- https://github.com/lroal/Roald/tree/master/src/Roald.Katas Example solution in .NET with state transition tree
- https://github.com/keithn/vsvimguide/blob/master/Examples/Kata.Tennis/TennisScoring.cs Simple one file example in C# .NET Core

## References
- [codingdojo.org/kata/Tennis/](http://codingdojo.org/kata/Tennis/)