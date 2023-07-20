<h1 align="center">Space Tower Defense Game</h1>
  

<p align="center">
  <a href="https://github.com/cfoulk/Space-Defense/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/cfoulk/Space-Defense?style=flat"
         alt="Github license">
    </a>
</p>

<h4 align="center">Space Defense is a space-themed tower defense game created by Charles Foulk and Nicolas King.</h4>

<p align="center">
  <a href="#key-features">Key Features</a> •
  <a href="#tower-breakdown">Tower Breakdown</a> •
  <a href="#enemy-breakdown">Enemy Breakdown</a> •
  <a href="#contact">Contact</a>
</p>

https://github.com/cfoulk/Space-Defense/assets/35832601/64e2fdda-0520-4482-a8cd-80f9abff7012

## <a name="key-features">Key Features</a>
* User controlled tower placement
  - Click/touch an area to place a tower for 200 points with place a Destruction tower(green)
  - No limit on tower placement as long as the level is in play and you have points
* Waves of enemies spawn in a path toward our base center
  - 3 types of enemies are able to spawn (Small octopus, Big octopus, and Boss)
  - If enemy reaches our base, the base takes 50 HP damage and the enemy dies
  - If a boss enemy reaches the base, 200 HP damage is taken
* Towers target and fire projectiles at enemies
  - 3 types of towers (Fire tower, Ice tower, and Destruction tower)
  - Each tower has a unique values of reload rate, and damage done to target
  - Ice tower projectiles permanently slows the target

## <a name="tower-breakdown">Tower Breakdown</a>

There are three tower types to Space Defense, those being the Fire Towers, the Ice Tower, and the
(Green) Destruction Towers. Each tower type is an extension of the class Tower which houses most of the main
functionality of each tower such as taking aim at enemy’s, creating projectiles, and the drawing of the towers.
Whereas each of the subclasses take the responsibility of creating their own unique Bitmaps for the superclass
to output and for shooting so each tower can properly output their respective projectiles. In the case of the ice
Projectile class, it has a unique feature that on collision with its target, permanently slows that target. The other
differences that the classes have is shooting speed and damage done with their projectiles. Since the ice
projectile does its powerful slowing ability, it does not do much damage in comparison with other projectiles.
The fire projectile is middle of the road with shooting speed and damage done, but it is the destruction projectile
that does the most damage, but its downside is that the speed at which it fires is very slow.
The tower gets their target based of the list of enemies created by the EnemyWave class, and then finds
the closest one.
Here is a breakdown the attributes of each tower type.

<table>
  <tr>
    <th>Tower Type</th>
    <th>Color</th>
    <th>Damage Per Projectile</th>
    <th>Reload Rate</th>
  </tr>
  <tr>
    <th>Fire Tower</th>
    <th>Red</th>
    <th>15</th>
    <th>2 seconds</th>
  </tr>
  <tr>
    <th>Ice Tower</th>
    <th>Blue</th>
    <th>5</th>
    <th>1.5 seconds</th>
  </tr>
  <tr>
    <th>Dstruction Tower</th>
    <th>Green</th>
    <th>40</th>
    <th>6 seconds</th>
  </tr>
</table>

![redtower](https://github.com/cfoulk/Space-Defense/assets/35832601/ad2814c8-3520-4993-b68a-c88f4cdc8d4a)
![bluetower](https://github.com/cfoulk/Space-Defense/assets/35832601/0677de55-3f07-4900-bfb8-646aff050b41)
![greentower](https://github.com/cfoulk/Space-Defense/assets/35832601/c9b85235-ce05-4325-bd55-5abca92ad01f)


## <a name="enemy-breakdown">Enemy Breakdown</a>

There are also three enemy types. The first and least intimidating one is the small octopus. The small
octopus is the first enemies to be spawn. The purpose of them is to prepare the user to for the later enemies by
allow the user to accumulate Score so they could properly destroy the bigger and more dreadful enemies. The
way I have created the Enemy waves was the have the same enemy types spawn with each other, to lead to the
end ‘boss’. When each enemy collides with the base at the end of the path, the enemy kills itself but leaves the
tower 50 hp lower. The only exception to this is the boss enemy which deals 200 damage to the base.

<table>
  <tr>
    <th>Enemy Type</th>
    <th>Health</th>
    <th>Wave</th>
  </tr>
  <tr>
    <th>Small Octopus</th>
    <th>60</th>
    <th>1-20</th>
  </tr>
  <tr>
    <th>Big Octopus</th>
    <th>75</th>
    <th>20-29</th>
  </tr>
  <tr>
    <th>Boss</th>
    <th>150</th>
    <th>30</th>
  </tr>
</table>

![alien_octo](https://github.com/cfoulk/Space-Defense/assets/35832601/4c9c5eb7-923e-4dff-9763-0684e172787e)
![bigocto](https://github.com/cfoulk/Space-Defense/assets/35832601/70d014a8-b6ed-4fd7-a29f-c6cb62e62247)
![bossalien](https://github.com/cfoulk/Space-Defense/assets/35832601/caac0a3b-4718-4adf-8de5-baa92642b0d7)

  
## <a name="contact">Contact</a>

**Email:** [charlesfoulk11@gmail.com](mailto:charlesfoulk11@gmail.com)

**Github:** [@cfoulk](https://github.com/cfoulk)
