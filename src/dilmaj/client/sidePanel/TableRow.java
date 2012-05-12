package dilmaj.client.sidePanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public class TableRow {
	List<Widget> widgets=new ArrayList<Widget>();
	
	public void addWidget(Widget widget) {
		widgets.add(widget);
	}
	
	public List<Widget> getWidgets() {
		return widgets;
	}
}
