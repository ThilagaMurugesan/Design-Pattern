/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package designpatterns;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface StockObserver {
    void update(String stockSymbol, double stockPrice);
}

// Concrete Observer
class Investor implements StockObserver {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        System.out.println("Investor " + name + " notified. " + stockSymbol + " is now " + stockPrice);
    }
}

// Subject
class Stock {
    private List<StockObserver> observers = new ArrayList<>();
    private String stockSymbol;
    private double stockPrice;

    public Stock(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }
}

// Client
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Stock googleStock = new Stock("GOOG");
        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        googleStock.addObserver(investor1);
        googleStock.addObserver(investor2);

        googleStock.setStockPrice(1350.00);
        googleStock.setStockPrice(1400.00);
    }
}
