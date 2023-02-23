package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import com.matejbucic.CashRegisterApp.model.listeners.DrinksListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

@Getter
@Setter
public class DrinksPanel extends JPanel {

    private HashMap<JButton, Drink> drinks;
    private DrinksListener listener;

    public DrinksPanel() {
        setLayout(new GridLayout(0, 2));
        fillDrinks("com.matejbucic.CashRegisterApp.model.drinks.concrete");
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
        for (JButton button : drinks.keySet()) {
            button.addActionListener(e -> {
                listener.addDrink(e);
            });
        }
    }

    private void layoutComponents() {
        // add the drink buttons to the panel
        for (JButton drinkButton : drinks.keySet()) {
            add(drinkButton);
        }
    }


    /**
     * Fills the 'drinks' HashMap with JButtons as keys and Drink instances as values.
     * The method searches for classes in the specified package that extend the Drink abstract class and
     * creates instances of those classes, as well as JButtons for each drink.
     *
     * @param packageName the name of the package to search for drink classes in
     */
    public void fillDrinks(String packageName) {
        // Create a new HashMap to store drinks
        drinks = new HashMap<>();

        // Get the current class loader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Replace package name with path separator
        String path = packageName.replace(".", "/");

        try {
            // Get all resources with given path from class loader
            Enumeration<URL> resources = classLoader.getResources(path);

            // Iterate over all resources
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();

                // Get the directory of the resource
                File directory = new File(resource.getFile());

                // Check if directory exists
                if (directory.exists()) {

                    // Get a list of files in the directory
                    File[] files = directory.listFiles();

                    // Iterate over all files in the directory
                    for (File file : files) {
                        String fileName = file.getName();

                        // Check if file is a class file
                        if (fileName.endsWith(".class")) {
                            String className = fileName.substring(0, fileName.lastIndexOf(".class"));

                            // Load the class and check if it is a subclass of Drink
                            try {
                                Class<?> clazz = Class.forName(packageName + "." + className);
                                if (Drink.class.isAssignableFrom(clazz)) {

                                    // Create a new instance of the drink
                                    Drink drink = (Drink) clazz.getDeclaredConstructor().newInstance();

                                    // Create a new JButton with the drink's name and add an ActionListener
                                    JButton button = new JButton(drink.getClass().getSimpleName());

                                    // Add the JButton and Drink to the HashMap
                                    drinks.put(button, drink);
                                }
                            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                                     InvocationTargetException ex) {
                                // Handle exceptions if class cannot be loaded or instantiated
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            // Handle exceptions if resource cannot be found
            ex.printStackTrace();
        }
    }
}
