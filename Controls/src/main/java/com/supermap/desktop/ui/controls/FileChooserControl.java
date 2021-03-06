package com.supermap.desktop.ui.controls;

import com.supermap.desktop.controls.utilities.ControlsResources;
import com.supermap.desktop.utilities.CoreResources;
import com.supermap.desktop.utilities.StringUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 带文件选择按钮的textField
 *
 * @author xie
 */
public class FileChooserControl extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JTextField textEditor;

    private String result;

	private JButton buttonDelete;

    private JButton button;

	public FileChooserControl() {
		this(null);
	}

    // 初始化带有默认文件路径的文件选择控件
    public FileChooserControl(String filePath) {
        initCompanent();
        setText(filePath);
	    textEditor.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
			    textEditorValueChanged();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
			    textEditorValueChanged();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
			    textEditorValueChanged();
		    }
	    });
	    buttonDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    setText("");
		    }
	    });
	    textEditorValueChanged();
    }

	private void textEditorValueChanged() {
		String text = textEditor.getText();
//		if (buttonDelete.isVisible() != StringUtilities.isNullOrEmpty(text)) {
		buttonDelete.setVisible(!StringUtilities.isNullOrEmpty(text));
//		}

	}


    public void setText(String filePath) {
        this.textEditor.setText(filePath);
    }

    public void setButtonEnabled(boolean flag) {
        this.button.setEnabled(flag);
    }

    public void setTextEnabled(boolean flag) {
        this.textEditor.setEnabled(flag);
    }

    @Override
    public void setEnabled(boolean flag) {
        this.textEditor.setEnabled(flag);
        this.button.setEnabled(flag);
    }

    // 初始化控件信息
    public void initCompanent() {
        this.textEditor = new JTextField(40);
        this.textEditor.setBackground(Color.white);
        this.textEditor.setAutoscrolls(true);
        this.textEditor.setHorizontalAlignment(JTextField.LEFT);

        this.button = new JButton();
        button.setIcon(ControlsResources.getIcon("/controlsresources/Image_DatasetGroup_Normal.png"));
        this.button.setBorder(BorderFactory.createEtchedBorder(1));
        this.button.setFocusPainted(false);
        this.button.setFocusable(false);

	    buttonDelete = new JButton();
	    buttonDelete.setIcon(CoreResources.getIcon("/coreresources/ToolBar/Image_ToolButton_Delete.png"));
	    buttonDelete.setMaximumSize(new Dimension(24, 23));
	    buttonDelete.setPreferredSize(new Dimension(24, 23));
	    buttonDelete.setForeground(Color.red);
	    buttonDelete.setBorderPainted(false);
	    buttonDelete.setContentAreaFilled(false);

	    this.setLayout(new GridBagLayout());
	    this.add(this.textEditor, new GridBagConstraintsHelper(0, 0, 1, 1).setWeight(1, 0).setAnchor(GridBagConstraints.CENTER).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));
	    this.add(buttonDelete, new GridBagConstraintsHelper(1, 0, 1, 1).setWeight(0, 0).setAnchor(GridBagConstraints.CENTER).setFill(GridBagConstraints.NONE).setInsets(0, 0, 0, 0));
	    this.add(button, new GridBagConstraintsHelper(2, 0, 1, 1).setWeight(0, 0).setAnchor(GridBagConstraints.CENTER).setFill(GridBagConstraints.NONE).setInsets(0, 5, 0, 0));


//        this.setLayout(new BorderLayout());
//        this.add(this.textEditor, BorderLayout.CENTER);
//        this.add(this.button, BorderLayout.EAST);
    }

    public void setIcon(ImageIcon image) {
        this.button.setIcon(image);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JButton getButton() {
        return button;
    }

    public JTextField getEditor() {
        return textEditor;
    }
}

