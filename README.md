<h1 align="center">
<br>
<p align="center">
"BattleShips"
</p>
<br>
<br>
</h1>

<p >

## Menu
<img src="images/Menu.png" alt="Screenshot">
 <p>In the Menu Player can choose one of the game modes:</p>
   <p> -Player vs Ai</p>
   <p> -Player vs Player</p>
   <p> -AI vs AI (test of AI)</p>
 <p>or exit game</p>

## Player vs Ai
<p>First player must place his/her ships on the board. It is possible to do using mouse, player can place ships with left click and rotate them using right click</p>
<p>Below board player can see size of his next ship</p>
<p align="center">
    <img src="images/plshipsPVA.png" alt="Screenshot">
</p>
<p>If player is not content with placing of his/her ships,reset ships Placement should be clicked. This action will reset position of all ships </p>
<p>when player placed all of his/her ships it is possible to click Start Game button, and Start the game </p>
<p align="center">
    <img src="images/battlePVA.png" alt="Screenshot">
</p>
<p>On the right Player can see his/her board with ship he/she placed</p>
<p>On the left placed is AI board with hidden ships, place can 'shoot' the opponent ships by left clicking on choosen square</p>
<p>after player move AI will automatically make it own move</p>
<p>game stops after one of sides doesn't have any unsunked ships</p>

## Player vs Player
<p>In this game mode recomended is for players to separate view on screen with some type of obstacle</p>
 <p>like before players first will place their ships </p>
<p align="center">
    <img src="images/plShipPVP.png" alt="Screenshot">
</p>
<p>if both player placed their ships and checked it using checker above boards the game start button will unlock</p>
<p>After checking ready? checkbox if player wants to change his/her ships, it is required to reset ships position, then board ships will be erased and board will unlock</p>
<p align="center">
    <img src="images/battlePVP.png" alt="Screenshot">
</p>
<p>Now both players can see their opponent board(big board), and their own board(small board at the bottom) </p>
<p> the green circle in the corners of window signals current shooter</p>
<p>players must do move after each other, starting with player 1</p>
<p>as in traditional game players are required to inform their opponents if one of their own ships were sunked</p>
<p>game stops after one of sides sunk all of enemy's ships</p>

## AI vs AI

<p>In this game mode the only thing observer can do is enabling next move of AI's</p>
<p align="center">
    <img src="images/AIBattle.png" alt="Screenshot">
</p>

<p>game stops after one of sides doesn't have any unsunken ships</p>

## Few Things about AI-Opponent

<h3 align="center">
    AI ships placement
</h3>
<p>AI loading new coordinates for ships placement from files named scheme'number'.txt for example scheme1.txt</p>
<p>in every file are different position of ships, AI draws one of theme and place ships accordingly</p>
<p>Those files look like this:</p>
00 01 <br>
03 04<br>
06 07<br>
09 19<br>
20 21 22<br>
24 25 26<br>
39 49 59<br>
40 50 60 70<br>
42 52 62 72<br>
44 54 64 74 84 94<br>
<p>every pair of numbers represents coordinates for one part of the ship, depending on how many pairs are in line we know how big the ship is. </p>
<p>Clss FileValidator checks if those coordinates are right before placing ships on the board</p>
<h3 align="center">
    How AI Shoot
</h3>
<p>this process can be summarised like that </p>
<p>1.get random position on board(*check if ai can shoot there), and shoot </p>
<p>2.if Ai hitted player ship choose one of four directions and shoot the closest squre in that direction </p>
<p>3.if shot missed return to 2, but if ship was hitted keep shooting in that direction</p>
<p>4.if shot missed and enemy ship isn't sunked get back to first hit on this ship and continue step 2(in opposite direction)</p>
<p>if ship finally sunk go to step 1</p>
<p>*Ai checks if place choosen to be shooted hasn't been already picked before</p>

## Used Software
<p>Java: sdk 17</p>
<p>javafx:17.0.1</p>

## How it was made
<p>First we wanted to crate this project in console as it was minimum requirement, but after ending it quite early, with a lot of time to spare we have reworked to include GUI</p>
