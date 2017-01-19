package networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.TableView.TableRow;

public class TableModelList extends AbstractTableModel implements Serializable {

    //titre du modèle
    private String title;

    //les données
    private List<List<Object>> data = new ArrayList();

    //les noms des colonnes
    private List<String> columnTitles = new ArrayList();

    public TableModelList() {
    }

    /**
     * Constructeur avec les listes
     * 
     * @param rows les données
     * @param columnTitles les noms des colonnes
     */
    public TableModelList(Collection rows, Collection columnTitles) {
        data.addAll(rows);        
        this.columnTitles.addAll(columnTitles);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public int getRowCount() {
        return data.size();
    }

    public TableRow getRow(int rowIndex) {
        return (TableRow) data.get(rowIndex);
    }

    public void setColumnTitles(Collection<String> newColumnTitles) {
        columnTitles.addAll(newColumnTitles);
    }

    public List<String> getColumnTitles() {
        return columnTitles;
    }

    public void setColumnName(int i, String newColumnTitle) {
        columnTitles.set(i, newColumnTitle);
    }

    public int getColumnCount() {
        return columnTitles.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnTitles.get(columnIndex);
    }

    /**
     * Retourne la classe de la colonne.
     * Retourne la classe de l'objet se situant sur la première ligne, si l'objet est nul retourne String.class
     * @param columnIndex le numéro de la colonne
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(getValueAt(0, columnIndex)!=null){
            return getValueAt(0, columnIndex).getClass();
        }
        else
        {
            return String.class;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex,aValue);
    }

    /**
     * Ajoute une ligne
     * La ligne doit avoir le même nombre d'objet
     * Cette méthode fonctionne mais ne conviendrais pas au puriste Java (utilisation de fireTableDataChanged())
     * @param row ligne
     */

    public void addRow(List row){
        data.add(row);
        fireTableDataChanged();
    }
}
