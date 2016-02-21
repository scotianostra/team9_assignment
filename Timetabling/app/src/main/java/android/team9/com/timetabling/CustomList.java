package android.team9.com.timetabling;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
    private static String[] moduleCode;
    private static String[] moduleTitle;
    private Activity context;

    public CustomList(Activity context, String[] moduleCode, String[] moduleTitle) {
        super(context, R.layout.list_view_layout, moduleCode);
        this.context = context;
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewModuleCode = (TextView) listViewItem.findViewById(R.id.textViewModuleCode);
        TextView textViewModuleTitle = (TextView) listViewItem.findViewById(R.id.textViewModuleTitle);

        textViewModuleCode.setText(moduleCode[position]);
        textViewModuleTitle.setText(moduleTitle[position]);

        return listViewItem;
    }
}