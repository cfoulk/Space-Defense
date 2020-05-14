package com.project.spacedefense;

interface GameEngineBroadcaster {

    // This allows the Player and UI Controller components
    // to add themselves as listeners of the GameEngine class
    void addObserver(InputObserver o);
}