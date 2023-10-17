package es.yorman.dad.calculadora;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label operador = new Label();
		Label operador2 = new Label();
		Label operador3 = new Label();
		
		operador2.textProperty().bind(operador.textProperty());
		operador3.textProperty().bind(operador.textProperty());
		
		ComboBox<String> operadorCombo = new ComboBox<>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");
		
		VBox operator = new VBox(5, operadorCombo);
		operator.setAlignment(Pos.CENTER);
		operator.setFillWidth(false);

		TextField aF = new TextField();
		aF.setPrefColumnCount(3);

		TextField bF = new TextField();
		bF.setPrefColumnCount(3);

		HBox fila1Input = new HBox(5, aF, operador, bF, new Label("i"));
		fila1Input.setAlignment(Pos.CENTER);
		fila1Input.setFillHeight(false);

		TextField cF = new TextField();
		cF.setPrefColumnCount(3);

		TextField dF = new TextField();
		dF.setPrefColumnCount(3);

		HBox fila2Input = new HBox(5, cF, operador2, dF, new Label("i"));
		fila2Input.setAlignment(Pos.CENTER);
		fila2Input.setFillHeight(false);

		TextField r1F = new TextField();
		r1F.setPrefColumnCount(3);
		r1F.setDisable(true);

		TextField r2F = new TextField();
		r2F.setPrefColumnCount(3);
		r2F.setDisable(true);

		HBox fila3Result = new HBox(5, r1F, operador3, r2F, new Label("i"));
		fila3Result.setAlignment(Pos.CENTER);
		fila3Result.setFillHeight(false);

		VBox inputs = new VBox(5, fila1Input, fila2Input, new Separator(), fila3Result);
		inputs.setAlignment(Pos.CENTER);

		HBox root = new HBox(5, operator, inputs);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora");
		primaryStage.setScene(scene);
		primaryStage.show();

		// Bindings
		Complejo complejo1 = new Complejo();
		Complejo complejo2 = new Complejo();
		Complejo result = new Complejo();

		aF.textProperty().bindBidirectional(complejo1.realProperty(), new NumberStringConverter());
		bF.textProperty().bindBidirectional(complejo1.imaginarioProperty(), new NumberStringConverter());
		cF.textProperty().bindBidirectional(complejo2.realProperty(), new NumberStringConverter());
		dF.textProperty().bindBidirectional(complejo2.imaginarioProperty(), new NumberStringConverter());
		
		r1F.textProperty().bind(result.realProperty().asString());
		r2F.textProperty().bind(result.imaginarioProperty().asString());

		// Listeners
		operadorCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
		operador.textProperty().set(operadorCombo.getValue());
		switch (operadorCombo.getValue()) {
			case "+":
				result.realProperty().bind(complejo1.realProperty().add(complejo2.realProperty()));
				result.imaginarioProperty().bind(complejo1.imaginarioProperty().add(complejo2.imaginarioProperty()));
				break;
			case "-":
				result.realProperty().bind(complejo1.realProperty().subtract(complejo2.realProperty()));
				result.imaginarioProperty().bind(complejo1.imaginarioProperty().subtract(complejo2.imaginarioProperty()));
				break;
			case "*":
				result.realProperty().bind(complejo1.realProperty().multiply(complejo2.realProperty()).subtract(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())));
				result.imaginarioProperty().bind(complejo1.realProperty().multiply(complejo2.imaginarioProperty()).add(complejo1.imaginarioProperty().multiply(complejo2.realProperty())));
				break;
			case "/":
				result.realProperty().bind(complejo1.realProperty().multiply(complejo2.realProperty()).add(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())).divide(complejo2.realProperty().multiply(complejo2.realProperty()).add(complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty()))));
				result.imaginarioProperty().bind(complejo1.imaginarioProperty().multiply(complejo2.realProperty()).subtract(complejo1.realProperty().multiply(complejo2.imaginarioProperty())).divide(complejo2.realProperty().multiply(complejo2.realProperty()).add(complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty()))));
				break;
			}
		});

		operadorCombo.getSelectionModel().selectFirst();
	}

}
