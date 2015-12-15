package com.supermap.desktop.ui.controls.prjcoordsys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.supermap.data.CoordSysTransMethod;
import com.supermap.data.CoordSysTransParameter;
import com.supermap.data.PrjCoordSys;
import com.supermap.desktop.Application;
import com.supermap.desktop.controls.ControlsProperties;
import com.supermap.desktop.properties.CommonProperties;
import com.supermap.desktop.ui.controls.DialogResult;
import com.supermap.desktop.ui.controls.SmDialog;
import com.supermap.desktop.utilties.CoordSysTransMethodUtilties;

public class JDialogPrjCoordSysTranslator extends SmDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel labelMethod;
	private JComboBox<String> comboBoxMethod;
	private JButton buttonSetPrj;
	private JLabel labelScaleDifference;
	private JFormattedTextField textFieldScaleDifference;
	private JLabel labelRotationX;
	private JFormattedTextField textFieldRotationX;
	private JLabel labelRotationY;
	private JFormattedTextField textFieldRotationY;
	private JLabel labelRotationZ;
	private JFormattedTextField textFieldRotationZ;
	private JLabel labelTranslateX;
	private JFormattedTextField textFieldTranslateX;
	private JLabel labelTranslateY;
	private JFormattedTextField textFieldTranslateY;
	private JLabel labelTranslateZ;
	private JFormattedTextField textFieldTranslateZ;
	private JButton buttonOK;
	private JButton buttonCancel;

	private transient CoordSysTransMethod method = CoordSysTransMethod.MTH_GEOCENTRIC_TRANSLATION;
	private transient CoordSysTransParameter parameter = new CoordSysTransParameter();
	private transient PrjCoordSys targetPrj = null;
	private transient PrjCoordSys beforePrj = null;

	private transient ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonSetPrj) {
				buttonSetPrjClicked();
			} else if (e.getSource() == buttonOK) {
				buttonOKClicked();
			} else if (e.getSource() == buttonCancel) {
				buttonCancelClicked();
			}
		}
	};
	private transient ItemListener itemListener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				comboBoxMethodSelectedChange();
			}
		}
	};

	/**
	 * 新建投影转换窗口
	 *
	 * @param beforePrj 转换之前的投影信息
	 */
	public JDialogPrjCoordSysTranslator(PrjCoordSys beforePrj) {
		this.beforePrj = beforePrj;
		initializeComponents();
		initializeResources();
		fillComponents();
		setComponentsEnabled();
		registerEvents();
		setSize(600, 350);
		setLocationRelativeTo(null);
	}

	public CoordSysTransMethod getMethod() {
		return method;
	}

	public CoordSysTransParameter getParameter() {
		return parameter;
	}

	public PrjCoordSys getTargetPrj() {
		return targetPrj;
	}

	private void initializeComponents() {
		// 基本参数
		this.labelMethod = new JLabel("Method:");
		this.comboBoxMethod = new JComboBox<String>();
		this.labelScaleDifference = new JLabel("ScaleDiff");
		this.textFieldScaleDifference = new JFormattedTextField(NumberFormat.getInstance());
		this.buttonSetPrj = new JButton(ControlsProperties.getString("String_SetDesPrjCoordSys"));

		JPanel panelBase = new JPanel();
		panelBase.setBorder(BorderFactory.createTitledBorder(ControlsProperties.getString("String_BasicParameters")));

		GroupLayout gl_panelBase = new GroupLayout(panelBase);
		gl_panelBase.setAutoCreateContainerGaps(true);
		gl_panelBase.setAutoCreateGaps(true);
		panelBase.setLayout(gl_panelBase);

		// @formatter:off
		gl_panelBase.setHorizontalGroup(gl_panelBase.createSequentialGroup()
				.addGroup(gl_panelBase.createParallelGroup(Alignment.LEADING)
						.addComponent(this.labelMethod)
						.addComponent(this.labelScaleDifference))
				.addGroup(gl_panelBase.createParallelGroup(Alignment.LEADING)
						.addComponent(this.comboBoxMethod)
						.addComponent(this.textFieldScaleDifference))
				.addComponent(this.buttonSetPrj));
		
		gl_panelBase.setVerticalGroup(gl_panelBase.createSequentialGroup()
				.addGroup(gl_panelBase.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelMethod)
						.addComponent(this.comboBoxMethod, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.buttonSetPrj))
				.addGroup(gl_panelBase.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelScaleDifference)
						.addComponent(this.textFieldScaleDifference, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)));
		// @formatter:on

		// 旋转角度
		this.labelRotationX = new JLabel("X:");
		this.textFieldRotationX = new JFormattedTextField(NumberFormat.getInstance());
		this.labelRotationY = new JLabel("Y:");
		this.textFieldRotationY = new JFormattedTextField(NumberFormat.getInstance());
		this.labelRotationZ = new JLabel("Z:");
		this.textFieldRotationZ = new JFormattedTextField(NumberFormat.getInstance());

		JPanel panelRotation = new JPanel();
		panelRotation.setBorder(BorderFactory.createTitledBorder(ControlsProperties.getString("String_Rotation")));
		GroupLayout gl_panelRotation = new GroupLayout(panelRotation);
		gl_panelRotation.setAutoCreateContainerGaps(true);
		gl_panelRotation.setAutoCreateGaps(true);
		panelRotation.setLayout(gl_panelRotation);

		// @formatter:off
		gl_panelRotation.setHorizontalGroup(gl_panelRotation.createSequentialGroup()
				.addGroup(gl_panelRotation.createParallelGroup(Alignment.LEADING)
						.addComponent(this.labelRotationX)
						.addComponent(this.labelRotationY)
						.addComponent(this.labelRotationZ))
				.addGroup(gl_panelRotation.createParallelGroup(Alignment.LEADING)
						.addComponent(this.textFieldRotationX)
						.addComponent(this.textFieldRotationY)
						.addComponent(this.textFieldRotationZ)));
		
		gl_panelRotation.setVerticalGroup(gl_panelRotation.createSequentialGroup()
				.addGroup(gl_panelRotation.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelRotationX)
						.addComponent(this.textFieldRotationX, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRotation.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelRotationY)
						.addComponent(this.textFieldRotationY, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRotation.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelRotationZ)
						.addComponent(this.textFieldRotationZ, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)));
		// @formatter:on

		// 偏移量
		this.labelTranslateX = new JLabel("X:");
		this.textFieldTranslateX = new JFormattedTextField(NumberFormat.getInstance());
		this.labelTranslateY = new JLabel("Y:");
		this.textFieldTranslateY = new JFormattedTextField(NumberFormat.getInstance());
		this.labelTranslateZ = new JLabel("Z:");
		this.textFieldTranslateZ = new JFormattedTextField(NumberFormat.getInstance());

		JPanel panelOffset = new JPanel();
		panelOffset.setBorder(BorderFactory.createTitledBorder(ControlsProperties.getString("String_Offset")));
		GroupLayout gl_panelOffset = new GroupLayout(panelOffset);
		gl_panelOffset.setAutoCreateContainerGaps(true);
		gl_panelOffset.setAutoCreateGaps(true);
		panelOffset.setLayout(gl_panelOffset);

		// @formatter:off
		gl_panelOffset.setHorizontalGroup(gl_panelOffset.createSequentialGroup()
				.addGroup(gl_panelOffset.createParallelGroup(Alignment.LEADING)
						.addComponent(this.labelTranslateX)
						.addComponent(this.labelTranslateY)
						.addComponent(this.labelTranslateZ))
				.addGroup(gl_panelOffset.createParallelGroup(Alignment.LEADING)
						.addComponent(this.textFieldTranslateX)
						.addComponent(this.textFieldTranslateY)
						.addComponent(this.textFieldTranslateZ)));
		
		gl_panelOffset.setVerticalGroup(gl_panelOffset.createSequentialGroup()
				.addGroup(gl_panelOffset.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelTranslateX)
						.addComponent(this.textFieldTranslateX, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelOffset.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelTranslateY)
						.addComponent(this.textFieldTranslateY, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelOffset.createParallelGroup(Alignment.CENTER)
						.addComponent(this.labelTranslateZ)
						.addComponent(this.textFieldTranslateZ, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)));
		// @formatter:on

		// 主界面
		this.buttonOK = new JButton("OK");
		this.buttonCancel = new JButton("Cancel");

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		this.getContentPane().setLayout(groupLayout);

		// @formatter:off
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(panelBase)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panelRotation)
						.addComponent(panelOffset))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10,10,Short.MAX_VALUE)
						.addComponent(this.buttonOK)
						.addComponent(this.buttonCancel)));
		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addComponent(panelBase)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(panelRotation)
						.addComponent(panelOffset))
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(this.buttonOK)
						.addComponent(this.buttonCancel)));
		// @formatter:on
	}

	private void initializeResources() {
		setTitle(ControlsProperties.getString("String_TransParamsSetting"));
		this.labelMethod.setText(ControlsProperties.getString("String_TransMethod"));
		this.labelScaleDifference.setText(ControlsProperties.getString("String_ScaleDifference"));
		this.buttonSetPrj.setText(ControlsProperties.getString("String_SetDesPrjCoordSys"));
		this.buttonOK.setText(CommonProperties.getString(CommonProperties.OK));
		this.buttonCancel.setText(CommonProperties.getString(CommonProperties.Cancel));
	}

	private void registerEvents() {
		this.comboBoxMethod.addItemListener(this.itemListener);
		this.buttonSetPrj.addActionListener(this.actionListener);
		this.buttonOK.addActionListener(this.actionListener);
		this.buttonCancel.addActionListener(this.actionListener);
	}

	private void unregisterEvents() {
		this.comboBoxMethod.removeItemListener(this.itemListener);
		this.buttonSetPrj.removeActionListener(this.actionListener);
		this.buttonOK.removeActionListener(this.actionListener);
		this.buttonCancel.removeActionListener(this.actionListener);
	}

	private void fillComponents() {
		fillComboBoxMethod();
		this.textFieldScaleDifference.setValue(this.parameter.getScaleDifference());
		setRotationParameter();
		this.textFieldTranslateX.setValue(this.parameter.getTranslateX());
		this.textFieldTranslateY.setValue(this.parameter.getTranslateY());
		this.textFieldTranslateZ.setValue(this.parameter.getTranslateZ());
	}

	private void fillComboBoxMethod() {
		this.comboBoxMethod.removeAllItems();
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_GEOCENTRIC_TRANSLATION));
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_MOLODENSKY));
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_MOLODENSKY_ABRIDGED));
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_POSITION_VECTOR));
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_COORDINATE_FRAME));
		this.comboBoxMethod.addItem(CoordSysTransMethodUtilties.toString(CoordSysTransMethod.MTH_BURSA_WOLF));
		this.comboBoxMethod.setSelectedItem(CoordSysTransMethodUtilties.toString(this.method));
	}

	private void buttonSetPrjClicked() {
		JDialogPrjCoordSysSettings prjSettings = new JDialogPrjCoordSysSettings();
		prjSettings.setPrjCoordSys(this.getTargetPrj() == null ? this.beforePrj : this.getTargetPrj());
		if (prjSettings.showDialog() == DialogResult.OK) {
			this.targetPrj = prjSettings.getPrjCoordSys();
		}
		setComponentsEnabled();
	}

	private void comboBoxMethodSelectedChange() {
		this.method = CoordSysTransMethodUtilties.valueOf(this.comboBoxMethod.getSelectedItem().toString());
		setComponentsEnabled();
	}

	private void setComponentsEnabled() {
		if (this.method == CoordSysTransMethod.MTH_GEOCENTRIC_TRANSLATION || this.method == CoordSysTransMethod.MTH_MOLODENSKY
				|| this.method == CoordSysTransMethod.MTH_MOLODENSKY_ABRIDGED) {
			this.textFieldScaleDifference.setEditable(false);
			this.textFieldRotationX.setEditable(false);
			this.textFieldRotationY.setEditable(false);
			this.textFieldRotationZ.setEditable(false);
		} else {
			this.textFieldScaleDifference.setEditable(true);
			this.textFieldRotationX.setEditable(true);
			this.textFieldRotationY.setEditable(true);
			this.textFieldRotationZ.setEditable(true);
		}
		this.buttonOK.setEnabled(this.targetPrj != null);
	}

	/**
	 * 旋转角度单位与组件保持一致，需要是弧度，这里做一下转换。秒转为弧度
	 */
	private void getRotationParameter() {
		//
		this.parameter.setRotateX(Double.valueOf(this.textFieldRotationX.getValue().toString()) / 60 / 60 / 180 * Math.PI);
		this.parameter.setRotateY(Double.valueOf(this.textFieldRotationY.getValue().toString()) / 60 / 60 / 180 * Math.PI);
		this.parameter.setRotateZ(Double.valueOf(this.textFieldRotationZ.getValue().toString()) / 60 / 60 / 180 * Math.PI);
	}

	/**
	 * 旋转角度单位与组件保持一致，需要是弧度，这里做一下转换。弧度转为秒
	 */
	private void setRotationParameter() {
		this.textFieldRotationX.setValue(this.parameter.getRotateX() / Math.PI * 180 * 60 * 60);
		this.textFieldRotationY.setValue(this.parameter.getRotateY() / Math.PI * 180 * 60 * 60);
		this.textFieldRotationZ.setValue(this.parameter.getRotateZ() / Math.PI * 180 * 60 * 60);
	}

	private void buttonOKClicked() {
		this.method = CoordSysTransMethodUtilties.valueOf(this.comboBoxMethod.getSelectedItem().toString());
		this.parameter.setScaleDifference(Double.valueOf(this.textFieldScaleDifference.getValue().toString()));
		getRotationParameter();
		this.parameter.setTranslateX(Double.valueOf(this.textFieldTranslateX.getValue().toString()));
		this.parameter.setTranslateY(Double.valueOf(this.textFieldTranslateY.getValue().toString()));
		this.parameter.setTranslateZ(Double.valueOf(this.textFieldTranslateZ.getValue().toString()));
		this.dialogResult = DialogResult.OK;
		setVisible(false);
	}

	private void buttonCancelClicked() {
		this.dialogResult = DialogResult.CANCEL;
		setVisible(false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogPrjCoordSysTranslator dialog = new JDialogPrjCoordSysTranslator(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			Application.getActiveApplication().getOutput().output(e);
		}
	}
}
