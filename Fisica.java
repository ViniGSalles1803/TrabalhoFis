import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  //Aluno: Vinícius Garcia de Salles
public class ExploradorPolarGUI extends JFrame {
    private JTextField distanciaField, direcaoField;
    private JButton calcularButton;
    private JLabel resultadoLabel;

    public ExploradorPolarGUI() {
        setTitle("Explorador Polar");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        distanciaField = new JTextField();
        direcaoField = new JTextField();
        calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double distancia = Double.parseDouble(distanciaField.getText());
                double direcao = Double.parseDouble(direcaoField.getText());
                calcularCaminho(distancia, direcao);
            }
        });
        resultadoLabel = new JLabel();

        panel.add(new JLabel("Distância caminhada (km):"));
        panel.add(distanciaField);
        panel.add(new JLabel("Direção caminhada (graus ao norte do leste):"));
        panel.add(direcaoField);
        panel.add(calcularButton);

        add(panel, BorderLayout.CENTER);
        add(resultadoLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void calcularCaminho(double distancia, double direcao) {
    
        double direcaoRadianos = Math.toRadians(direcao);

        double distanciaHorizontal = distancia * Math.cos(direcaoRadianos);
        double distanciaVertical = distancia * Math.sin(direcaoRadianos);

        double distanciaRetorno = Math.sqrt(distanciaHorizontal * distanciaHorizontal + distanciaVertical * distanciaVertical);
        double direcaoRetorno = Math.toDegrees(Math.atan2(distanciaVertical, distanciaHorizontal));

        direcaoRetorno = (direcaoRetorno + 360) % 360;

        resultadoLabel.setText(String.format("Distância de retorno: %.2f km\nDireção de retorno: %.2f graus ao norte do leste", distanciaRetorno, direcaoRetorno));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExploradorPolarGUI());
    }
}
