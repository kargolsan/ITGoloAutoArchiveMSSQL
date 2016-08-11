package Modules.Application.Models;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

/**
 * Created by Karol Golec on 11.08.2016.
 */
public class PasswordLabelCell extends TableCell<TaskRow, String> {
    private Label label;

    public PasswordLabelCell() {
        label = new Label();
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.setGraphic(null);
    }

    private String genDotString(int len) {
        String dots = "";

        for (int i = 0; i < len; i++) {
            dots += "\u2022";
        }

        return dots;
    }

    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            label.setText(genDotString(item.length()));
            setGraphic(label);
        } else {
            setGraphic(null);
        }
    }
}
