package front_end.view_modules.penProperties;

import java.util.ArrayList;
import java.util.List;

import front_end.view_modules.image_color_module.interfaces.IColorModule;
import integration.drawing.LineStyleSpec;
import integration.drawing.PenInformation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class PenPopup implements IPenPopup {

    private BorderPane layout;
    private static final int POPUP_SIZE = 600;
    private static final int SPACING = 10;
    private static final int MAX_PEN_THICKNESS = 30;
    private Button applyButton;
    private Button clearButton;
    private Scene myScene;
    private ColorPicker myColorPicker;
    private HBox closingButtonBox;
    private RadioButton myPenDownButton;
    private RadioButton myPenUpButton;
    private VBox myOrder;
    private String myLineStyle;
    private int myPenThickness;
    private IColorModule myColorModule;
    private ComboBox<Object> paletteColor;
    private boolean myPenUpStatus;
    private PenInformation myInfo;
    private int colorID;
    private TextField myField;
    
    public PenPopup(IColorModule aColorModule){
        myInfo = new PenInformation();
        myPenUpStatus = myInfo.isPenUp();
        myOrder = new VBox(SPACING);
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        myColorModule = aColorModule;
        makeColorPickerRow();
        createEndingButtons();
        penUpOrDown();
        setBorderPane();
        myScene = new Scene(layout);
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    
    private List<Object> penThicknessOptions () {
        List<Object> myList = new ArrayList<Object>();
        for (int i = 1; i < MAX_PEN_THICKNESS; i++) {
            myList.add(i);
        }
        return myList;
    }
    
    private List<Object> makeColorPaletteOptions() {
        List<Object> myList = new ArrayList<Object>();
        for(int i = 0; i < myColorModule.getColorAmount(); i++) {
            myList.add(i);
        }
        return myList;
    }

    private void penUpOrDown () {
        myPenDownButton = new RadioButton("Pen Down");
        myPenUpButton = new RadioButton("Pen Up");
        myPenUpButton.setSelected(myInfo.isPenUp());
        myPenDownButton.setUserData("Pen Down");
        myPenUpButton.setUserData("Pen Up");
        
        ToggleGroup group1 = new ToggleGroup();
        group1.selectedToggleProperty()
                // Set Change Text if toggled
                .addListener( (ObservableValue<? extends Toggle> ov,
                               Toggle old_toggle,
                               Toggle new_toggle) -> {
                    if (new_toggle == null)
                        return;
                    if (new_toggle.isSelected()) {
                    }
                });
        myPenDownButton.setToggleGroup(group1);
        myPenUpButton.setToggleGroup(group1);
    }

    private void setBorderPane () {
        setTurtlePicker();
        colorSelectorBox();
        setThicknessBox();
        setLineStyleBox();
        setPenUpDownBox();
        layout.setCenter(myOrder);
        layout.setBottom(closingButtonBox);
    }
    
    private void setLineStyleBox () {
        String lineStyleLabel = "Line Style: ";
        ComboBox<Object> myLineCombo = createComboBox(lineStyleLabel, myLineStyleOptions());
        myLineCombo.setValue(myInfo.getLineStyle());
        HBox myLineHBox = createComboHBox(lineStyleLabel);
        myLineHBox.getChildren().add(myLineCombo);
        myOrder.getChildren().add(myLineHBox);
    }
    
    private void setThicknessBox() {
        ComboBox<Object> penThickness = createComboBox("Pen thickness: ", penThicknessOptions());
        penThickness.setValue(myInfo.getPenThickness());
        HBox penThickBox = createComboHBox("Pen thickness: ");
        penThickBox.getChildren().add(penThickness);
        myOrder.getChildren().add(penThickBox);
    }
    
    private void setTurtlePicker() {
        myField = myTurtleChooser();
        HBox turtChoose = createComboHBox("Choose turtle: ");
        turtChoose.getChildren().add(myField);
        myOrder.getChildren().add(turtChoose);
    }
    
    private void setPenUpDownBox() {
        HBox penUpBox = new HBox(SPACING);
        penUpBox.getChildren().addAll(myPenUpButton, myPenDownButton);
        penUpBox.setPadding(new Insets(5, 20, 10, 20));
        myOrder.getChildren().add(penUpBox);
    }
    
    private void colorSelectorBox() {
        String colorPickerLabel = "Choose new pen color: ";
        HBox colorPickerBox = createComboHBox(colorPickerLabel);
        colorPickerBox.getChildren().add(myColorPicker);
        myOrder.getChildren().add(colorPickerBox);
        
        HBox colorChoices = createComboHBox("OR: ");
        myOrder.getChildren().add(colorChoices);
        
        String labelName = "Choose Palette Color: ";
        paletteColor = createComboBox(labelName, makeColorPaletteOptions());
        paletteColor.setValue(myInfo.getColorID());
        setComboBoxListener(paletteColor, labelName);
        HBox paletteBox = createComboHBox(labelName);
        paletteBox.getChildren().add(paletteColor);
        myOrder.getChildren().add(paletteBox);

        myColorPicker.valueProperty().addListener(new ChangeListener<Object>() {
            public void changed (ObservableValue<? extends Object> ov,
                                final Object oldVal,
                                final Object newVal) {
                paletteColor.setDisable(true);
            }
            
        });
        
        paletteColor.valueProperty().addListener(new ChangeListener<Object>() {
            public void changed (ObservableValue<? extends Object> ov,
                                final Object oldVal,
                                final Object newVal) {
                myColorPicker.setDisable(true);
            }
            
        });
    }
    
    private TextField myTurtleChooser() {
        TextField myField = new TextField();
        return myField;
    }
    
    private void setComboBoxListener(ComboBox<Object> myComboBox, String labelName) {
        myComboBox.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<Object>() {
            public void changed (ObservableValue<? extends Object> ov,
                                 final Object oldvalue,
                                 final Object newvalue) {
                if (labelName.contains("Pen"))
                    setPenThickness(newvalue);
                if (labelName.contains("Line"))
                    setLineStyle(newvalue);
                if(labelName.contains("Palette"))
                    setColorID(newvalue);
            }
        });
    }
    
    private int setColorID(Object aColorID) {
        colorID = (int) aColorID;
        return (int) colorID;
    }

    private List<Object> myLineStyleOptions () {
        return LineStyleSpec.getMyLineStyles();
    }

    private ColorPicker makeColorPickerRow () {
        myColorPicker = new ColorPicker(Color.WHITE);
        return myColorPicker;
    }

    private void createEndingButtons () {
        applyButton = makeButton("Apply Changes");
        clearButton = makeButton("Clear");
        closingButtonBox = new HBox(SPACING);
        closingButtonBox.setPadding(new Insets(0, 20, 10, 20));
        closingButtonBox.setAlignment(Pos.CENTER_RIGHT);
        closingButtonBox.getChildren().addAll(clearButton, applyButton);
    }

    private ComboBox<Object> createComboBox (String labelName, List<Object> myOptions) {
        ComboBox<Object> addComboBox = new ComboBox<Object>();
        addComboBox.getItems().addAll(myOptions);
        return addComboBox;
    }
    
    private HBox createComboHBox(String labelName) {
        HBox comboBoxRow = new HBox(SPACING);
        comboBoxRow.setPadding(new Insets(5, 20, 10, 20));
        Label comboBoxLabel = new Label(labelName);
        comboBoxRow.getChildren().add(comboBoxLabel);
        return comboBoxRow;
    }

    private void setLineStyle (Object newvalue) {
        myLineStyle = newvalue.toString();
    }

    private void setPenThickness (Object newvalue) {
        myPenThickness = Integer.parseInt(newvalue.toString());
    }

    public Color getColorValue () {
        //TODO: Change this later
        myColorModule.newColorRow(myColorPicker.getValue());
        return myColorPicker.getValue();
    }

    public int getPenThickness () {
        return myPenThickness;
    }

    public String getLineStyle () {
        return myLineStyle;
    }

    private Button makeButton (String buttonText) {
        Button addButton = new Button(buttonText);
        return addButton;
    }

    @Override
    public void clear () {
        // TODO: get pen to take its previous state
        myColorPicker.setValue(Color.WHITE);
        myPenUpButton.setSelected(myInfo.isPenUp());
        paletteColor.setDisable(false);
        myColorPicker.setDisable(false);
    }

    @Override
    public void onApplyPress (EventHandler<MouseEvent> aEvent) {
        applyButton.setOnMouseClicked(aEvent);
    }

    @Override
    public void onClearPress (EventHandler<MouseEvent> aEvent) {
        clearButton.setOnMouseClicked(aEvent);
    }

    @Override
    public PenInformation buildPenInfo () {
        myInfo.setColorID(colorID);
        //myInfo.setLineStyle(aLineStyle);
        myInfo.setPenThickness(myPenThickness);
        myInfo.setPenUp(myPenUpStatus);
        return myInfo;
    }

    @Override
    public int getTurtleID () {
        try{
            Integer.parseInt(myField.getText().toString());
            System.out.println(Integer.parseInt(myField.getText().toString()));
        }catch(NumberFormatException e){
            return 0;
        }
        return Integer.parseInt(myField.getText().toString());
    }
    
}
