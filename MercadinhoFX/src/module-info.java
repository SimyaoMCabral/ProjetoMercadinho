module MercadinhoFX {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;// tem que ter ele, para fazer o argumento do projeto.
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;// para dar autorização ao controller usar os componentes, por isso foi adicionado "requires javafx.fxml;"
	opens model to javafx.base,javafx.graphics,javafx.fxml;// isso para funcionar o envio de informação da tabel
}
