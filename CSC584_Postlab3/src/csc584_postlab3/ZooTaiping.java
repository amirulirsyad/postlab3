/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc584_postlab3;

import javax.swing.JOptionPane;

/**
 *
 * @author hakimchi
 */
public abstract class ZooTaiping {
    private String visitorName;
    private String icNumber;
    private boolean govServant;

    public ZooTaiping(String visitorName, String icNumber, boolean govServant) {
        this.visitorName = visitorName;
        this.icNumber = icNumber;
        this.govServant = govServant;
    }
    
    public abstract double totalCharges();

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public boolean isGovServant() {
        return govServant;
    }

    public void setGovServant(boolean govServant) {
        this.govServant = govServant;
    }
    
    @Override
    public String toString() {
        return "ZooTaiping{" + "visitorName=" + visitorName + ", icNumber=" + icNumber + ", govServant=" + govServant + '}';
    }
}

class DayVisit extends ZooTaiping {
    private String category; // child or adult

    public DayVisit(String category, String visitorName, String icNumber, boolean govServant) {
        super(visitorName, icNumber, govServant);
        this.category = category;
    }
    
    public double totalCharges() {
        double price = 0.0;
        
        if (category.equals("Adult")) {
            price = 25;
        } else if (category.equals("Child")) {
            price = 15;
        }
        
        if (super.isGovServant()) {
            price = price * 0.85;
        }
        
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DayVisit{" + "category=" + category + '}';
    }
}

class NightVisit extends ZooTaiping {
    private char Package; //the visitor choose package A/B/C

    public NightVisit(char Package, String visitorName, String icNumber, boolean govServant) {
        super(visitorName, icNumber, govServant);
        this.Package = Package;
    }
    
    public double totalCharges() {
        double price = 0.0;
        
        switch(Package) {
            case 'A':
                price = 50;
                break;
            case 'B':
                price = 75;
                break;
            case 'C':
                price = 100;
                break;
        }
        
        if (super.isGovServant()) {
            price = price * 0.85;
        }
        
        return price;
    }

    public char getPackage() {
        return Package;
    }

    public void setPackage(char Package) {
        this.Package = Package;
    }
    
    @Override
    public String toString() {
        return "NightVisit{" + "Package=" + Package + '}';
    }    
}

class Main {
    public static void main(String[] args) {
        //String numberOfData = JOptionPane.showInputDialog("Ticket for how many: ");
        int arraySize = 5;
        ZooTaiping[] cus = new ZooTaiping[arraySize];
        
        int countDay = 0, countNight = 0;
        double totalAll = 0;
        String packageCVisitorDetails = "\nPackage C Purchaser:\n";
        int countPackageC = 1;
        
        for (int i = 0; i < arraySize; i++) {
            String name = JOptionPane.showInputDialog("Enter your name: ");
            String ic = JOptionPane.showInputDialog("Enter your IC: ");
            String govt = JOptionPane.showInputDialog("Are you a government servent? (Yes/No): ");
            String timeVisit = JOptionPane.showInputDialog("Time visit (Day/Night): ");
            
            if (timeVisit.equals("Day")) {
                //String category, String visitorName, String icNumber, boolean govServant
                String category = JOptionPane.showInputDialog("Age Category (Adult/Child): ");
                
                cus[i] = new DayVisit(category, name, ic, Boolean.parseBoolean(govt));
                countDay++;
                totalAll += cus[i].totalCharges();
            } else if (timeVisit.equals("Night")) {
                //char Package, String visitorName, String icNumber, boolean govServant
                String pack = JOptionPane.showInputDialog("Enter package (A/B/C): ");
                cus[i] = new NightVisit(pack.charAt(0), name, ic, Boolean.parseBoolean(govt));
                countNight++;
                totalAll += cus[i].totalCharges();
                if (pack.equals("C")) {
                    packageCVisitorDetails += countPackageC++ + ". \nName: " + cus[i].getVisitorName() + "\nIC: " + cus[i].getIcNumber();
                }
            }
        }
        System.out.println("Day Visitor(s): " + countDay);
        System.out.println("Night Visitor(s): " + countNight);
        System.out.println("Total Income: RM" + totalAll);
        System.out.println(packageCVisitorDetails);
    }
}