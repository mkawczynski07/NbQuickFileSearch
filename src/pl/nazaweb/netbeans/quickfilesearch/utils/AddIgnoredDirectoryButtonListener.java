/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nazaweb.netbeans.quickfilesearch.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 *
 * @author Jonasz
 */
public class AddIgnoredDirectoryButtonListener implements ActionListener {

    DefaultListModel model;
    JTextField textField;

    public AddIgnoredDirectoryButtonListener(DefaultListModel model, JTextField textField) {
        this.model = model;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = textField.getText();
        List<String> elements = new ArrayList<String>();
        int listSize = model.size();
        for (int i = 0; i < listSize; i++) {
            elements.add((String) model.getElementAt(i));
        }
        if (!elements.isEmpty()) {
            for (String element : elements) {
                if (element.equals(inputText)) {
                    System.out.println("Already exist");
                } else {
                    model.addElement(inputText);
                }
            }

        } else {
            model.addElement(inputText);
        }

    }

}
