module com.example.tp2_lassana_damoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp2_lassana_damoi to javafx.fxml;
    exports com.example.tp2_lassana_damoi;
}