package com.dlsc.preferencesfx.util.formsfx;

import com.dlsc.formsfx.model.structure.BooleanField;
import javafx.scene.control.Label;
import org.controlsfx.control.ToggleSwitch;

/**
 * Displays a control for boolean values with a toggle from ControlsFX.
 */
public class ToggleControl extends SimpleControl<BooleanField, ToggleSwitch> {
  public static final double NEGATIVE_LABEL_INSETS = -17.3;
  /**
   * - fieldLabel is the container that displays the label property of the
   * field.
   * - toggleSwitch is the toggle switch to set user input.
   * - container holds the toggle so that it can be styled properly.
   */
  private Label fieldLabel;

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeParts() {
    super.initializeParts();

    node.getStyleClass().add("toggle-control");

    fieldLabel = new Label(field.labelProperty().getValue());
    node = new ToggleSwitch();
    // is necessary to offset the control to the left, because we don't use the provided label
    node.setTranslateX(NEGATIVE_LABEL_INSETS);
    node.setSelected(field.getValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  void layoutParts() {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupBindings() {
    super.setupBindings();

    node.disableProperty().bind(field.editableProperty().not());
    fieldLabel.textProperty().bind(field.labelProperty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupValueChangedListeners() {
    super.setupValueChangedListeners();
    field.userInputProperty().addListener((observable, oldValue, newValue) -> {
      node.setSelected(Boolean.parseBoolean(field.getUserInput()));
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupEventHandlers() {
    node.selectedProperty().addListener((observable, oldValue, newValue) -> {
      field.userInputProperty().setValue(String.valueOf(newValue));
    });
  }

}


