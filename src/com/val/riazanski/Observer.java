package com.val.riazanski;

// слушатель
interface Observer {
    void notification(String message);
}

// наблюдатель
interface Observable {
    void registerObserver(Observer o);
    void notifyObservers(String message);
}
