package ec.edu.ups.vista;

import javax.swing.*;

public class PreguntasContraseniaView extends JInternalFrame {

    private JPanel PreguntasDeSeguridad;
    private JTextField txtNomMama;
    private JTextField txtNomMascota;
    private JTextField txtCiudadNacimiento;
    private JTextField txtMaestro;
    private JTextField txtAmigoInfancia;
    private JTextField txtPeliFavorita;
    private JTextField txtPrimerLibro;
    private JTextField txtBicicleta;
    private JTextField txtPrimerJefe;
    private JTextField txtComidaFav;
    private JButton btnGuardarDatos;

    public PreguntasContraseniaView() {
        setContentPane(PreguntasDeSeguridad);
        setTitle("Preguntas de Seguridad");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
    }

    public JPanel getPreguntasDeSeguridad() {
        return PreguntasDeSeguridad;
    }

    public JTextField getTxtNomMama() {
        return txtNomMama;
    }

    public JTextField getTxtNomMascota() {
        return txtNomMascota;
    }

    public JTextField getTxtCiudadNacimiento() {
        return txtCiudadNacimiento;
    }

    public JTextField getTxtMaestro() {
        return txtMaestro;
    }

    public JTextField getTxtAmigoInfancia() {
        return txtAmigoInfancia;
    }

    public JTextField getTxtPeliFavorita() {
        return txtPeliFavorita;
    }

    public JTextField getTxtPrimerLibro() {
        return txtPrimerLibro;
    }

    public JTextField getTxtBicicleta() {
        return txtBicicleta;
    }

    public JTextField getTxtPrimerJefe() {
        return txtPrimerJefe;
    }

    public JTextField getTxtComidaFav() {
        return txtComidaFav;
    }

    public JButton getBtnGuardarDatos() {
        return btnGuardarDatos;
    }

    public void setPreguntasDeSeguridad(JPanel preguntasDeSeguridad) {
        PreguntasDeSeguridad = preguntasDeSeguridad;
    }

    public void setTxtNomMama(JTextField txtNomMama) {
        this.txtNomMama = txtNomMama;
    }

    public void setTxtNomMascota(JTextField txtNomMascota) {
        this.txtNomMascota = txtNomMascota;
    }

    public void setTxtCiudadNacimiento(JTextField txtCiudadNacimiento) {
        this.txtCiudadNacimiento = txtCiudadNacimiento;
    }

    public void setTxtMaestro(JTextField txtMaestro) {
        this.txtMaestro = txtMaestro;
    }

    public void setTxtAmigoInfancia(JTextField txtAmigoInfancia) {
        this.txtAmigoInfancia = txtAmigoInfancia;
    }

    public void setTxtPeliFavorita(JTextField txtPeliFavorita) {
        this.txtPeliFavorita = txtPeliFavorita;
    }

    public void setTxtPrimerLibro(JTextField txtPrimerLibro) {
        this.txtPrimerLibro = txtPrimerLibro;
    }

    public void setTxtBicicleta(JTextField txtBicicleta) {
        this.txtBicicleta = txtBicicleta;
    }

    public void setTxtPrimerJefe(JTextField txtPrimerJefe) {
        this.txtPrimerJefe = txtPrimerJefe;
    }

    public void setTxtComidaFav(JTextField txtComidaFav) {
        this.txtComidaFav = txtComidaFav;
    }

    public void setBtnGuardarDatos(JButton btnGuardarDatos) {
        this.btnGuardarDatos = btnGuardarDatos;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNomMama.setText("");
        txtNomMascota.setText("");
        txtCiudadNacimiento.setText("");
        txtMaestro.setText("");
        txtAmigoInfancia.setText("");
        txtPeliFavorita.setText("");
        txtPrimerLibro.setText("");
        txtBicicleta.setText("");
        txtPrimerJefe.setText("");
        txtComidaFav.setText("");
    }
}
