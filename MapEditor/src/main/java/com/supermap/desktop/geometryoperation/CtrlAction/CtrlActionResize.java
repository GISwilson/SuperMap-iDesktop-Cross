package com.supermap.desktop.geometryoperation.CtrlAction;

import com.supermap.desktop.Interface.IBaseItem;
import com.supermap.desktop.Interface.IForm;
import com.supermap.desktop.geometryoperation.editor.IEditor;
import com.supermap.desktop.geometryoperation.editor.ResizeEditor;

/**
 * Created by xie on 2016/8/23.
 */
public class CtrlActionResize extends CtrlActionEditorBase {

    private ResizeEditor editor = new ResizeEditor();

    public CtrlActionResize(IBaseItem caller, IForm formClass) {
        super(caller, formClass);
        // TODO Auto-generated constructor stub
    }

    @Override
    public IEditor getEditor() {
        return this.editor;
    }
}