package fjr.main.rutte.traizer;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Rutte extends Application {

	static String main = Rutte.class.getResource("mami.jpg").toExternalForm();
	int step = 5;
	int stepY = 50; 
	double depth = 70;
	double width = 0 ;
	double  height = 0; 

	@Override
	public void start(Stage primaryStage) throws Exception {
		final Group ensembel = getEsembel();
		ensembel.setRotationAxis(Rotate.Z_AXIS); 
		ensembel.setTranslateX(50); 
		final Slider slider1 = SliderBuilder.create()
				.translateX(10).translateY(10).orientation(Orientation.VERTICAL).prefHeight(70).prefWidth(20).value(50)
				.build(); 
		slider1.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				// TODO Auto-generated method stub
			double vv = slider1.getValue(); 
				if(vv > 50) {
					ensembel.setRotate(-(slider1.getValue() - 50) *180/ 100); 
				}else{
					ensembel.setRotate((- slider1.getValue()+50) *180/ 100); 
				}
			}
		});
		Group root = new Group(); 
		root.getChildren().addAll(ensembel, slider1);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("TUT WURI HANDAYANI");
		primaryStage.show();
	}

	public Group getEsembel() {
		Group root = new Group();
		Image image = new Image(main);
		width = image.getWidth();
		height = image.getHeight();

		PixelReader pixel = image.getPixelReader();

		double originX = 0;
		double destinyX = 0;
		double originY = 100;
		double destinyY = 0.0;

		Canvas canvas = new Canvas(width+50,height+50); 
		GraphicsContext gc = canvas.getGraphicsContext2D(); 
		
		double shifty  = 0; 
		double rotate = 0 ;
		for(int y = 0 ; y < height; y+= step){
			rotateX(gc, -45) ; 
			for(int x = 0; x < width-step ;x+= step){
				Color color = pixel.getColor(x, y);
				Color color1 = pixel.getColor(x+step, y);
				double brightness = color.getBrightness();
				double brightness1 = color1.getBrightness(); 
				
				originX = x ; 
				destinyX = x+step; 
				originY = - brightness*depth+brightness/2  + shifty;
				destinyY = - brightness1*depth+brightness1/2  + shifty;
				gc.setStroke(color);
				gc.strokeLine(originX,originY, destinyX, destinyY );
				originX  = destinyX ; 
				originY = destinyY; 
			}
			shifty+= 5;
//			rotate-=15; 
		}
	
		root.setTranslateX(20); 
		root.setTranslateY(20);
		root.getChildren().add(canvas);
		
		return root;
	}
	
	/*
	 * I'm not found the way to rotate GraphicContext with simple way like other node
	 */
	 void rotateX(GraphicsContext gc , double angle){
		Affine affine = new Affine(); 
		double angleRadi = Math.toRadians(angle); 
		double sinus = Math.sin(angleRadi);
		double cosinus = Math.cos(angleRadi);
		affine.setMxx(1); 
		affine.setMxy(0); 
		affine.setMxz(0); 
		affine.setTx(0); 
		affine.setMyx(0); 
		affine.setMyy(cosinus);
		affine.setMyz(-sinus); 
		affine.setTy(0); 
		affine.setMzx(0); 
		affine.setMzy(sinus);
		affine.setMzz(cosinus);
		affine.setTz(0); 
		
		gc.setTransform(affine);
		
	}
	
	void rotateY(GraphicsContext gc , double angle){
		Affine affine = new Affine(); 
		double angleRadi = Math.toRadians(angle); 
		double sinus = Math.sin(angleRadi);
		double cosinus = Math.cos(angleRadi);
		affine.setMxx(cosinus); 
		affine.setMxy(0); 
		affine.setMxz(sinus); 
		affine.setTx(0); 
		affine.setMyx(0); 
		affine.setMyy(1);
		affine.setMyz(0); 
		affine.setTy(0); 
		affine.setMzx(-sinus); 
		affine.setMzy(0);
		affine.setMzz(-cosinus);
		affine.setTz(0); 
		
		gc.setTransform(affine);
		
	}
	
	 void rotateZ(GraphicsContext gc , double angle){
		Affine affine = new Affine(); 
		double angleRadi = Math.toRadians(angle); 
		double sinus = Math.sin(angleRadi);
		double cosinus = Math.cos(angleRadi);
		affine.setMxx(cosinus); 
		affine.setMxy(-sinus); 
		affine.setMxz(0); 
		affine.setTx(0); 
		affine.setMyx(sinus); 
		affine.setMyy(cosinus);
		affine.setMyz(0); 
		affine.setTy(0); 
		affine.setMzx(0); 
		affine.setMzy(0);
		affine.setMzz(1);
		affine.setTz(0); 
		
		gc.setTransform(affine);
		
	}

	public Group getLine(double y) {
		Group root = new Group();

		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
