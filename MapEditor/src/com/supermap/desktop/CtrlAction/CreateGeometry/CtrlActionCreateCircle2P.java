package com.supermap.desktop.CtrlAction.CreateGeometry;

import com.supermap.data.DatasetType;
import com.supermap.desktop.Interface.IBaseItem;
import com.supermap.desktop.Interface.IForm;
import com.supermap.ui.Action;

public class CtrlActionCreateCircle2P extends ActionCreateBase {

	public CtrlActionCreateCircle2P(IBaseItem caller, IForm formClass) {
		super(caller, formClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action getAction() {
		return Action.CREATE_CIRCLE_2P;
	}

	@Override
	public boolean isSupportDatasetType(DatasetType datasetType) {
		return DatasetType.REGION == datasetType || DatasetType.REGION3D == datasetType || DatasetType.CAD == datasetType || DatasetType.LINE == datasetType
				|| DatasetType.LINE3D == datasetType || DatasetType.LINEM == datasetType;
	}
}
