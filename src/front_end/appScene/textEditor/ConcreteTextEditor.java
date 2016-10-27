package front_end.appScene.textEditor;

import java.util.List;
import java.util.stream.Collectors;
import integration.languages.Languages;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * 
 * @author George Bernard
 * @author Kayla Schulz
 *
 */

class ConcreteTextEditor implements ITextEditor {

    private ScrollPane myTextEditor;
    private VBox myTextColumn;
    private List<TextField> myTextFields;
    private List<HBox> myRows;

    private int myWidth;
    private int myHeight;

    private int myLastIndex;
    private boolean myRowsAdded;

    private static final int NUM_START_ROWS = 5;
    private static final int NEW_ROW_BURST = 5;
    private static final int SPACING = 20;

    /******* API Defined Methods ********/

    @Override
    public Node getInstanceAsNode () {
        return myTextEditor;
    }

    @Override
    public void reset () {
        buildInstance(myWidth, myHeight);
    }

    @Override
    public void highlightLine (Color color, int line) {
        if (line >= myLastIndex)
            return;

        Background highlight = new Background(new BackgroundFill(
                                                                 color,
                                                                 CornerRadii.EMPTY,
                                                                 Insets.EMPTY));

        myRows.get(line).setBackground(highlight);
    }

    @Override
    public List<String> getInstructionList () {

        List<String> outputList = myTextFields.stream()
                .map(TextField::getText)
                .collect(Collectors.toList());

        return outputList;
    }

    @Override
    public void setInstructionList (List<String> aInstructions) {
        for (int i = 0; (i < myTextFields.size()) && (i < aInstructions.size()); i++) {
            myTextFields.get(i).setText(aInstructions.get(i));
        }
    }

    /******** Package Visible Methods *********/

    ConcreteTextEditor (int aWidth, int aHeight) {
        buildInstance(aWidth, aHeight);
    }

    void clearTextFields () {
        myTextFields.forEach(tf -> tf.clear());
    }
    
    int getEditorSize(){
		return myLastIndex;
	}

    /******** Private Helper Methods *********/

    private void buildInstance (int aWidth, int aHeight) {
        configureStartParameters(aWidth, aHeight);
        initTextScroller(buildTextColumn());
    }

    private void configureStartParameters (int aWidth, int aHeight) {
        myWidth = aWidth;
        myHeight = aHeight;
        myLastIndex = 0;
        myRowsAdded = false;

        myTextFields = new ArrayList<TextField>();
        myRows = new ArrayList<HBox>();
    }

    private VBox buildTextColumn () {
        myTextColumn = new VBox(0);
        initTextToolbar();
        while (myLastIndex < NUM_START_ROWS)
            newLine();
        return myTextColumn;
    }

    private void initTextToolbar () {
        myTextColumn.getChildren().add(new TextEditorToolbar(this).getBar());
    }

    private void newLine () {
        HBox rowBox = new HBox(SPACING);

        HBox labelBox = new HBox(0);
        Label curLabel = new Label(Integer.toString(myLastIndex + 1));
        labelBox.setMinWidth(SPACING);
        labelBox.setMaxWidth(SPACING);
        labelBox.getChildren().add(curLabel);

        TextField curTextField = new TextField();
        curTextField
                .setMinWidth(myWidth - labelBox.getWidth() - curLabel.getWidth() - SPACING - 30);
        curTextField
                .setMaxWidth(myWidth - labelBox.getWidth() - curLabel.getWidth() - SPACING - 30);
        myTextFields.add(myLastIndex, curTextField);

        curTextField.setOnMouseClicked(e -> {
            if (curTextField.equals(myTextFields.get(myLastIndex - 1))) {
                myRowsAdded = true;
                for (int i = 0; i < NEW_ROW_BURST; i++)
                    newLine();
            }
        });

        rowBox.getChildren().addAll(labelBox, curTextField);
        myRows.add(myLastIndex, rowBox);
        myTextColumn.getChildren().add(rowBox);

        myLastIndex++;
    }

    private void initTextScroller (Node aContent) {

        if (myTextEditor == null)
            myTextEditor = new ScrollPane();
        else {
            myTextEditor.setContent(aContent);
        }

        myTextEditor.setHbarPolicy(ScrollBarPolicy.NEVER);
        myTextEditor.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        myTextEditor.setPrefSize(myWidth + 5, myHeight);

        myTextEditor.vvalueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed (ObservableValue<? extends Number> observable,
                                 Number oldValue,
                                 Number newValue) {
                if (myRowsAdded) {
                    myRowsAdded = false;
                    myTextEditor.setVvalue(myTextEditor.getVmax());
                }

            }
        });
        myTextEditor.setContent(aContent);
    }

    @Override
    public void switchLanguage (Languages aLanguage) {
        // TODO switch language of toolbar

    }
}
