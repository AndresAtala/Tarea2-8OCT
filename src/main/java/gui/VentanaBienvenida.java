package gui;

import Package.CafeController;
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;

import javax.swing.*;

public class VentanaBienvenida {
    public static void main(String[] args) {
        String nombreBD = "BD";
        String usuario = "root";
        String password = "";
        DSLContext query = DBConnector.createDSLContext(nombreBD, usuario, password);
        DBGenerator.iniciarBD(nombreBD);

        CafeController cafeController = new CafeController(query);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bienvenido a la Cafetería");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton botonIngresarCafeteria = new JButton("Ingresar a la cafetería");
            JButton botonSalir = new JButton("Salir");

            botonIngresarCafeteria.addActionListener(e -> {
                VentanaCafeteria ventanaCafeteria = new VentanaCafeteria(cafeController, query);
                ventanaCafeteria.mostrar();
            });

            botonSalir.addActionListener(e -> System.exit(0));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(botonIngresarCafeteria);
            panel.add(botonSalir);

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
