package com.Product.GUI.radio_button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JRadioButton;

public class RadioButtonCustom extends JRadioButton {

    public RadioButtonCustom() {
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int diameter = Math.min(getHeight(), getWidth()) - 4;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        
        g2.setColor(Color.WHITE); // Màu trắng cho nền
        g2.fillOval(x, y, diameter, diameter);
        
        if (isSelected()) {
            g2.setColor(Color.BLACK); // Màu đen cho vòng tròn bên trong khi được chọn
            g2.fillOval(x + diameter / 4, y + diameter / 4, diameter / 2, diameter / 2);
        }

        if (isFocusOwner()) {
            g2.setColor(Color.GRAY); // Màu xám cho viền khi được focus
            g2.drawOval(x, y, diameter, diameter);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics grphcs) {
        // Không vẽ viền để tránh hiển thị viền màu xanh dương
    }
}
