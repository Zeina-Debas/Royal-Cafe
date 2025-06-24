package com.mycompany.royal_cafe;

import java.awt.FlowLayout;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Royal_Cafe extends JFrame {

    JLabel Label, totalLabel;
    JTextField textfiels;
    JPanel Panelfood, PanelDrinks, PanelEast, PanelNorth, PanelSouth, PanelWest;
    JTabbedPane TabbedPane = new JTabbedPane();
    JTextArea orderTextArea;

    Royal_Cafe() {
        setVisible(true);
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        InitializationPanelfood();
        InitializationPanelDrinks();
        InitializationPanelEast();
        InitializationPanelNorth();
        InitializationPanelSouth();
//

        add(TabbedPane, BorderLayout.CENTER);
        add(PanelEast, BorderLayout.EAST);
        add(PanelNorth, BorderLayout.NORTH);
        add(PanelSouth, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new Royal_Cafe();
        URL url = Royal_Cafe.class.getResource("/images/coffee.jpg");
        System.out.println("URL = " + url);
    }

    private void InitializationPanelfood() {
        String[] name = {
            "strawberry cake", "fresh-fruit-cake", "Rainbow-Cake-Feature",
            "Vegetable-Pizza", "Chicken Burgers", "chicken-and-noodles",};
        String[] imageNames = {
               "strawberry cake.jpg", "fresh-fruit-cake.jpg", "Rainbow-Cake-Feature.jpg",
             "Vegetable-Pizza.jpg", "Chicken Burgers.jpg", "chicken-and-noodles.jpg", "7 up.jpg", "orange juice.jpg"
        };

        String price[] = {
            "30.0", "200.0", "45.0", "60.0", "66.0", "90.0"
        };
        Panelfood = new JPanel();
        Panelfood.setPreferredSize(new Dimension(300, 400));
        Panelfood.setBackground(new Color(232, 242, 251));
        Panelfood.setLayout(new GridLayout(2, 3));
        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(239, 240, 242));
            panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.setLayout(new BorderLayout());

//            System.out.println("/images/" + imageNames[i]);
//            System.out.println(getClass().getResource("/images/" + imageNames[i]));
            //تحميل الصوره
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + imageNames[i]));
            Image img = icon.getImage().getScaledInstance(250, 130, Image.SCALE_AREA_AVERAGING);
            JLabel label = new JLabel(new ImageIcon(img));
            JLabel nameLabel = new JLabel(name[i], JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            // add label price
            JLabel labelprice = new JLabel("PRICE" + " " + price[i]);
            labelprice.setFont(new Font("Arial", Font.BOLD, 14));
            //add spinner

            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 5, 1);

            JSpinner spinner = new JSpinner(model);

            spinner.setPreferredSize(new Dimension(40, 10));
            spinner.setFont(new Font("Arial", Font.BOLD, 22));
            //add JCeckBox
            JCheckBox checkbox = new JCheckBox("purchase");
            checkbox.addActionListener(e -> {
                if (checkbox.isSelected()) {
                    spinner.setValue(1);
                } else {
                    spinner.setValue(0);
                }
                updateOrderTextArea();
            });

            spinner.addChangeListener(e -> {
                int val = (Integer) spinner.getValue();
                if (val == 0) {
                    checkbox.setSelected(false);
                } else {
                    checkbox.setSelected(true);
                }
                updateOrderTextArea();
            });
            panel.add(checkbox, BorderLayout.SOUTH);
            panel.add(labelprice, BorderLayout.WEST);
            panel.add(label, BorderLayout.NORTH);
            panel.add(nameLabel, BorderLayout.CENTER);
            panel.add(spinner, BorderLayout.EAST);
            Panelfood.add(panel);

        }
        TabbedPane.addTab("Food Section", Panelfood);
    }

    
private void InitializationPanelEast() {
    PanelEast = new JPanel();
    PanelEast.setLayout(new BorderLayout());
    PanelEast.setPreferredSize(new Dimension(250, 400));
    PanelEast.setBackground(new Color(240, 251, 242));

    orderTextArea = new JTextArea("Royal cafe\n");
    orderTextArea.setEditable(false);
    orderTextArea.setLineWrap(true);
    orderTextArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(orderTextArea);
    PanelEast.add(scrollPane, BorderLayout.CENTER);

      orderTextArea.setFont(new Font("Arial", Font.BOLD, 15));
    // زر لحساب المجموع
    JButton totalButton = new JButton("عرض المجموع");
    totalButton.setFont(new Font("Arial", Font.BOLD, 14));
    totalButton.setBackground(new Color(204, 255, 204));

    // Label لعرض المجموع
    totalLabel = new JLabel("Total: 0.0 ₪");
    totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalLabel.setHorizontalAlignment(JLabel.CENTER);
    totalLabel.setForeground(Color.BLUE);

    // إضافة حدث الزر لتحديث الليبل
   
   totalButton.addActionListener(e -> {
    showTotal(); // هذه تطبع الطلب مع المجموع في الـ JTextArea
    double total = calculateTotal();
    totalLabel.setText("Total: " + total + " ₪");
});

    // لوحة للأزرار والليبل تستخدم BoxLayout عمودي
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(240, 251, 242));
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    // محاذاة الوسط للزر والليبل
    totalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    buttonPanel.add(totalButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // مسافة بين الزر والليبل
    buttonPanel.add(totalLabel);

    PanelEast.add(buttonPanel, BorderLayout.SOUTH);
}

    private void InitializationPanelNorth() {
        JLabel label = new JLabel("Menu Items", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(80, 25, 200, 50);
        PanelNorth = new JPanel();
        PanelNorth.setPreferredSize(new Dimension(300, 100));
        PanelNorth.setBackground(new Color(252, 240, 250));
        PanelNorth.setLayout(null);
        PanelNorth.add(label);
    }

    private void InitializationPanelSouth() {
        PanelSouth = new JPanel();
        PanelSouth.setPreferredSize(new Dimension(300, 100));
        PanelSouth.setBackground(new Color(252, 242, 232));

        PanelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton foodButton = new JButton("Food section");
        foodButton.setFont(new Font("Arial", Font.BOLD, 14));
        foodButton.setBackground(new Color(255, 204, 204));
        foodButton.addActionListener(e -> {
            TabbedPane.setSelectedIndex(0);
        });

        JButton drinksButton = new JButton("Drink section");
        drinksButton.setFont(new Font("Arial", Font.BOLD, 14));
        drinksButton.setBackground(new Color(204, 229, 255));
        drinksButton.addActionListener(e -> {
            TabbedPane.setSelectedIndex(1); // التبويب الثاني
        });
        PanelSouth.add(foodButton);
        PanelSouth.add(drinksButton);
    }

    private void InitializationPanelDrinks() {
        PanelDrinks = new JPanel();
        String[] name = {
            "coffee", "cappuccino coffee", "chocolate coffee", "cold coffee", "green tea", "mineral water",
            "coca", "7 up", "orange juice"

        };
        String[] imageNames = {
            "coffee.jpg", "cappuccino coffee.jpg", "chocolate coffee.jpg", "cold coffee.jpg", "green tea.jpg", "mineral water.jpg",
            "coca.jpg", "7 up.jpg", "orange juice.jpg"
        };

        String price[] = {
            "53.0", "55.0", "54.0", "52.0", "50.0", "70.0", "80.0", "100.0", "45.0",};

        PanelDrinks.setLayout(new GridLayout(3, 3, 10, 10));
        PanelDrinks.setPreferredSize(new Dimension(300, 400));
        PanelDrinks.setBackground(new Color(232, 242, 251));
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(239, 240, 242));
            panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.setLayout(new BorderLayout());

//            System.out.println("/images/" + imageNames[i]);
//            System.out.println(getClass().getResource("/images/" + imageNames[i]));
            //تحميل الصوره
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + imageNames[i]));
            Image img = icon.getImage().getScaledInstance(250, 130, Image.SCALE_AREA_AVERAGING);
            JLabel label = new JLabel(new ImageIcon(img));
            JLabel nameLabel = new JLabel(name[i], JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            // add label price
            JLabel labelprice = new JLabel("PRICE" + " " + price[i]);
            labelprice.setFont(new Font("Arial", Font.BOLD, 14));
            //add spinner
  //
  
  URL url = getClass().getResource("/images/" + imageNames[i]);
System.out.println("Image URL for " + imageNames[i] + ": " + url);
  //
            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 5, 1);

            JSpinner spinner = new JSpinner(model);

            spinner.setPreferredSize(new Dimension(40, 80));
            spinner.setFont(new Font("Arial", Font.BOLD, 22));
            //add JCeckBox
            JCheckBox checkbox = new JCheckBox("purchase");

            checkbox.addActionListener(e -> {
                if (checkbox.isSelected()) {
                    spinner.setValue(1);
                } else {
                    spinner.setValue(0);
                }
                updateOrderTextArea();
            });

            spinner.addChangeListener(e -> {
                int val = (Integer) spinner.getValue();
                if (val == 0) {
                    checkbox.setSelected(false);
                } else {
                    checkbox.setSelected(true);
                }
                updateOrderTextArea();
            });

            panel.add(checkbox, BorderLayout.SOUTH);
            panel.add(labelprice, BorderLayout.WEST);
            panel.add(label, BorderLayout.NORTH);
            panel.add(nameLabel, BorderLayout.CENTER);
            panel.add(spinner, BorderLayout.EAST);
            PanelDrinks.add(panel);

        }
        TabbedPane.addTab("Drinks Section", PanelDrinks);
    }

    

   private void updateOrderTextArea() {
    // مشابهة للدالة showTotal لكن بدون إعادة حساب المجموع لأن الدالة calculateTotal() موجودة
    StringBuilder sb = new StringBuilder("Royal cafe\n");
    for (Component comp : Panelfood.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel nameLabel = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    } else if (label.getHorizontalAlignment() == JLabel.CENTER) {
                        nameLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && nameLabel != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    sb.append(nameLabel.getText())
                      .append(" x ").append(qty)
                      .append(" * ").append(price)
                      .append(" = ").append(qty * price)
                      .append("\n");
                }
            }
        }
    }
    for (Component comp : PanelDrinks.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel nameLabel = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    } else if (label.getHorizontalAlignment() == JLabel.CENTER) {
                        nameLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && nameLabel != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    sb.append(nameLabel.getText())
                      .append(" x ").append(qty)
                      .append(" * ").append(price)
                      .append(" = ").append(qty * price)
                      .append("\n");
                }
            }
        }
    }
    orderTextArea.setText(sb.toString());
    totalLabel.setText("Total: " + calculateTotal() + " ₪");
}
private void showTotal() {
    double total = calculateTotal();
    StringBuilder sb = new StringBuilder();
    sb.append("Royal cafe\n");

    // تفاصيل الطلب قسم الطعام
    for (Component comp : Panelfood.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel nameLabel = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    } else if (label.getHorizontalAlignment() == JLabel.CENTER) {
                        nameLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && nameLabel != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    sb.append(nameLabel.getText())
                      .append(" x ").append(qty)
                      .append(" * ").append(price)
                      .append(" = ").append(qty * price)
                      .append("\n");
                }
            }
        }
    }
    // تفاصيل الطلب قسم المشروبات
    for (Component comp : PanelDrinks.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel nameLabel = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    } else if (label.getHorizontalAlignment() == JLabel.CENTER) {
                        nameLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && nameLabel != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    sb.append(nameLabel.getText())
                      .append(" x ").append(qty)
                      .append(" * ").append(price)
                      .append(" = ").append(qty * price)
                      .append("\n");
                }
            }
        }
    }
    sb.append("\nTotal: ").append(total).append(" ₪");
    orderTextArea.setText(sb.toString());
    totalLabel.setText("Total: " + total + " ₪");
}

    private double calculateTotal() {
    double total = 0.0;
    // حساب المجموع في قسم الطعام
    for (Component comp : Panelfood.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    total += qty * price;
                }
            }
        }
    }
    // حساب المجموع في قسم المشروبات
    for (Component comp : PanelDrinks.getComponents()) {
        if (comp instanceof JPanel panel) {
            JCheckBox cb = null;
            JSpinner sp = null;
            JLabel priceLabel = null;
            for (Component c : panel.getComponents()) {
                if (c instanceof JCheckBox checkbox) {
                    cb = checkbox;
                } else if (c instanceof JSpinner spinner) {
                    sp = spinner;
                } else if (c instanceof JLabel label) {
                    String text = label.getText();
                    if (text != null && text.startsWith("PRICE")) {
                        priceLabel = label;
                    }
                }
            }
            if (cb != null && sp != null && priceLabel != null) {
                int qty = (Integer) sp.getValue();
                if (cb.isSelected() && qty > 0) {
                    double price = Double.parseDouble(priceLabel.getText().replace("PRICE", "").trim());
                    total += qty * price;
                }
            }
        }
    }
    return total;
}
    
}
