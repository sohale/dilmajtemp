package dilmaj.server;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class ObjectFactory {

    public static String convertPropertyName(String caption) {
        String lowerName = caption.toLowerCase();
        String[] pieces = lowerName.split("_");
        if (pieces.length == 1) {
            return lowerName;
        }
        StringBuffer result = new StringBuffer(pieces[0]);
        for (int i = 1; i < pieces.length; i++) {
            result.append(Character.toUpperCase(pieces[i].charAt(0)));
            result.append(pieces[i].substring(1));
        }
        return result.toString();    }

    public static Object convertToObject(ResultSet rs, Class cl) {
    	Object object=null;
        try {
            Object item = cl.newInstance();
            rs.beforeFirst();
            rs.next();
            int colCount = rs.getMetaData().getColumnCount();
	        for (int i = 1; i <= colCount; i += 1 ) {
		        String colName = rs.getMetaData().getColumnName(i);
		        String propertyName = convertPropertyName(colName);
		        Object value = rs.getObject(i);
		        PropertyDescriptor pd = new PropertyDescriptor(propertyName, cl);
		        Method mt = pd.getWriteMethod();
		        mt.invoke(item, new Object[] {value});
	        }
	        
	        object=item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return object;
    }
    
    public static List convertToObjects(ResultSet rs, Class cl) {
        List result = new ArrayList();
        try {
        	int rowCount=0;
            int colCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object item = cl.newInstance();
                for (int i = 1; i <= colCount; i += 1 ) {
                    String colName = rs.getMetaData().getColumnName(i);
                    String propertyName = convertPropertyName(colName);
                    Object value = rs.getObject(rowCount);
                    PropertyDescriptor pd = new PropertyDescriptor(propertyName, cl);
                    Method mt = pd.getWriteMethod();
                    mt.invoke(item, new Object[] {value});
                }
                result.add(item);
                rowCount++;
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
    
    public static Object[] convertToObjectsArray(ResultSet rs, Class cl) {
    	Object[] objects;
        try {
	    	int row_count = 0;
	    	while(rs.next())
	    	{
	    	row_count ++;
	    	}
	        objects= new Object[row_count];
	
	    	row_count=0;
            int colCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object item = cl.newInstance();
                for (int i = 1; i <= colCount; i += 1 ) {
                    String colName = rs.getMetaData().getColumnName(i);
                    String propertyName = convertPropertyName(colName);
                    Object value = rs.getObject(row_count);
                    PropertyDescriptor pd = new PropertyDescriptor(propertyName, cl);
                    Method mt = pd.getWriteMethod();
                    mt.invoke(item, new Object[] {value});
                }
                objects[row_count++]= item;
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objects;
    }

    
    public static String[] convertToStringArray(ResultSet rs) {
    	String[] objects;
        try {
	    	int row_count = 0;
	    	while(rs.next())
	    	{
	    	row_count ++;
	    	}
	        objects= new String[row_count];
	
	    	row_count=0;
	    	rs.beforeFirst();
            while (rs.next()) {
                objects[row_count++]= rs.getString("caption");
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objects;
    }
}
